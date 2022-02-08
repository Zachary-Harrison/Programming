package com.zacharyharrison.final_project.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.zacharyharrison.final_project.models.Equation;


@Database(entities = {Equation.class}, version = 1, exportSchema = false)
public abstract class EquationsDatabase extends RoomDatabase {
    public abstract EquationsDao getEquationsDao();
}
