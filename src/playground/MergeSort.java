package playground;


import edu.princeton.cs.algs4.StdOut;

public class MergeSort {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        // avoid a and aux switch cause NPE
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // use shell sort with little array is more faster
        if (lo + 7 >= hi) {
            sort(aux, lo, hi);
            return;
        }
        // without optimization
        // if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        // make aux is sorted
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        // without optimization
        // sort(a, aux, lo, mid);
        // sort(a, aux, mid + 1, hi);
        // avoid merge if array is sorted
        if (less(a[mid], a[mid + 1])) return;
        // merge aux back to a
        merge(aux, a, lo, hi, mid);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int k = (hi + 1) / 3; k >= 0; k--) {
            int h = 3 * k + 1;
            for (int i = h; i < hi + 1; i++) {
                for (int j = i; j - h >= lo; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exchange(a, j, j - h);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int hi, int mid) {

        // without optimization
        // for (int k = lo; k <= hi; k++) {
        //     aux[k] = a[k];
        // }

        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) aux[k] = a[j++];
            else if (j > hi) aux[k] = a[i++];
            else if (less(a[i], a[j])) aux[k] = a[i++];
            else aux[k] = a[j++];
        }

        assert isSorted(aux, lo, hi);

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

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Comparable[] data = {1, 23, 5, 7, 5, -1, -7, 10, 9, 34, 34, 0};
        MergeSort.sort(data);
        for (Comparable i : data) {
            StdOut.println(i);
        }

    }
}
