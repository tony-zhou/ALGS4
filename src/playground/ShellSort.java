package playground;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by tony on 4/21/18.
 */
public class ShellSort {
    public static void sort(Comparable[] a) {
        for (int k = 2; k >= 0; k--) {
            int h = 3 * k + 1;
            for (int i = h; i < a.length; i++) {
                for (int j = i; j - h >= 0; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exchange(a, j, j - h);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Comparable[] data = {1, 23, 5, 7, 5, 9, 12, -1, 34, 0};
        ShellSort.sort(data);
        for(Comparable i: data) {
            StdOut.println(i);
        }
    }
}
