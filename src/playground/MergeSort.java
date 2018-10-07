package playground;


import edu.princeton.cs.algs4.StdOut;

public class MergeSort {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, hi, mid);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int hi, int mid) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

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
            if (less(a[i + 1], a[i]))
                return false;
        }
        return true;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    public static void main(String[] args) {
        Comparable[] data = {1, 23, 5, 7, 5, -1, 9, 34, 0};
        MergeSort.sort(data);
        for (Comparable i : data) {
            StdOut.println(i);
        }
    }
}
