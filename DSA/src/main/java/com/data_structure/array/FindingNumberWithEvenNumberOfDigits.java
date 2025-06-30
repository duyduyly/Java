package com.data_structure.array;

public class FindingNumberWithEvenNumberOfDigits {

    public int findLengthNumberIsEvent(int[] arr) {
        int count = 0;
        for (int v : arr) {
            if (countLengthNumber(v, 0) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public int findLengthNumberIsEvent2(int[] arr) {
        int count = 0;
        for (int v : arr) {
            if (countLengthNumber(v) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    private int countLengthNumber(int number) {
        int count = 0;
        while (number != 0) {
            count++;
            number = number / 10;
        }
        return count;
    }

    // recursive;
    private int countLengthNumber(int number, int count) {
        if (number == 0) {
            return count;
        }
        count++;
        return countLengthNumber(number / 10, count);
    }
}
