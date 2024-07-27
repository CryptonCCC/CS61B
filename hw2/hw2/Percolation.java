package hw2;

import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF gridSet;
    private WeightedQuickUnionUF topSet;
    private boolean[][] grid;
    private int size;
    private int numberOfOpenSites;
    private int virtualTopSite;
    private int virtualBottomSite;


    private int xyToInt(int x, int y) {
        return x * size + y;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N must be greater than 0");
        }

        size = N;
        gridSet = new WeightedQuickUnionUF(N * N + 2);
        topSet = new WeightedQuickUnionUF(N * N + 2);
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        numberOfOpenSites = 0;
        virtualTopSite = size * size;
        virtualBottomSite = size * size + 1;
    }

    private int[] neighbor(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
        }

        int upperNeighbor = xyToInt(x - 1, y);
        int lowerNeighbor = xyToInt(x + 1, y);
        int rightNeighbor = xyToInt(x, y + 1);
        int leftNeighbor = xyToInt(x, y - 1);
        if (x == 0 && y == 0) {
            return new int[] {lowerNeighbor, rightNeighbor};
        } else if (x == 0 && y == size - 1) {
            return new int[] {lowerNeighbor, leftNeighbor};
        } else if (x == size - 1 && y == 0) {
            return new int[] {upperNeighbor, rightNeighbor};
        } else if (x == size - 1 && y == size - 1) {
            return new int[] {upperNeighbor, leftNeighbor};
        } else if (y == 0) {
            return new int[] {upperNeighbor, lowerNeighbor, rightNeighbor};
        } else if (y == size - 1) {
            return new int[] {upperNeighbor, lowerNeighbor, leftNeighbor};
        } else if (x == 0) {
            return new int[] {lowerNeighbor, rightNeighbor, leftNeighbor};
        } else if (x == size - 1) {
            return new int[] {upperNeighbor, rightNeighbor, leftNeighbor};
        }
        return new int[]{upperNeighbor, lowerNeighbor, rightNeighbor, leftNeighbor};
    }

    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        if (grid[row][col]) {
            return;
        }

        grid[row][col] = true;
        numberOfOpenSites++;

        if (row == 0) {
            gridSet.union(virtualTopSite, xyToInt(row, col));
            topSet.union(virtualTopSite, xyToInt(row, col));
        }
        if (row == size - 1) {
            gridSet.union(virtualBottomSite, xyToInt(row, col));
        }

        int[] neighbors = neighbor(row, col);
        for (int neighbor : neighbors) {
            int neighborRow = neighbor / size;
            int neighborCol = neighbor % size;
            if (grid[neighborRow][neighborCol]) {
                gridSet.union(neighbor, xyToInt(row, col));
                topSet.union(neighbor, xyToInt(row, col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return topSet.connected(xyToInt(row, col), virtualTopSite);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return gridSet.connected(virtualBottomSite, virtualTopSite);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(0, 0);
        percolation.open(1, 0);
        percolation.open(2, 0);
        percolation.open(3, 0);
        percolation.open(4, 0);
        System.out.println(percolation.gridSet.connected(0, 25));
        System.out.println(percolation.gridSet.connected(20, 25));
        System.out.println(percolation.gridSet.connected(20, 26));
        System.out.println(percolation.percolates());
        System.out.println(percolation.isFull(2, 0));
    }
}
