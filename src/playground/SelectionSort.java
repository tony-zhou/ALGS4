package playground;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by tony on 4/19/18.
 */
public class SelectionSort {
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Comparable[] data = {1, 23, 5, 7, 5, -1, 34, 0};
        SelectionSort.sort(data);
        for(Comparable i: data) {
            StdOut.println(i);
        }
    }
}
