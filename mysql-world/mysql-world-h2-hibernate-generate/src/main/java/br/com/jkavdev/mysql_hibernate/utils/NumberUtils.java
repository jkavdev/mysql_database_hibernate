package br.com.jkavdev.mysql_hibernate.utils;

import com.mysql.cj.util.StringUtils;

public class NumberUtils {
	
	public static Integer getInteger(String value) {
		return StringUtils.isEmptyOrWhitespaceOnly(value) ? 0 : Integer.valueOf(value);
	}

	public static Float getFloat(String value) {
		return StringUtils.isEmptyOrWhitespaceOnly(value) ? 0 : Float.valueOf(value);
	}

}
