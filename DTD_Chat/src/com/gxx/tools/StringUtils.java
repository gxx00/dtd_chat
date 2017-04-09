package com.gxx.tools;

public class StringUtils {
	public static boolean StringisNull(String text) {
		return text == null || text.equals("");

	}

	public static boolean StringisEmail(String text) {

		boolean bo1 = text.indexOf("@") >= 1;
		boolean bo2 = text.indexOf(".") - text.indexOf("@") >= 2;
		boolean bo3 = text.endsWith(".com");
		return bo1 && bo2 && bo3;

	}
}
