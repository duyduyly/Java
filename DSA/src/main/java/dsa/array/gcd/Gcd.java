package dsa.array.gcd;

//https://leetcode.com/problems/greatest-common-divisor-of-strings/description/?envType=study-plan-v2&envId=leetcode-75&fbclid=IwAR1023OuMZZOVYghHCPL6rcrkITkC-o9rAX0ktjHpGVFB4efBof18i2HfnM
public class Gcd {

    // thuật toán vét cạn  O(min(a, b))
    public static int gcdByBruteForce(int a, int b) {
        int gcd = 1;
        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    //#2 – Tìm UCLN sử dụng thuật toán Euclid
    /*
     * Java method to find GCD of two number using Euclid's method
     * @return GDC of two numbers in Java
     */
    public int findGCD(int x, int y) {
        //base case
        if(y== 0){
            return x;
        }
        return this.findGCD(y, x%y);
    }
    // Code from https://vntalking.com
    public int findGCD2(int x, int y) {
        int temp;
        while(y!= 0) {
            temp = x % y;
            x= y;
            y= temp;
        }
        return x;
    }


    public static void main(String[] args) {
//        String word1 = "abc";
//        String word2 = "pqr";
//        String result = mergeAlternately(word1, word2);
//        System.out.println(result);  // Output: "apbqcr"

        String word3 = "ab";
        String word4 = "pqrs";
        String result2 = mergeAlternately(word3, word4);
        System.out.println(result2);  // Output: "apbqrs"
    }

    public static String mergeAlternately(String word1, String word2) {
        StringBuilder mergedString = new StringBuilder();
        int i = 0, j = 0;
        int len1 = word1.length();
        int len2 = word2.length();

        while (i < len1 || j < len2) {
            if (i < len1) {
                mergedString.append(word1.charAt(i));
                i++;
            }
            if (j < len2) {
                mergedString.append(word2.charAt(j));
                j++;
            }
        }

        return mergedString.toString();
    }
}