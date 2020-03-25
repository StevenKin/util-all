package me.stevenkin.util.all.sort;

public class QuickSort {

    public void sort(int[] array, int l, int r) {
        if (array == null || array.length == 0)
            return;
        if (l >= r)
            return;
        int m = partition(array, l, r);
        sort(array, l, m);
        sort(array, m+1, r);
    }

    private int partition(int[] array, int l, int r) {
        int p = r - 1;
        int i = l - 1;
        int j = l;
        for (; j < r - 1; j++) {
            if (array[j] >= array[p]) {
                break;
            }
            i++;
        }
        if (j == r - 1)
            return p;
        while (true) {
            for (j++; j < r - 1; j++) {
                if (array[j] < array[p]) {
                    break;
                }
            }
            if (j == r - 1) {
                int t = array[i + 1];
                array[i + 1] = array[p];
                array[p] = t;
                return i + 1;
            }
            int t = array[i + 1];
            array[i + 1] = array[j];
            array[j] = t;
            i++;
        }
    }

    public static void main(String[] args) {
        int[] array = {3,2,5,6};
        new QuickSort().sort(array, 0, array.length);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

    }

}
