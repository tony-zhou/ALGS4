import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] threholdList;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        threholdList = new double[trials];
        for (int i = 0; i < trials; i++) {
            threholdList[i] = percolationThrehold(n);
        }
    }

    private double percolationThrehold(int n) {
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            percolation.open(row, col);
        }
        return percolation.numberOfOpenSites() / (double) (n * n);
    }

    public double mean() {
        return StdStats.mean(threholdList);
    }

    public double stddev() {
        return StdStats.stddev(threholdList);
    }

    public double confidenceLo() {
        return mean() - 1.9 * Math.sqrt(stddev() / threholdList.length);
    }

    public double confidenceHi() {
        return mean() + 1.9 * Math.sqrt(stddev() / threholdList.length);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int Trails = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(N, Trails);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + "[" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
