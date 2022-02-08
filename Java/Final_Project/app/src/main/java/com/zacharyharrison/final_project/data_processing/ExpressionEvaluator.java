package com.zacharyharrison.final_project.data_processing;

import java.util.Arrays;

public class ExpressionEvaluator {

    public static String solve(String exp) {
        String[] expression;
        double answer = 0;
        if (!exp.equals("")) {
            if (exp.contains(",")) {
                expression = exp.split(",");
            }else {
                expression = new String[] {exp};
            }
            answer = Double.parseDouble(expression[0]);
        }else {
            expression = new String[]{};
        }
        if (expression.length == 1) {
            return String.valueOf(answer);
        }
        if (expression.length < 3) {
            return "Error";
        }
        try {
            // Note: even indices (0, 2, ...) should be numbers
            // Note: odd indices (1, 3, ...) should be operators
            for (int i = 0; i < expression.length-2; i+=2) {
                answer = evaluate(answer, expression[i+1], Double.parseDouble(expression[i+2]));
            }
            return String.valueOf(answer);
        }catch (ArrayIndexOutOfBoundsException | NumberFormatException err) {
            return "Syntax Error";
        } catch (Exception err) {
            return "Error";
        }
    }

    private static double evaluate(double a, String operator, double b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "÷":
                return a / b;
            case "×":
                return a * b;
            case "^":
                return Math.pow(a, b);
            default:
                return Double.NaN;
        }
    }
}
