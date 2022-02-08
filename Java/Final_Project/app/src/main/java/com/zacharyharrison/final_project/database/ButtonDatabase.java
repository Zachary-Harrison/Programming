package com.zacharyharrison.final_project.database;

import com.zacharyharrison.final_project.models.ButtonData;

import java.util.ArrayList;

public class ButtonDatabase {
    private final ArrayList<ButtonData> buttonData = new ArrayList<ButtonData>() {
        {
            add(new ButtonData("H", 0, 0, 1, ButtonData.ButtonType.DIE));
            add(new ButtonData("XdY", 0, 1, 1, ButtonData.ButtonType.DIE));
            add(new ButtonData("d4", 0, 2, 1, ButtonData.ButtonType.DIE));
            add(new ButtonData("d6", 0, 3, 1, ButtonData.ButtonType.DIE));
            add(new ButtonData("d8", 0, 4, 1, ButtonData.ButtonType.DIE));

            add(new ButtonData("L", 1, 0, 1, ButtonData.ButtonType.DIE));
            add(new ButtonData("d10", 1, 1, 1, ButtonData.ButtonType.DIE));
            add(new ButtonData("d12", 1, 2, 1, ButtonData.ButtonType.DIE));
            add(new ButtonData("d20", 1, 3, 1, ButtonData.ButtonType.DIE));
            add(new ButtonData("d100", 1, 4, 1, ButtonData.ButtonType.DIE));

            add(new ButtonData("7", 2, 0, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("8", 2, 1, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("9", 2, 2, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("×", 2, 3, 1, ButtonData.ButtonType.OPERATOR));
            add(new ButtonData("ERASE", 2, 4, 1, ButtonData.ButtonType.ERASE));

            add(new ButtonData("4", 3, 0, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("5", 3, 1, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("6", 3, 2, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("÷", 3, 3, 1, ButtonData.ButtonType.OPERATOR));
            add(new ButtonData("Clear", 3, 4, 1, ButtonData.ButtonType.CLEAR));

            add(new ButtonData("1", 4, 0, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("2", 4, 1, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("3", 4, 2, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("+", 4, 3, 1, ButtonData.ButtonType.OPERATOR));
            add(new ButtonData("Graph", 4, 4, 1, ButtonData.ButtonType.GRAPH));

            add(new ButtonData(".", 5, 0, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("0", 5, 1, 1, ButtonData.ButtonType.NUMBER));
            add(new ButtonData("^", 5, 2, 1, ButtonData.ButtonType.OPERATOR));
            add(new ButtonData("-", 5, 3, 1, ButtonData.ButtonType.OPERATOR));
            add(new ButtonData("Roll", 5, 4, 1, ButtonData.ButtonType.EVALUATE));
        }
    };
    public ArrayList<ButtonData> getButtonData() {
        return buttonData;
    }
}
