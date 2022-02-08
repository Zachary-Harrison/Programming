package com.zacharyharrison.final_project.data_processing;

import com.zacharyharrison.final_project.models.Dice;

import java.util.Arrays;

public class ExpressionToDiceConverter {
    public static Dice expressionToDice(String expression) {
        // expression is of the form ,A,dB,HC,LD,,X,E, where "A", "B", "C", "D", and "E" are numbers
        // and X is an operator and "," is the delimiter
        Dice dice = new Dice();
        String[] diceInfo = expression.split(",");
        if (diceInfo[0].equals("")) {
            dice.numOfDice = 1;
        } else {
            dice.numOfDice = Integer.parseInt(diceInfo[0]);
        }
        for (int i = 1; i < diceInfo.length; i++) {
            if (diceInfo[i].equals("")) continue;
            if (!dice.bonus.equals("")) {
                dice.bonus += diceInfo[i] + ",";
            } else if (diceInfo[i].contains("d")) {
                dice.numOfSides = Integer.parseInt(diceInfo[i].replace("d", ""));
            } else if (diceInfo[i].contains("H")) {
                if (i == diceInfo.length - 1) {
                    dice.dropLow = dice.numOfDice - 1;
                }else {
                    dice.dropLow = dice.numOfDice - Integer.parseInt(diceInfo[i + 1].replace("H", ""));
                    i++;
                }
            } else if (diceInfo[i].contains("L")) {
                if (i == diceInfo.length - 1) {
                    dice.dropHigh = dice.numOfDice - 1;
                }else {
                    dice.dropHigh = dice.numOfDice - Integer.parseInt(diceInfo[i + 1].replace("L", ""));
                    i++;
                }
            } else if (diceInfo[i].contains("+") || diceInfo[i].contains("-") ||
                    diceInfo[i].contains("×") || diceInfo[i].contains("÷") || diceInfo[i].contains("^")) {
                dice.bonus = "," + diceInfo[i] + ",";
            }
        }
        return dice;
    }
}
