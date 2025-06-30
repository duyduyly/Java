package com.data_structure.array;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int [] arr1 = new int []{7,3,5,2,4,22,33,4,1};
        int [] arr2 = new int []{4,5,2,6,23,6,2,3};

        removeElementByValue(22, arr1);


    }

    //step 1: find value and get index
    //step 2: remove value and previous index next to index of value
    private static void removeElementByValue(int value, int[] arr){
        int[] newArr = new int[arr.length-1];
        int indexByValue = getIndexByValue(value, arr);
        if (indexByValue == -1){
            System.out.println("the Value do not exist in Array");
            return;
        }

        int newArrIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if(i != indexByValue){
                newArr[newArrIndex] = arr[i];
                newArrIndex++;
            }
        }

        System.out.println(Arrays.toString(newArr));

    }

    private static int getIndexByValue(int value, int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value){
                return  i;
            }
        }
        return -1;
    }
}

