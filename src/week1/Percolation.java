import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int[] id;
    private int N;
    private int count;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        N = n;
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        id = new int[n * n + 1];
        for (int i = 1; i <= n; i++) {
            weightedQuickUnionUF.union(0, i);
            weightedQuickUnionUF.union(n * n + 1, n * n + 1 - i);
        }
        for (int i = 1; i <= n * n; i++)
            id[i] = 0;
    }

    public void open(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();
        if (row > 1 && isOpen(row - 1, col))
            weightedQuickUnionUF.union(N * (row - 1) + col, N * (row - 2) + col);
        if (row < N && isOpen(row + 1, col))
            weightedQuickUnionUF.union(N * (row - 1) + col, N * row + col);
        if (col > 1 && isOpen(row, col - 1))
            weightedQuickUnionUF.union(N * (row - 1) + col, N * (row - 1) + col - 1);
        if (col < N && isOpen(row, col + 1))
            weightedQuickUnionUF.union(N * (row - 1) + col, N * (row - 1) + col + 1);
        id[N * (row - 1) + col] = 1;
        count += 1;
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();
        return id[N * (row - 1) + col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
            throw new IllegalArgumentException();
        return weightedQuickUnionUF.connected(0, N * (row - 1) + col);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, N * N + 1);
    }

    public static void main(String[] args) {
    }
}