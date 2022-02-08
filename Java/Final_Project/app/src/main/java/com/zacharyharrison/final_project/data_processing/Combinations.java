package com.zacharyharrison.final_project.data_processing;

import com.zacharyharrison.final_project.models.Dice;

import java.util.Arrays;

public class Combinations {
    public boolean keepGoing = true;
    private final int length;
    private final int base;
    private final int dropLow;
    private final int dropHigh;
    private final int[] head;
    private int[] sums;
    private int[] funcDist;
    private double[] probDist;


    public Combinations(int X, int dY, int dropLow, int dropHigh) {
        this.length = X;
        this.base = dY;
        this.dropLow = dropLow;
        this.dropHigh = dropHigh;
        this.head = new int[length];
        for (int i = 0; i < length; i++) {
            head[i] = base;
        }
        run();
    }

    public Combinations(Dice dice) {
        this.length = dice.numOfDice;
        this.base = dice.numOfSides;
        this.dropLow = dice.dropLow;
        this.dropHigh = dice.dropHigh;
        this.head = new int[length];
        for (int i = 0; i < length; i++) {
            head[i] = base;
        }
        run();
    }

    private void run() {
        // generating sum and pSum lists
        int listLength = (base - 1) * (length - (dropLow + dropHigh)) + 1;
        sums = new int[listLength];
        funcDist = new int[listLength];
        probDist = new double[listLength];
        if (dropLow == 0 && dropHigh == 0) {
            for (int total = length; total <= base*length; total++) {
                sums[total - length] = total;
                funcDist[total - length] = f(total);
            }
        } else {
            while (keepGoing) {
                downOne();
                int total = Arrays.stream(getCombinations()).sum();
                sums[total - (length - (dropLow + dropHigh))] = total;
                funcDist[total - (length - (dropLow + dropHigh))] += 1;
            }
        }

        for (int i = 0; i < probDist.length; i++) {
            probDist[i] = funcDist[i] / Math.pow(base, length);
        }
    }

    private void downOne() {
        for (int i = 0; i < this.length; i++) {
            if (head[i] > 1) {
                head[i]--;
                return;
            } else if (head[i] == 1) {
                head[i] = base;
                if ((i + 1) < length && head[i + 1] > 1) {
                    head[i + 1]--;
                    return;
                }
            }
        }
        keepGoing = false;
    }

    private int[] getCombinations() {
        int[] arr = new int[length];
        if (length >= 0) System.arraycopy(head, 0, arr, 0, length);
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, dropLow, length - dropHigh);
    }

    private int f(int Total) {
        int sum = 0;
        for (int k = 0; k <= ((Total - length)/base); k++){
            sum += comb(length, k) * Math.pow(-1, k) * comb(Total - base * k - 1, length - 1);
        }
        return sum;
    }

    private int getIndex(int n, int r) {
        return (int) ((n+1)*n/2.0 + r);
    }

    private int comb(int n, int r) {
        int[] pascal = new int[getIndex(n, r) + 1];
        int index;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                index = getIndex(i, j);
                if (index > pascal.length-1) break;
                if (j == 0 || i-j == 0) {
                    pascal[index] = 1;
                }else if (j == 1 || i-j == 1) {
                    pascal[index] = i;
                }else {
                    pascal[index] = pascal[getIndex(i-1, j-1)] + pascal[getIndex(i-1, j)];
                }
            }
        }
        return pascal[getIndex(n, r)];
    }

    public double getMean() {
        if (dropLow == 0 && dropHigh == 0) {
            return (length * (base + 1)/2.0);
        }
        double mean = 0;
        for (int i = 0; i < sums.length; i++) {
            mean += sums[i] * probDist[i];
        }
        return mean;
    }

    public double getStandardDeviation() {
        double mean = getMean();
        double x_iDiffSquaredTotal = 0d;
        double XSumTotal = 0d;
        for (int i = 0; i < funcDist.length; i++) {
            x_iDiffSquaredTotal += funcDist[i] * Math.pow(i+3 - mean, 2);
            XSumTotal += funcDist[i];
        }
        return Math.pow(x_iDiffSquaredTotal/XSumTotal, .5);
    }

    public int[] getSums() {
        return sums;
    }

    public int[] getFuncDist() {
        return funcDist;
    }

    public double[] getProbDist() {
        return probDist;
    }
}

