package com.youngbj.choongang.etc;

public class OutputEscapeHtmlUtils {

	public static String outputEncode(String text) {// 엔터가 br로 바껴야됨,
	      String result = null;
	      result = text;

	      result = result.replace("<", "&lt;");
	      result = result.replace(">", "&gt;");
	      result = result.replace("\n", "<br>");

	      return result;
	   }
}
