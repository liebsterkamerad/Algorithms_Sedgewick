/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf_2;
    private final int n;
    private int numberOfOpenSites = 0;
    private final int topVirtualIndex;
    private final int bottomVirtualIndex;
    private final boolean[] openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n <= 0 is not allowed!");
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n * n + 2); // plus 2 virtual sites for top and bottom
        this.uf_2 = new WeightedQuickUnionUF(n * n + 2); // plus 2 virtual sites for top and bottom
        this.openSites = new boolean[n * n + 1]; // plus one for virtual top site
        this.topVirtualIndex = 0;
        this.bottomVirtualIndex = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int currentSiteIndex = getUFIndex(row, col);

        if (!openSites[currentSiteIndex]) {
            openSites[currentSiteIndex] = true;
            numberOfOpenSites++;

            if (row == 1) {
                uf.union(topVirtualIndex, getUFIndex(1, col));
                uf_2.union(topVirtualIndex, getUFIndex(1, col));
            }

            if (row == n) {
                uf.union(bottomVirtualIndex, getUFIndex(n, col));
            }

            // merge with adjacent open sites
            if (row > 1 && openSites[getUFIndex(row - 1, col)]) {
                uf.union(currentSiteIndex, getUFIndex(row - 1, col));
                uf_2.union(currentSiteIndex, getUFIndex(row - 1, col));
            }

            if (row < n && openSites[getUFIndex(row + 1, col)]) {
                uf.union(currentSiteIndex, getUFIndex(row + 1, col));
                uf_2.union(currentSiteIndex, getUFIndex(row + 1, col));
            }

            if (col > 1 && openSites[getUFIndex(row, col - 1)]) {
                uf.union(currentSiteIndex, getUFIndex(row, col - 1));
                uf_2.union(currentSiteIndex, getUFIndex(row, col - 1));
            }

            if (col < n && openSites[getUFIndex(row, col + 1)]) {
                uf.union(currentSiteIndex, getUFIndex(row, col + 1));
                uf_2.union(currentSiteIndex, getUFIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[getUFIndex(row, col)];
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n)
            throw new IllegalArgumentException(
                    String.format("Row index %s is outside allowed range (1, %d)!", row, n));
        if (col < 1 || col > n)
            throw new IllegalArgumentException(
                    String.format("Column index %s is outside allowed range (1, %d)!", col, n));
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf_2.find(topVirtualIndex) == uf_2.find(getUFIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(topVirtualIndex) == uf.find(bottomVirtualIndex);
    }

    private int getUFIndex(int row, int col) {
        validate(row, col);
        // plus 1 for virtual top site having index 0
        return (col - 1) + (row - 1) * n + 1;
    }
}