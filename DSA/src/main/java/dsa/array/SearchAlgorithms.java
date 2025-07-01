package dsa.array;

import dsa.utils.CommonUtils;

import java.util.Arrays;

public class SearchAlgorithms {


    public static void main(String[] args) {
        int[] intArray = CommonUtils.randomIntArrayID(10_000_000);
        System.out.println(Arrays.toString(intArray));
        intArray[5000_000] = 1000_000;
        Arrays.sort(intArray);

        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();
        CommonUtils.countRuntime("Search Inline", () -> {
            int i = searchAlgorithms.searchInline(intArray, 1000_000);
            System.out.println("Index: " + i);
        });

        CommonUtils.countRuntime("Binary Search", () -> {
            int i = searchAlgorithms.binarySearch(intArray, 1000_000);
            System.out.println("Index: " + i);
        });

        CommonUtils.countRuntime("Binary Search Recursive", () -> {
            int i = searchAlgorithms.binarySearchRecursive(intArray, 0, intArray.length - 1, 1000_000);
            System.out.println("Index: " + i);
        });


    }

    // Function for linear search
    public int searchInline(int arr[], int x) {
        int n = arr.length;

        // Traverse array arr[]
        for (int i = 0; i < n; i++) {

            // If element found then
            // return that index
            if (arr[i] == x)
                return i;
        }
        return -1;
    }

    // Function that returns index of
    // x if it is present in arr[l, r]
    int binarySearchRecursive(int arr[], int l,
                              int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present
            // at the middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than
            // mid, then it can only be
            // present in left subarray
            if (arr[mid] > x)
                return binarySearchRecursive(arr, l,
                        mid - 1, x);

            // Else the element can only be
            // present in right subarray
            return binarySearchRecursive(arr, mid + 1,
                    r, x);
        }

        // Reach here when element is
        // not present in array
        return -1;
    }

    // Returns index of x if it is present
    // in arr[], else return -1
    int binarySearch(int arr[], int x) {

        int l = 0, r = arr.length - 1;

        // Iterate until l <= r
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is at mid
            if (arr[m] == x)
                return m;

            // If x greater than arr[m]
            // then ignore left half
            if (arr[m] < x)
                l = m + 1;

                // If x is smaller than arr[m]
                // ignore right half
            else
                r = m - 1;
        }

        // If we reach here, then element
        // was not present
        return -1;
    }

}
