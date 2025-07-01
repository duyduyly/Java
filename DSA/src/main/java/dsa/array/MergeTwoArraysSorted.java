package dsa.array;

public class MergeTwoArraysSorted {

    //merge two array sorted
    //find k index
    public void merge(int[] n1, int m, int[] n2, int n) {
        for (int v : n2) {
            addElementBySort(n1, m, v);
            m++;
        }
    }

    private void addElementBySort(int[] n1, int m, int v) {
        boolean isBiggest = true;
        for (int i = 0; i < m; i++) {
            if (v < n1[i]) {
                isBiggest = false;
                for (int j = m-1; j >= i; j--) {
                    n1[j + 1] = n1[j];
                }
                n1[i] = v;
                break;
            }
        }
        if (isBiggest) {
            n1[m] = v;
        }
    }


    //use two pointer
    public void merge1(int[] n1, int m, int[] n2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = n1.length - 1;
        while (k >= 0) {
            if (i < 0) {
                n1[k] = n2[j];
                j--;
            } else if (j < 0) {
                n1[k] = n1[i];
                i--;
            } else if (n1[i] > n2[j]) {
                n1[k] = n1[i];
                i--;
            } else {
                n1[k] = n2[j];
                j--;
            }
            k--;
        }
    }
}
