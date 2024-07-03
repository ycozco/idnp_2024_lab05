package com.example.lab1.dataBase;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PinturaDao {
    @Insert
    void insert(Pintura pintura); // Asegúrate de que esté anotado con @Insert

    @Query("SELECT Pintura.titulo, Autor.nombre, Autor.apellido, Pintura.tecnica, Pintura.categoria, Pintura.descripcion, Pintura.anio, Pintura.imagen " +
            "FROM Pintura " +
            "INNER JOIN Autor ON Pintura.autorId = Autor.id")
    List<PinturaAutor> getPinturasConAutor();
}
