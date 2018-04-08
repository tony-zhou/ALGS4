package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final WeightedQuickUnionUF ufMap;
    private boolean[] id;
    private final int n;
    private int count = 0;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.n = n;
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        ufMap = new WeightedQuickUnionUF(n * n + 1);
        id = new boolean[n * n + 1];
        for (int i = 1; i <= n; i++) {
            weightedQuickUnionUF.union(0, i);
            ufMap.union(0, i);
            weightedQuickUnionUF.union(n * n + 1, n * n + 1 - i);
        }
        for (int i = 1; i <= n * n; i++)
            id[i] = false;
    }

    private int positionHelper(int row, int col) {
        return n * (row - 1) + col;
    }

    private void validationHelper(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();
    }

    public void open(int row, int col) {
        validationHelper(row, col);
        if (!isOpen(row, col)) {
            if (row > 1 && isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(positionHelper(row, col), positionHelper(row - 1, col));
                ufMap.union(positionHelper(row, col), positionHelper(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(positionHelper(row, col), positionHelper(row + 1, col));
                ufMap.union(positionHelper(row, col), positionHelper(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(positionHelper(row, col), positionHelper(row, col - 1));
                ufMap.union(positionHelper(row, col), positionHelper(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(positionHelper(row, col), positionHelper(row, col + 1));
                ufMap.union(positionHelper(row, col), positionHelper(row, col + 1));
            }
            id[positionHelper(row, col)] = true;
            count += 1;
        }
    }

    public boolean isOpen(int row, int col) {
        validationHelper(row, col);
        return id[positionHelper(row, col)];
    }

    public boolean isFull(int row, int col) {
        validationHelper(row, col);
        return ufMap.connected(0, positionHelper(row, col)) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, n * n + 1) && numberOfOpenSites() >= n;
    }

}