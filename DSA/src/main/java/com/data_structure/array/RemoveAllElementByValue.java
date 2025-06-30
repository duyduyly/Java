package com.data_structure.array;

public class RemoveAllElementByValue {

    // remove all elements by value
    // step 1: find index of value in array
    // step 2: move all elements after that forward
    public int removeAllElementByValue(int value, int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ) {
            if (arr[i] == value) {
                moveValueForward(i, arr);
                n--;// n-1 to return size of length after removed
            } else {
                i++;// when compare if(element value unEquals value) i+1; (avoid case two element adjacent have value equals together)
                // [2,3,3,2] when remove 3 at a[1], value 3 at a[2] = a[1] case wrong)
            }
        }
        return n;
    }

    private void moveValueForward(int i, int[] arr) {
        for (int j = i; j < arr.length - 1; j++) {
            arr[j] = arr[j + 1];
        }
    }
}
