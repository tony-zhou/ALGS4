package playground;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import week3.Point;

import java.util.Comparator;

public class MergeSort {

    private static int min_gap = 7;

    public static void sort(Comparable[] a, Comparator comparator) {
        Comparable[] aux = new Comparable[a.length];
        // avoid a and aux switch cause NPE
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        sort(a, aux, comparator, 0, a.length - 1);
        if (a.length <= min_gap + 1) {
            for (int i = 0; i < a.length; i++) {
                a[i] = aux[i];
            }
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, Comparator comparator, int lo, int hi) {
        // use shell sort with little array is more faster
        if (lo + min_gap >= hi) {
            sort(aux, comparator, lo, hi);
            return;
        }
        // without optimization
        // if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        // make aux is sorted
        sort(a, aux, comparator, lo, mid);
        sort(a, aux, comparator, mid + 1, hi);
        // without optimization
        // sort(a, aux, lo, mid);
        // sort(a, aux, mid + 1, hi);
        // avoid merge if array is sorted
        if (less(a[mid], a[mid + 1], comparator)) return;
        // merge aux back to a
        merge(aux, a, comparator, lo, hi, mid);
    }

    public static void sort(Comparable[] a, Comparator comparator, int lo, int hi) {
        for (int k = (hi + 1) / 3; k >= 0; k--) {
            int h = 3 * k + 1;
            for (int i = h; i < hi + 1; i++) {
                for (int j = i; j - h >= lo; j -= h) {
                    if (less(a[j], a[j - h], comparator)) {
                        exchange(a, j, j - h);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, Comparator comparator, int lo, int hi, int mid) {

        // without optimization
        // for (int k = lo; k <= hi; k++) {
        //     aux[k] = a[k];
        // }

        assert isSorted(a, comparator, lo, mid);
        assert isSorted(a, comparator, mid + 1, hi);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) aux[k] = a[j++];
            else if (j > hi) aux[k] = a[i++];
            else if (less(a[i], a[j], comparator)) aux[k] = a[i++];
            else aux[k] = a[j++];
        }

        assert isSorted(aux, comparator, lo, hi);

    }

    private static boolean isSorted(Comparable[] a, Comparator comparator, int i, int j) {
        for (int k = i; k < j; k++) {
            if (less(a[k + 1], a[k], comparator))
                return false;
        }
        return true;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean less(Comparable i, Comparable j, Comparator comparator) {
        return comparator.compare(i, j) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        MergeSort.sort(points, points[1].slopeOrder());
        for (Comparable i : points) {
            StdOut.println(i);
        }

    }
}
