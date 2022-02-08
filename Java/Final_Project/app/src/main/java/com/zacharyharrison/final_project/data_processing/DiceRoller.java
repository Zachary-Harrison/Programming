package com.zacharyharrison.final_project.data_processing;

import static com.zacharyharrison.final_project.data_processing.ExpressionToDiceConverter.expressionToDice;

import com.zacharyharrison.final_project.models.Dice;

import java.util.Arrays;

public class DiceRoller {
    private Dice dice;
    private double[] diceList;
    private boolean exceptionEncountered = false;

    public DiceRoller(Dice dice) {
        this.dice = dice;
        diceList = new double[dice.numOfDice];
    }

    public DiceRoller(String expression) {
        try {
            this.dice = expressionToDice(expression);
            diceList = new double[dice.numOfDice];
        }catch (Exception err) {
            exceptionEncountered = true;
        }
    }

    public String roll() {
        if (exceptionEncountered)
            return "Error";
        for (int i = 0; i < dice.numOfDice; i++) {
            diceList[i] = (int) (Math.random() * dice.numOfSides) + 1;
        }
        // sort list from lowest to highest
        Arrays.sort(diceList);
        double[] totalArray = Arrays.copyOfRange(diceList, dice.dropLow, dice.numOfDice - dice.dropHigh);
        double total = 0;
        for (double num: totalArray) {
            total += num;
        }
        return ExpressionEvaluator.solve(total + dice.bonus);
    }
}
