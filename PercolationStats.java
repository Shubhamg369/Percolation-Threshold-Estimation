import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {

    //Instance Variables
    int trials;
    double[] trialsArray;

    // Perform T independent experiments (Monte Carlo simulations) on an
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("Invalid Arguments");

        trials = T;
        trialsArray = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation stats = new Percolation(N);
            while (!stats.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                stats.open(x, y);
            }
            trialsArray[i] = (double) stats.numberOfOpenSites() / (N * N);
        }
    }

    // Sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(trialsArray);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(trialsArray);
    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
