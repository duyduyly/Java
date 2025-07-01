package com.data_structure.utils;

public class TestUtils {

    public static String arrToString(int[] arr) {
        StringBuilder str = new StringBuilder();
        for (Object v : arr) {
            str.append("[").append(v).append("]");
        }
        return str.toString();
    }
}
