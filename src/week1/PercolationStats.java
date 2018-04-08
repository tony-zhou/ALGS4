package week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double MAGICNUMBER = 1.96;
    private final double[] threholdList;
    private final double mean;
    private final double stddev;


    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        threholdList = new double[trials];
        for (int i = 0; i < trials; i++) {
            threholdList[i] = percolationThrehold(n);
        }
        mean = StdStats.mean(threholdList);
        stddev = StdStats.stddev(threholdList);
    }

    private double percolationThrehold(int n) {
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int position = StdRandom.uniform(1, n * n + 1);
            int row = position % n == 0 ? position / n : position / n + 1;
            int col = position - n * (row - 1);
            percolation.open(row, col);
        }
        return percolation.numberOfOpenSites() / (double) (n * n);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return mean - MAGICNUMBER * stddev / Math.sqrt(threholdList.length);
    }

    public double confidenceHi() {
        return mean + MAGICNUMBER * stddev / Math.sqrt(threholdList.length);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trails);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + "[" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
