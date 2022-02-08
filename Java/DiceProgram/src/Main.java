import java.util.Arrays;

public class Main {
    public static void main (String[] args) {
        String INSTRUCTIONS = ("java DiceProgram XdY dropLowest dropHighest\n" +
                "Too few arguments. Please use as follows: [X]d[Y] -[dropLowest] +[dropHighest]");
        int numOfDice = 0, numOfSides = 0, dropLowest = 0, dropHighest = 0;
        if (args.length < 1) {
            System.out.println(INSTRUCTIONS);
            System.exit(1);
        }else {
            String[] xdY = args[0].split("d");
            numOfDice = Integer.parseInt(xdY[0]);
            numOfSides = Integer.parseInt(xdY[1]);
            if (args.length == 3) {
                dropLowest = Integer.parseInt(args[1].substring(1));
                dropHighest = Integer.parseInt(args[2].substring(1));
                if (dropLowest < 0 || dropHighest < 0 || dropLowest + dropHighest >= numOfDice) {
                    System.out.println(INSTRUCTIONS);
                    System.exit(1);
                }
            }
            if (numOfDice < 0 || numOfSides < 0) {
                System.out.println(INSTRUCTIONS);
                System.exit(1);
            }
            System.out.println(numOfDice + "d" + numOfSides + " -" + dropLowest + " +" + dropHighest);
        }

        if (dropLowest == 0 && dropHighest == 0) {
            System.out.println("mean = " + numOfDice * (numOfSides + 1)/2);
            System.out.println("standard deviation = " + Math.sqrt(numOfDice * (Math.pow(numOfSides, 2) - 1)/12));
            System.exit(1);
        }
        int[] X = new int[numOfSides];
        int[] XSum = new int[numOfSides * (numOfDice - (dropLowest + dropHighest))];
        int combTotal = 0;
        int sum = 0;
        int[] sumSum = new int[numOfSides*(numOfDice-1)- (numOfDice-(dropLowest+dropHighest)) + 1];
        Combinations combinations = new Combinations(numOfDice, numOfSides);
        while (combinations.keepGoing) {
            for (int num :combinations.getCombinations(dropLowest, dropHighest)) {
                X[num - 1] += 1;
                combTotal += num;
            }
            sum = Arrays.stream(combinations.getCombinations(dropLowest, dropHighest)).sum();
            sumSum[sum - (numOfDice-(dropLowest+dropHighest))] += 1;
            XSum[combTotal - 1] += 1;
            combTotal = 0;
            //combinations.printCombinations();
            combinations.downOne();
        }

        System.out.println(Arrays.toString(sumSum));
        // calculating mean:
        double x_iTotal = 0d;
        for (int i = 0; i < X.length; i++) {
            x_iTotal += X[i] * (i+1);
        }
        double mean = x_iTotal/Math.pow(numOfSides, numOfDice);
        System.out.println("mean = " + mean);

        // calculating standard deviation:
        double x_iDiffSquaredTotal = 0d;
        double XSumTotal = 0d;
        for (int i = 0; i < XSum.length; i++) {
            x_iDiffSquaredTotal += XSum[i] * Math.pow(i+1 - mean, 2);
            XSumTotal += XSum[i];
        }
        double standDev = Math.pow(x_iDiffSquaredTotal/XSumTotal, .5);
    }
}
