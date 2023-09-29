package com.eldar.challenge.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * JConstruct a string (lowercase) from a List<String> (length <=10)</>
     * @param stringList
     * @return "firsrword secondword thirdword"
     * @throws Exception
     */
    public static String joinStrings(List<String> stringList) throws Exception {
        if (stringList.size() > 10 ) throw new Exception("Array length should not be 10");

        String stringFinal = "";
        for (String str : stringList) {
            if (isValidOnlyLetterAndSpace(str)) stringFinal = stringFinal + str.toLowerCase() + " ";
        }

        return stringFinal;
    }

    /**
     * Check if the string have only letters and spaces
     * @param str
     * @return true ("kevin"), false ("kevin323")
     */
    public static boolean isValidOnlyLetterAndSpace(String str) {
        //Patrón para verificar si la cadena contiene números o caracteres especiales
        String patron = ".*\\d|[!@#\\$%^&\\(\\)_\\+\\{\\}\\[\\]:;<>,.?~\\/-].*";
        //Compilar el patrón en una expresión regular
        Pattern pattern = Pattern.compile(patron);

        Matcher matcher = pattern.matcher(str);
        return !matcher.find();
    }
}
