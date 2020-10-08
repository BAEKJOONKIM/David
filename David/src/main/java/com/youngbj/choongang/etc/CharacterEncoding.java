package com.youngbj.choongang.etc;

public class CharacterEncoding {

	public static String encode(String text) {
		String result = null;
		result = text;
		
		result = result.replace("<", "&lt;");
		result = result.replace(">", "&gt;");
		result = result.replace("\n", "<br>");
		
		return result;
	}
	
}
