package org.baharan.common.utility;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class StringUtility {
	public static String toUTF8(String isoString) {
		String utf8String = null;
		if (null != isoString && !isoString.equals("")) {
			try {
				byte[] stringBytesISO = isoString.getBytes("ISO-8859-1");
				utf8String = new String(stringBytesISO, "UTF-8");
				if(utf8String.indexOf("?")>=0 && utf8String.indexOf("?")==0)
					utf8String=isoString;
			} catch (UnsupportedEncodingException e) {

				// TODO: This should never happen. The UnsupportedEncodingException
				// should be propagated instead of swallowed. This error would
				// indicate a severe misconfiguration of the JVM.
				// As we can't translate just send back the best guess.
				System.out.println("UnsupportedEncodingException is: "+ e.getMessage());
				utf8String = isoString;
			}
		} else {
			utf8String = isoString;
		}
		return utf8String;
	}
	public static String trimLastZero(String zeroString)
	{
		//1010700000000----->10107
		//1050000000000----->105
		String reverseZero=new StringBuffer(zeroString).reverse().toString();
		int index=0;
		for (char c : reverseZero.toCharArray()) {
			if(c=='0')
				reverseZero=removeCharAt(reverseZero,index);
			else
				break;
		}
		return new StringBuffer(reverseZero).reverse().toString();
	}
	public static String removeCharAt(String s, int pos) {
	    StringBuffer buf = new StringBuffer( s.length() - 1 );
	    buf.append( s.substring(0,pos) ).append( s.substring(pos+1) );
	    return buf.toString();
	}
	public static void main(String[] args) {
		System.out.println(StringUtility.trimLastZero("10107000020010"));
	}
}
