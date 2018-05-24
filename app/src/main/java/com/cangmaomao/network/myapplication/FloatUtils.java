package com.cangmaomao.network.myapplication;

import java.text.DecimalFormat;

public class FloatUtils {

    private static final String KEEP_TWO = "##0.00";

    public static String keepTwo(float val) {
        DecimalFormat decimalFormat = new DecimalFormat(KEEP_TWO);
        return decimalFormat.format(val);
    }

}
