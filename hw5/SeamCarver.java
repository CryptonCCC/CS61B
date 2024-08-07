import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    private double[][] energyMatrix;

    public SeamCarver(Picture picture) {
        this.picture = picture;
        width = picture().width();
        height = picture.height();
        energyMatrix = new double[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                energyMatrix[x][y] = energy(x, y);
            }
        }
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    private double deltaXSquare(int x, int y) {
        Color left = picture.get(Math.floorMod(x - 1, width), y);
        Color right = picture.get(Math.floorMod(x + 1, width), y);

        double Rx = left.getRed() - right.getRed();
        double Gx = left.getGreen() - right.getGreen();
        double Bx = left.getBlue() - right.getBlue();

        return Rx * Rx + Gx * Gx + Bx * Bx;
    }

    private double deltaYSquare(int x, int y) {
        Color up = picture.get(x, Math.floorMod(y + 1, height));
        Color bottom = picture.get(x, Math.floorMod(y - 1, height));

        double Ry = up.getRed() - bottom.getRed();
        double Gy = up.getGreen() - bottom.getGreen();
        double By = up.getBlue() - bottom.getBlue();

        return Ry * Ry + Gy * Gy + By * By;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x > width - 1 || x < 0 || y > height - 1 || y < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        return deltaXSquare(x, y) + deltaYSquare(x, y);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        swapWH();
        energyMatrix = transpose(energyMatrix);

        int[] seam = findVerticalSeam();

        swapWH();
        energyMatrix = transpose(energyMatrix);
        return seam;
    }

    private void swapWH() {
        int t = height;
        height = width;
        width = t;
    }

    private double[][] transpose(double[][] matrix) {
        int w = matrix.length;
        int h = matrix[0].length;

        double[][] tMatrix = new double[h][w];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                tMatrix[j][i] = matrix[i][j];
            }
        }

        return tMatrix;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int[][] edgeTo = new int[width][height];
        double[][] M = new double[width][height];

        //fill M iteratively
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                //base case for M
                if (j == 0) {
                    M[i][j] = energyMatrix[i][j];
                    edgeTo[i][j] = i;
                    continue;
                }


                //border case
                if (i == 0) {
                    M[i][j] = energyMatrix[i][j] + Math.min(M[i][j - 1], M[i + 1][j - 1]);
                    edgeTo[i][j] = M[i][j - 1] > M [i + 1][j - 1] ?
                            i + 1 : i;
                } else if (i == width - 1) {
                    M[i][j] = energyMatrix[i][j] + Math.min(M[i - 1][j - 1], M[i][j - 1]);
                    edgeTo[i][j] = M[i - 1][j - 1] > M[i][j - 1] ?
                            i : i - 1;
                } else {
                    int minVertices = i - 1;
                    for (int k = 0; k < 2; k++){
                        if (M[minVertices][j - 1] > M[i + k][j - 1]) {
                            minVertices = i + k;
                        }
                    }
                    M[i][j] = energyMatrix[i][j] + M[minVertices][j - 1];
                    edgeTo[i][j] = minVertices;
                }

            }
        }

        int[] seam = new int[height];

        //find the minimum vertices
        int minVertices = 0;
        for (int i = 0; i < width; i++) {
            if (M[i][height - 1] < M[minVertices][height - 1]) {
                minVertices = i;
            }
        }

        //fill seam
        for (int i = height - 1; i >= 0; i--) {
            if (i == height - 1) {
                seam[i] = minVertices;
                continue;
            }
            seam[i] = edgeTo[seam[i + 1]][i + 1];
        }
        return seam;
    }

    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new NullPointerException();
        } else if (seam.length != width) {
            throw new java.lang.IllegalArgumentException();
        } else {
            for (int i = 0; i < seam.length - 1; i++) {
                if (Math.abs(seam[i] - seam[i + 1]) > 1) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }

        SeamRemover.removeHorizontalSeam(picture, seam);
    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new NullPointerException();
        } else if (seam.length != height) {
            throw new java.lang.IllegalArgumentException();
        } else {
            for (int i = 0; i < seam.length - 1; i++) {
                if (Math.abs(seam[i] - seam[i + 1]) > 1) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }

        SeamRemover.removeVerticalSeam(picture, seam);
    }
}