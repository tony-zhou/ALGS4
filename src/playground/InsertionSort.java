package playground;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by tony on 4/20/18.
 */
public class InsertionSort {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1])) {
                    exchange(a, j, j - 1);
                }
            }
        }
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Comparable[] data = {1, 23, 5, 7, 5, -1, 9, 34, 0};
        SelectionSort.sort(data);
        for(Comparable i: data) {
            StdOut.println(i);
        }
    }
}
