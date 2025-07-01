package dsa.array.prefix_sum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrefixSum {
    public static void main(String[] args) {
//        int[] intArr = new int[]{1,2,3,4,5,6};
//        int length = intArr.length;
//
//        List<Integer> prefixSum = Stream.generate(() -> 0).limit(length).collect(Collectors.toList());
//        prefixSum.set(0, intArr[0]);
//
//        for (int i = 1; i < length; i++)
//            prefixSum.set(i, prefixSum.get(i - 1) + intArr[i]);
//
//        System.out.println(prefixSum);

        String case1 = "011101";
        String case2 = "01001";
        System.out.println(maxScore(case2));

        Stream<String> stream = Stream.of(case1, case2);

        var myList = new String[]{"L", "u", "K"};
        StringBuilder result = new StringBuilder();
        for (String s : myList) result.append(s);

        var listNumber = new ArrayList<String>();
        for (char s : case1.toCharArray()) listNumber.add(String.valueOf(s));

        String reduce = listNumber.stream().reduce("", (x, y) -> x + y);
        System.out.println(reduce);
    }


    private static int maxScore(String s) {
        List<Integer> prefixSum = Stream.generate(() -> 0).limit(s.length()).collect(Collectors.toList());
        List<Integer> zeroCountList = Stream.generate(() -> 0).limit(s.length()).collect(Collectors.toList());
        List<Integer> oneCountList = Stream.generate(() -> 0).limit(s.length()).collect(Collectors.toList());

        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') zeroCount++;
            if (s.charAt(i) == '1') oneCount++;
            zeroCountList.add(zeroCount);
            oneCountList.add(oneCount);
        }

        for (int i = 1; i < zeroCountList.size(); i++) {
            prefixSum.add(zeroCountList.get(i) + oneCountList.get(i));
        }
        return prefixSum.stream().max(Integer::compare).get();
    }

    private static int countStringValue(String s, int middleIndex) {
        int countLeft = 0;
        int countRight = 0;

        //i == right
        //j == left
        int i = middleIndex;
        int j = 0;
        while (true) {
            if (i < s.length() && j <= middleIndex) {
                j++;
                i++;
            } else {
                break;
            }
            if ("1".equals(String.valueOf(s.charAt(i)))) countRight++;
            if ("0".equals(String.valueOf(s.charAt(j)))) countLeft++;

        }
        return countLeft + countRight;
    }

}

