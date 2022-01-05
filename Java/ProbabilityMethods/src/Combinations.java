import java.util.Arrays;

public class Combinations {
    public static void printTime(double timeFinal, double timeInitial) {
        if (timeFinal - timeInitial < 1000) {
            System.out.println("--This method took " + (timeFinal - timeInitial) + " milliseconds.\n");
        }else {
            System.out.println("--This method took " + (timeFinal - timeInitial)/1000f + " seconds.\n");
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int r = Integer.parseInt(args[1]);
        double timeInitial;
        if (n >= r) {
            try {
                System.out.println("Factorial:");
                timeInitial = System.currentTimeMillis();
                System.out.println("\t" + n + "_C_" + r + " = " + combFactorial(n, r));
                printTime(System.currentTimeMillis(), timeInitial);
            }catch (ArithmeticException error) {
                System.out.println("ArithmeticException: probably division by zero");
            }

//            try {
//                System.out.println("Recursive Pascal's:");
//                timeInitial = System.currentTimeMillis();
//                System.out.println("\t" + n + "_C_" + r + " = " + combRecursivePascal(n, r));
//                printTime(System.currentTimeMillis(), timeInitial);
//            }catch (StackOverflowError error) {
//                System.out.println("StackOverflowError");
//            }

            System.out.println("Data Structure Pascal's:");
            timeInitial = System.currentTimeMillis();
            System.out.println("\t" + n + "_C_" + r + " = " + combDataStructurePascal(n, r));
            printTime(System.currentTimeMillis(), timeInitial);


            try {
                System.out.println("FactorialSimplify:");
                timeInitial = System.currentTimeMillis();
                System.out.println("\t" + n + "_C_" + r + " = " + combFactorialSimplify(n, r));
                printTime(System.currentTimeMillis(), timeInitial);
            }catch (ArithmeticException error) {
                System.out.println("ArithmeticException: probably division by zero");
            }
        }else {
            System.out.println("Error: " + n + " is not greater than or equal to " + r);
        }
    }

    public static int factorial(int k) {
        int result = 1;
        for (int i = 1; i <= k; i++) {
            result *= i;
        }
        return result;
    }

    public static int combFactorial(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n-r));
    }

    public static int combRecursivePascal(int n, int r) {
        if (r == 0 || n-r == 0) return 1;
        if (r == 1 || n-r == 1) return n;
        return combRecursivePascal(n-1, r-1) + combRecursivePascal(n-1, r);
    }

    public static int getIndex(int n, int r) {
        return (int) ((n+1)*n/2.0 + r);
//        int total = 0;
//        for (int row = 0; row < n; row++) {
//            total += row+1;
//        }
//        return total+r;
    }

    public static int combDataStructurePascal(int n, int r) {
        int[] pascal = new int[getIndex(n, r) + 1];
        int index;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                index = getIndex(i, j);
                if (index > pascal.length-1) break;
//                System.out.println(i + "_C_" + j + " = " + index);
                if (j == 0 || i-j == 0) {
                    pascal[index] = 1;
                }else if (j == 1 || i-j == 1) {
                    pascal[index] = i;
                }else{
//                    System.out.println(i + "_C_" + j + " = " + pascal[getIndex(i-1, j-1)] + " + " + pascal[getIndex(i-1, j)]);
                    pascal[index] = pascal[getIndex(i-1, j-1)] + pascal[getIndex(i-1, j)];
                }
            }
        }
//        System.out.println(Arrays.toString(pascal));
        return pascal[getIndex(n, r)];
    }

    public static int combFactorialSimplify(int n, int r) {
        int result = 1;
        if (r > (n-r)) {
            for (int i = r+1; i <= n; i++) {
                result *= i;
            }
            for (int i = 1; i <= n-r; i++) {
                result /= i;
            }
        }else {
            for (int i = n-r+1; i <= n; i++) {
                result *= i;
            }
            for (int i = 1; i <= r; i++) {
                result /= i;
            }
        }
        return result;
    }
}
