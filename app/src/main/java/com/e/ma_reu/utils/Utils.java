package com.e.ma_reu.utils;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static String makeDialog(String mail) {
        String str = "";
        String[] tokensVal = mail.split(";");

        for (String a : tokensVal) {
            a += "\n";
            str += a;
        }
        return str;

    }
    public static String makeNumberDialog(String mail){
        String s;
        String[] tokensval = mail.split(";");
        List<String>container = Arrays.asList(tokensval);
        s = "" + container.size();
        return s;

    }
}
