package com.example.lab1.dataBase;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SalaDao {
    @Insert
    void insert(Sala sala);

    @Query("SELECT * FROM Sala")
    List<Sala> getAll();
}
