package com.zacharyharrison.final_project.models;

public class ButtonData {
    public String text;
    public int row;
    public int col;
    public int colSpan;
    public ButtonType type;
    public enum ButtonType {
        NUMBER,
        OPERATOR,
        EVALUATE,
        CLEAR,
        DIE,
        GRAPH,
        ERASE
    }

    public ButtonData(String text, int row, int col, int colSpan, ButtonType type)
    {
        this.text = text;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
        this.type = type;
    }
}
