import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

// Models an N-by-N percolation system.
public class Percolation {

    //Instance variables
    private Boolean[][] x;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF Bwash;
    private int openSites;
    private int GridSize;

    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("N must be greater than 0");

        openSites = 0;
        GridSize = N;

        x = new Boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                x[i][j] = false;
            }

        uf = new WeightedQuickUnionUF((N * N) + 2);
        Bwash = new WeightedQuickUnionUF((N * N) + 1);

        for (int j = 0; j < N; j++) {
            uf.union(0, encode(0, j));
            Bwash.union(0, (encode(0, j)));
        }

        for (int j = 0; j < N; j++)
            uf.union(((N * N) + 1), encode(N - 1, j));
    }

    // Check if input is valid
    public void InputValidation(int row, int col) {
        if (row < 0 || row >= GridSize || col < 0 || col >= GridSize)
            throw new IllegalArgumentException("Invalid Argument");
    }

    // Open site (row, col) if it is not open already.
    public void open(int row, int col) {
        InputValidation(row, col);

        if (!isOpen(row, col)) {
            x[row][col] = true;
            openSites++;

            if (row - 1 >= 0 && isOpen(row - 1, col)) {
                uf.union(encode(row - 1, col), encode(row, col));
                Bwash.union(encode(row - 1, col), encode(row, col));
            }

            if (row + 1 < GridSize && isOpen(row + 1, col)) {
                uf.union(encode(row + 1, col), encode(row, col));
                Bwash.union(encode(row + 1, col), encode(row, col));
            }

            if (col - 1 >= 0 && isOpen(row, col - 1)) {
                uf.union(encode(row, col - 1), encode(row, col));
                Bwash.union(encode(row, col - 1), encode(row, col));
            }

            if (col + 1 < GridSize && isOpen(row, col + 1)) {
                uf.union(encode(row, col + 1), encode(row, col));
                Bwash.union(encode(row, col + 1), encode(row, col));
            }
        }
    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) {
        InputValidation(row, col);
        return x[row][col];
    }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
        InputValidation(row, col);
        return isOpen(row, col) && Bwash.connected(encode(row, col), 0);
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return openSites;
    }

    // Does the system percolate?
    public boolean percolates() {
        return (uf.connected(0, ((GridSize * GridSize) + 1)));
    }

    // An integer ID (1...N) for site (row, col).
    // The encode method maps a site (x,y) to an integer value corresponding
    // to a site in the union-find data structure.
    private int encode(int row, int col) {
        return (GridSize * row) + col + 1;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }
        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
