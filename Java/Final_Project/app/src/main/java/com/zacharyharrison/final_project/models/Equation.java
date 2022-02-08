package com.zacharyharrison.final_project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Equation {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String expression;
    // Strings in between ⨂'s are operators. Otherwise, it is an operand.

    @ColumnInfo
    public String answer;

    @ColumnInfo(name = "created_at")
    public long createdAt;
}
