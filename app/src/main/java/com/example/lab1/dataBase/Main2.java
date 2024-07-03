package com.example.lab1.dataBase;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.R;

import java.util.List;

public class Main2 extends AppCompatActivity {
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = AppDataBase.getDatabase(this);

        // Insertar datos (si es necesario)
        new Thread(new Runnable() {
            @Override
            public void run() {
                Sala sala = new Sala();
                sala.nombre = "Sala 1";
                sala.temaDescripcion = "Descripción de la sala 1";
                db.salaDao().insert(sala);

                Autor autor = new Autor();
                autor.nombre = "Pablo";
                autor.apellido = "Picasso";
                db.autorDao().insert(autor);

                Pintura pintura = new Pintura();
                pintura.titulo = "Guernica";
                pintura.autorId = autor.id;
                pintura.tecnica = "Óleo sobre lienzo";
                pintura.categoria = "Cubismo";
                pintura.descripcion = "Descripción de Guernica";
                pintura.anio = 1937;
                pintura.imagen = "http://example.com/guernica";
                pintura.salaId = sala.id;
                db.pinturaDao().insert(pintura);

                // Log para verificar la inserción de datos
                Log.d(TAG, "Datos insertados correctamente");
            }
        }).start();

        // Obtener datos
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PinturaAutor> pinturasConAutor = db.pinturaDao().getPinturasConAutor();

                // Hacer algo con los datos obtenidos
                for (PinturaAutor pca : pinturasConAutor) {
                    Log.d(TAG,"Título: " + pca.titulo);
                    Log.d(TAG,"Autor: " + pca.nombre + " " + pca.apellido);
                    Log.d(TAG,"Técnica: " + pca.tecnica);
                    Log.d(TAG,"Categoría: " + pca.categoria);
                    Log.d(TAG,"Descripción: " + pca.descripcion);
                    Log.d(TAG,"Año: " + pca.anio);
                    Log.d(TAG,"Enlace: " + pca.imagen);
                }
            }
        }).start();
    }
}
