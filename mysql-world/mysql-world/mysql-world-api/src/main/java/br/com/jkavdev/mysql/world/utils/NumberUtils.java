package br.com.jkavdev.mysql.world.utils;

import org.apache.commons.lang3.StringUtils;

public class NumberUtils {

    public static Integer getInteger(String value) {
        return StringUtils.isNotBlank(value) ? 0 : Integer.valueOf(value);
    }

    public static Float getFloat(String value) {
        return StringUtils.isNotBlank(value) ? 0 : Float.valueOf(value);
    }

}
