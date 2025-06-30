package com.data_structure.array;


import com.data_structure.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MergeTwoArraysSortedTest {

    private final MergeTwoArraysSorted mergeTwoArraysSorted;

    public MergeTwoArraysSortedTest(){
        mergeTwoArraysSorted = new MergeTwoArraysSorted();
    }

    @Test
    void merge1_case1(){
        int[] n1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] n2 = new int[]{4, 5, 6};
        int[] result = new int[]{1,2,3,4,5,6};
        int m = 3;
        int n = 3;
        mergeTwoArraysSorted.merge1(n1, m, n2, n);
        Assertions.assertEquals(TestUtils.arrToString(result), TestUtils.arrToString(n1));
    }

    @Test
    void merge1_case2(){
        int[] n1 = new int[]{7, 8, 9, 0, 0, 0};
        int[] n2 = new int[]{4, 5, 6};
        int[] result = new int[]{4,5,6,7,8,9};
        int m = 3;
        int n = 3;
        mergeTwoArraysSorted.merge1(n1, m, n2, n);
        Assertions.assertEquals(TestUtils.arrToString(result), TestUtils.arrToString(n1));
    }

    @Test
    void merge1_case3(){
        int[] n1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] n2 = new int[]{1, 2, 3};
        int[] result = new int[]{1,1,2,2,3,3};
        int m = 3;
        int n = 3;
        mergeTwoArraysSorted.merge1(n1, m, n2, n);
        Assertions.assertEquals(TestUtils.arrToString(result), TestUtils.arrToString(n1));
    }


    @Test
    void merge_case4(){
        int[] n1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] n2 = new int[]{4, 5, 6};
        int[] result = new int[]{1,2,3,4,5,6};
        int m = 3;
        int n = 3;
        mergeTwoArraysSorted.merge(n1, m, n2, n);
        Assertions.assertEquals(TestUtils.arrToString(result), TestUtils.arrToString(n1));
    }

    @Test
    void merge_case5(){
        int[] n1 = new int[]{7, 8, 9, 0, 0, 0};
        int[] n2 = new int[]{4, 5, 6};
        int[] result = new int[]{4,5,6,7,8,9};
        int m = 3;
        int n = 3;
        mergeTwoArraysSorted.merge(n1, m, n2, n);
        Assertions.assertEquals(TestUtils.arrToString(result), TestUtils.arrToString(n1));
    }

    @Test
    void merge_case6(){
        int[] n1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] n2 = new int[]{1, 2, 3};
        int[] result = new int[]{1,1,2,2,3,3};
        int m = 3;
        int n = 3;
        mergeTwoArraysSorted.merge(n1, m, n2, n);
        Assertions.assertEquals(TestUtils.arrToString(result), TestUtils.arrToString(n1));
    }
}