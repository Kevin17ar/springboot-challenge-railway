package com.eldar.challenge.util;

import java.util.List;

public class StringUtil {

    public static String joinStrings(List<String> stringList) throws Exception {
        if (stringList.size() > 10 ) throw new Exception("Array length must be 10");

        String stringFinal = "";
        for (String str : stringList) {
            if (!isValidOnlyLetterAndSpace(str)) throw  new IllegalArgumentException("Invalid characters");
            stringFinal = stringFinal + str.toLowerCase() + " ";
        }

        return stringFinal;
    }

    public static boolean isValidOnlyLetterAndSpace(String str) {
        return str.matches("[a-zA-Z][32]{6}");
    }
}
