package com.data_structure.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindingNumberWithEvenNumberOfDigitsTest {

    private  final FindingNumberWithEvenNumberOfDigits findingNumberWithEvenNumberOfDigits;

    public FindingNumberWithEvenNumberOfDigitsTest(){
        findingNumberWithEvenNumberOfDigits = new FindingNumberWithEvenNumberOfDigits();
    }

    @Test
    void findLengthNumberIsEvent_case01(){
        int[] arr = new int[]{1,2,3,44,1,23,4,22,3333,444,22};
        int result = 5;
        int lengthNumberIsEvent = findingNumberWithEvenNumberOfDigits.findLengthNumberIsEvent(arr);
        assertEquals(result, lengthNumberIsEvent);
    }

    @Test
    void findLengthNumberIsEvent_case02(){
        int[] arr = new int[]{1,22,33333,232323,1};
        int result = 2;
        int lengthNumberIsEvent = findingNumberWithEvenNumberOfDigits.findLengthNumberIsEvent(arr);
        assertEquals(result, lengthNumberIsEvent);
    }

    @Test
    void findLengthNumberIsEvent_case03(){
        int[] arr = new int[]{1};
        int result = 0;
        int lengthNumberIsEvent = findingNumberWithEvenNumberOfDigits.findLengthNumberIsEvent(arr);
        assertEquals(result, lengthNumberIsEvent);
    }


    @Test
    void findLengthNumberIsEvent2_case01(){
        int[] arr = new int[]{1,2,3,44,1,23,4,22,3333,444,22};
        int result = 5;
        int lengthNumberIsEvent = findingNumberWithEvenNumberOfDigits.findLengthNumberIsEvent2(arr);
        assertEquals(result, lengthNumberIsEvent);
    }

    @Test
    void findLengthNumberIsEvent2_case02(){
        int[] arr = new int[]{1,22,33333,232323,1};
        int result = 2;
        int lengthNumberIsEvent = findingNumberWithEvenNumberOfDigits.findLengthNumberIsEvent2(arr);
        assertEquals(result, lengthNumberIsEvent);
    }

    @Test
    void findLengthNumberIsEvent2_case03(){
        int[] arr = new int[]{1};
        int result = 0;
        int lengthNumberIsEvent = findingNumberWithEvenNumberOfDigits.findLengthNumberIsEvent2(arr);
        assertEquals(result, lengthNumberIsEvent);
    }

}