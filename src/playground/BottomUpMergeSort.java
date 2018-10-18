package playground;

import edu.princeton.cs.algs4.StdOut;

public class BottomUpMergeSort {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        bottomUpSort(a, aux, 0, a.length);
    }

    private static void bottomUpSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        for (int sz = 1; sz < hi; sz += sz) {
            for (int i = lo, j = lo + 2 * sz - 1; j <= hi; i += 2 * sz, j += 2 * sz) {
                j = Math.min(hi - 1, j);
                bottomUpMerge(a, aux, i, j, (i + j) / 2);
            }
        }

    }

    private static void bottomUpMerge(Comparable[] a, Comparable[] aux, int lo, int hi, int mid) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        assert isSorted(aux, lo, mid);
        assert isSorted(aux, mid + 1, hi);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

        assert isSorted(a, lo, hi);

    }

    private static boolean isSorted(Comparable[] a, int i, int j) {
        for (int k = i; k < j; k++) {
            if (less(a[k + 1], a[k]))
                return false;
        }
        return true;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    public static void main(String[] args) {
        Comparable[] data = {23, 7, 5, 10, 8, 9, -1};
        BottomUpMergeSort.sort(data);
        for (Comparable i : data) {
            StdOut.println(i);
        }

    }

}
