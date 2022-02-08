package com.zacharyharrison.final_project.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zacharyharrison.final_project.models.Equation;

import java.util.List;
// CRUD:
// Create
// Read
// Update
// Delete


//Database Access Object
@Dao
public interface EquationsDao {
    @Insert
    public long insert(Equation equation);

    @Query("SELECT * FROM equation")
    public List<Equation> getAll();

    @Query("SELECT * FROM equation WHERE id = :id LIMIT 1")
    public Equation findById(long id);

    @Update
    public void update(Equation equation);

    @Delete
    public void delete(Equation equation);
}
