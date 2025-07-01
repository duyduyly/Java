package com.data_structure.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveAllElementByValueTest {

    private final RemoveAllElementByValue removeAllElementByValue;

    public RemoveAllElementByValueTest(){
        removeAllElementByValue = new RemoveAllElementByValue();
    }

    @Test
    void removeAllElementByValue_case01(){
        int[] arr = new int[]{2,3,3,2};
        int v = 2;
        int result = 2;
        int numberElementValue = removeAllElementByValue.removeAllElementByValue(v, arr);
        assertEquals(result, numberElementValue);
    }

    @Test
    void removeAllElementByValue_case02(){
        int[] arr = new int[]{2,3,3,2};
        int v = 3;
        int result = 2;
        int numberElementValue = removeAllElementByValue.removeAllElementByValue(v, arr);
        assertEquals(result, numberElementValue);
    }

    @Test
    void removeAllElementByValue_case03(){
        int[] arr = new int[]{2,3,3,2,3,4};
        int v = 3;
        int result = 3;
        int numberElementValue = removeAllElementByValue.removeAllElementByValue(v, arr);
        assertEquals(result, numberElementValue);
    }
}