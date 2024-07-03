package com.example.lab1.dataBase;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity
    public class Sala {
        @PrimaryKey(autoGenerate = true)
        public int id;
        public String nombre;
        public String temaDescripcion;
    }