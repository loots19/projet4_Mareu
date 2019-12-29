package com.e.ma_reu.utils;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static String getListOfParticipant(String mail) {
        String str = "";
        String[] tokensVal = mail.replaceAll("[\\s ,!&$.#|:/]", ";").split(";");

        for (String a : tokensVal) {
            a += "\n";
            str += a;
        }
        return str;

    }
    public static String getNumbersOfParticipant(String mail){
        String s;
        String[] tokensval = mail.replaceAll("[\\s ,!&$.#|:/]", ";").split(";");
        List<String>container = Arrays.asList(tokensval);
        s = "" + container.size();
        return s;

    }
}
