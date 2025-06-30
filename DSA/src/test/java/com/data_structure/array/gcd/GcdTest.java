package com.data_structure.array.gcd;

public class GcdTest {

    private final Gcd gcd = new Gcd();

    //https://leetcode.com/problems/greatest-common-divisor-of-strings/description/?envType=study-plan-v2&envId=leetcode-75&fbclid=IwAR1023OuMZZOVYghHCPL6rcrkITkC-o9rAX0ktjHpGVFB4efBof18i2HfnM
    @org.junit.jupiter.api.Test
    public void case1(){
        String str1 = "ABCABC", str2 = "ABC";
        int gcd1 = gcd.findGCD(str1.length(), str2.length());
        System.out.println(gcd1);
        System.out.println(str2.substring(0, gcd1));
    }
}
