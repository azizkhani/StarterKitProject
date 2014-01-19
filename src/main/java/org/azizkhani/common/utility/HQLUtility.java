package org.azizkhani.common.utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class HQLUtility {

	public static Map<String, Object> createJQL(StringBuffer jqlbuf,
			String filter, String order) {
		Map params = new HashMap<String, Object>();
		int start = jqlbuf.indexOf("order by");
		String oldOrder = null;
		if (start > -1) {
			oldOrder = jqlbuf.substring(start + 8).trim();
			jqlbuf = jqlbuf.replace(start, jqlbuf.length(), "");
		}

		if (filter != null && filter.length() > 0) {
			if (jqlbuf.indexOf(" where ") == -1) {
				jqlbuf.append(" where ");
				addFiter(jqlbuf, filter, params);
			} else {
				jqlbuf.append(" and ( ");
				addFiter(jqlbuf, filter, params);
				jqlbuf.append(")");
			}
		}
		if (order != null && order.length() > 0) {
			jqlbuf.append(" order by ").append(order);
			if (oldOrder != null) {
				jqlbuf.append(",").append(oldOrder);
			}
		} else if (oldOrder != null) {
			jqlbuf.append(" order by ").append(oldOrder);
		}
		return params;
	}

	@SuppressWarnings("unchecked")
	private static void addFiter(StringBuffer jqlbuf, String filterStr,
			Map params) {
		boolean addAnd = false;
		String[] pairs = filterStr.split("@;@");
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i].length() == 0)
				continue;
			String[] pair = pairs[i].split("@@");// Each pair is such this :
													// template@@fname@@value
			if (pair.length < 3 || pair[2] == null || pair[2].length() == 0) {
				jqlbuf.append(" 1=1 ");
				return;
			}
			String[] name = pair[1].split("@");
			if (name.length == 1) {// is simple field
				if (addAnd)
					jqlbuf.append(" and ");
				addAnd = true;
				jqlbuf.append(pair[0].replaceAll("fname", name[0]).replaceAll(
						"value", pair[2].trim()));
			} else {// is not simple field my be is a date field
				if (name[1].equals("from")) {// Date type. Has from and to date
					if (addAnd)
						jqlbuf.append(" and ");
					addAnd = true;
					params.put("param" + i + "_from",
							DateUtility.solarToDate(pair[2].trim()));// TODO
																		// lang?

					if (filterStr.indexOf(name[0] + "@to") != -1) {
						params.put("param" + i + "_to",
								DateUtility.solarToDate(pairs[i + 1]
										.split("@@")[2].trim()));
						jqlbuf.append(name[0]).append(" between :param")
								.append(i).append("_from and :param").append(i)
								.append("_to");
					} else {
						jqlbuf.append(name[0]).append(" >= :param").append(i)
								.append("_from ");
					}
				} else {
					if (name[1].equals("to")) {
						if (filterStr.indexOf(name[0] + "@from") != -1) {// Already
																			// added
																			// in
																			// from
																			// clause
																			// processing
							continue;
						} else {
							if (addAnd)
								jqlbuf.append(" and ");
							addAnd = true;
							params.put("param" + i + "_to",
									DateUtility.solarToDate(pair[2].trim()));// TODO
																				// lang?
							jqlbuf.append(name[0]).append(" <= :param")
									.append(i).append("_to ");
						}
					}
				}
			}
		}
	}

	public static String genSimpleFilterStr(String fieldName, String value,String... template) {
		String tmp = "fname='value'";
		if (template != null && template.length > 0) {
			tmp = template[0];
		}
		fieldName = fieldName == null ? "x" : fieldName;
		value = value == null ? "x" : value;
		return new StringBuffer(tmp).append("@@").append(fieldName)
				.append("@@").append(value).append("@;@").toString();
	}

	public static String getDateFilterStr(String fieldName, String from,
			String to) {
		return genSimpleFilterStr(fieldName + "@from", from)
				+ genSimpleFilterStr(fieldName + "@to", to);
	}

	public static String genFilterStr(Map<String, String> constraints) {
		if (constraints == null)
			return "";
		StringBuffer filter = new StringBuffer();
		for (Iterator iterator = constraints.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> entry = (Entry<String, String>) iterator.next();
			filter.append(genSimpleFilterStr(entry.getKey(), entry.getValue()));
		}
		return filter.toString();
	}

	

	private static String status1 = "(%s = '%s')";//            			UI.General.Search.isEqual
	private static String status2 = "(%s != '%s')";//          				UI.General.Search.isNotEqual
	private static String status3 = "(%s > '%s')";//            			UI.General.Search.Lager
	private static String status4 = "(%s < '%s')"; //           			UI.General.Search.Lower
	private static String status5 = "(%s >= '%s')"; //          			UI.General.Search.GreaterThanOrEqual
	private static String status6 = "(%s <= '%s')";//           			UI.General.Search.LessThanOrEqual
	private static String status7 = "(%s Like '%s%s%s')";//     			UI.General.Search.IncludeThisValue
	private static String status8 = "(%s Like '%s%s')"; //      			UI.General.Search.BeginValue
	private static String status9 = "(%s Like '%s%s')";// 					UI.General.Search.EndValue
	private static String status10 = "(%s Not Like '%s%s%s')"; //			UI.General.Search.NotLike
	private static String status11 = "(%s Not Like '%s%s')";// 				UI.General.Search.NotLikeBeginValue
	private static String status12 = "(%s Not Like '%s%s')";//  			UI.General.Search.NotLikeEndValue
	private static String status13 = "(%s Between %s And %s)"; //		    UI.General.Search.Between
    private static String status14 = "(%s Not Between %s And %s)";//		UI.General.Search.NotBetween

	// $$,$$e.firstName$$23@@24$$13$$,$$e.lastName$$azizi$$1$$,$$e.ozviat$$1$$1$$,$$
	public static String toHql(String temp) {
		// (e.firstName between 23 and 24 ) and (e.lastName='aziz') and
		// (e.ozviat!=1)
		String sentence[] = temp.split(Pattern.quote("$$,$$"));
		String totalSentence = "";
		for (int i = 1; i < sentence.length; i++) {
			String nested_sentence[] = sentence[i].split(Pattern.quote("$$"));
			if (!sentence[i].contains("@@")) {
				int count = 1;
				if (nested_sentence.length > 2)
					count = Integer.parseInt(nested_sentence[2]);
				switch (count) {
				case 1:
					totalSentence += String.format(status1, nested_sentence[0],nested_sentence[1]);
					break;
				case 2:
					totalSentence += String.format(status2, nested_sentence[0],nested_sentence[1]);
					break;
				case 3:
					totalSentence += String.format(status3, nested_sentence[0],nested_sentence[1]);
					break;
				case 4:
					totalSentence += String.format(status4, nested_sentence[0],nested_sentence[1]);
					break;
				case 5:
					totalSentence += String.format(status5, nested_sentence[0],nested_sentence[1]);
					break;
				case 6:
					totalSentence += String.format(status6, nested_sentence[0],nested_sentence[1]);
					break;
				case 7:
					totalSentence += String.format(status7, nested_sentence[0],"%", nested_sentence[1], "%");
					break;
				case 8:
					totalSentence += String.format(status9, nested_sentence[0],nested_sentence[1], "%");
					break;
				case 9:
					totalSentence += String.format(status8, nested_sentence[0],"%", nested_sentence[1]);
					break;
				case 10:
					totalSentence += String.format(status10,nested_sentence[0], "%", nested_sentence[1], "%");
					break;
				case 12:
					totalSentence += String.format(status11,nested_sentence[0], "%", nested_sentence[1]);
					break;
				case 11:
					totalSentence += String.format(status12,nested_sentence[0], nested_sentence[1], "%");
					break;
				default:
					break;
				}
				if (i != sentence.length - 1) {
					if (nested_sentence.length > 3)
						totalSentence += " " + nested_sentence[3] + " ";
					else
						totalSentence += " And ";
				}
			} else {
				int count = 13;
				if (nested_sentence[2].length() > 2)
					count = Integer.parseInt(nested_sentence[2]);
				String twoValue[] = nested_sentence[1].split(Pattern.quote("@@"));
				switch (count) {
				case 13:
					totalSentence += String.format(status13,nested_sentence[0], twoValue[0], twoValue[1]);
					break;
				case 14:
					totalSentence += String.format(status14,nested_sentence[0], twoValue[0], twoValue[1]);
					break;
				default:
					break;
				}
				if (i != sentence.length - 1) {
					if (nested_sentence.length > 3)
						totalSentence += " " + nested_sentence[3] + " ";
					else
						totalSentence += " And ";
				}
			}
		}
		return totalSentence;
	}
	public static void toHQL(StringBuffer jqlbuf,String filter, String order) {
		int start = jqlbuf.indexOf("order by");
		String oldOrder = null;
		if (start > -1) {
			oldOrder = jqlbuf.substring(start + 8).trim();
			jqlbuf = jqlbuf.replace(start, jqlbuf.length(), "");
		}
		
		if (filter != null && filter.length() > 0) {
			if (jqlbuf.indexOf(" where ") <0) 
				jqlbuf.append(" where 1=1 ");	
			
			String where=toHql(filter);
			if(where.length()>0){
				jqlbuf.append(" and ( ");
				jqlbuf.append(where);
				jqlbuf.append(" )");
			}
		}
		if (order != null && order.length() > 0) {
			jqlbuf.append(" order by ").append(order);
			if (oldOrder != null) {
				jqlbuf.append(",").append(oldOrder);
			}
		} else if (oldOrder != null) {
			jqlbuf.append(" order by ").append(oldOrder);
		}
	}
	public static void main(String[] args) {
		System.out.println(HQLUtility.toHql(" $$,$$e.firstName$$23@@24$$13$$,$$e.lastName$$azizi$$1$$,$$e.ozviat$$1$$1$$,$$"));
	}
}
