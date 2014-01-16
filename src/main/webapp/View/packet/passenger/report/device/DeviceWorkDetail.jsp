<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%@ include file="../../../../Head.jsp" %>
    <%@ include file="../../../../DateHead.jsp" %>
    
    <script type="text/javascript">
	    var restUrl="<c:url value = '/rest/packet/passenger/report/line' />";
	    $(function(){
	    	JsonData = { searchFilter: "", order: "", pageNumber: 0, pageSize: 0 };
            fillCombo("cmbLocation"	, 	"<c:url value = '/rest/location/list' />", JsonData, "id", "name",".....");
            $('#txtFromDate').datepicker({dateFormat: 'yy/mm/dd',showButtonPanel: true});
            $('#txtToDate').datepicker({dateFormat: 'yy/mm/dd',showButtonPanel: true});
		});
	    function search() {
	        
	        JsonData = { lineCode:$('#txtLineCode').val(), 
						 fromDate:$('#txtFromDate').val(), 
						 toDate:$('#txtToDate').val(),
						 locationId:$('#cmbLocation').val(), 
						 order: "", pageNumber: 0, pageSize: 10 };
			 
	        $.getJSON(restUrl+"/workDetail", JsonData, function (entities) {
	            resultNum = entities.totalRecords;
	            $('#entityBody tr').remove();
	            $('#GridRowTemplate').tmpl(entities.entityList).appendTo('#entityBody');
	            createNavigation(entities.totalRecords, 0, 10);
	        });
	    }
		function clearSearch(){
			$('.searchControl').val("");
		}


    </script>
</head>
<body>
	<div style="direction: rtl">
	 <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
            	<td>منطقه</td>
            	<td>
            		<select id="cmbLocation" class="searchControl">
            			<option></option>
            		</select>
            	</td>
            	<td>کد خط</td>
            	<td>
            		<input  id="txtLineCode" type="text" class="text searchControl " style="direction:ltr" size="40" />
            	</td>
            	<td>
            		<input type="button"  value="جستجو"    onclick="search()"  class="actionBtn" />
            	</td>
            </tr>
            <tr>
            	<td>از تاریخ</td>
            	<td>
            		<input  id="txtFromDate" type="text" class="text searchControl" style="direction:ltr" size="40" />
            	</td>
            	<td>تا تاریخ</td>
            	<td>
            		<input  id="txtToDate" type="text" class="text searchControl" style="direction:ltr" size="40" />
            	</td>
            	<td>
            		<input type="button"  value="پاک کردن"    onclick="clearSearch()"  class="actionBtn" />
            	</td>
            </tr>
     </table>
	 <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center" width="100%">
                    <table class="grid" width="100%" align="center">
                        <thead>
                            <tr class="grid_header">
                                <th nowrap="nowrap">
                                		ردیف
                                </th>
                                 <th nowrap="nowrap">
                                 		کد خط
                                </th>
                                <th nowrap="nowrap">
                                		نام خط
                                </th>
                                <th nowrap="nowrap">
                                		تاریخ تراکنش
                                </th>
                                <th nowrap="nowrap">
                                		زمان تراکنش 
                                </th>
                                <th nowrap="nowrap">
                                		مبلغ تراکنش  
                                </th>
                            </tr>
                        </thead>
                        <tbody id="entityBody">
								<script id="GridRowTemplate" type="text/html">
							    <tr id="pattern"  class="oddRow" >
									<td align="right">
							        </td>
									<td align="right">
							            ${lineCode}
							        </td>
									<td align="right">
							            ${lineName}
							        </td>
									<td align="right">
							            ${actionDate}
							        </td>
									<td align="right">
							            ${actionTime}
							        </td>
									<td align="right">
							            ${amount}
							        </td>
							    </tr>
								</script>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <!--navigation:begin-->
                <td align="left" style="width: 100%; height: 25px; background: #B9CCEB;">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                        <td nowrap="nowrap">
                                            <input type="text" name="pageSize" onblur="pageSize = $('#pageSize').val();init();"
                                                id="pageSize" value="10" size="1" />
                                        </td>
                                        <td>
                                            <a href="#" onclick="pageSize = $('#pageSize').val();init();returnValue=false;">
                                                <img src="../../../../Content/images/refresh.gif" /></a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="padding-right: 50px;">
                                <spring:message code="UI.General.ResultNum" /> <span id="resultNum" style="font-weight: bold; color: #f00;">
                                </span>
                            </td>
                            <td style="padding-left: 5px; padding-right: 50px;">
                                <table>
                                    <tr>
                                        <td>
                                            <spring:message code="UI.General.Page" />
                                        </td>
                                        <td>
                                            <a title="" id="nextIcon" class="noborder" href="#" onclick="nextPage()" align="left">
                                                <img src="../../../../Content/images/next.gif" /></a>
                                        </td>
                                        <td id="navigateNums" nowrap="nowrap" align="left" dir="ltr">
                                        </td>
                                        <td>
                                            <a title="" id="prevIcon" class="noborder" href="#" onclick="prevPage()">
                                                <img src="../../../../Content/images/prev.gif" /></a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
                <!--navigation:end-->
            </tr>
        </table>
    </div>
</body>
</html>