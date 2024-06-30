package com.example.lab1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.lab1.R;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class VisualizacionFragment extends Fragment {

    private TextView tvAutor, tvTitulo, tvTecnica, tvCategoria, tvDescripcion, tvAno;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualizacion, container, false);

        tvAutor = view.findViewById(R.id.tvAutor);
        tvTitulo = view.findViewById(R.id.tvTitulo);
        tvTecnica = view.findViewById(R.id.tvTecnica);
        tvCategoria = view.findViewById(R.id.tvCategoria);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        tvAno = view.findViewById(R.id.tvAno);

        cargarDatos();

        return view;
    }

    private void cargarDatos() {
        String filename = "ultima_pintura.txt";
        try (FileInputStream fis = getContext().openFileInput(filename)) {
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[100];
            StringBuilder sb = new StringBuilder();
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                sb.append(inputBuffer, 0, charRead);
            }

            String[] datos = sb.toString().split(",");
            if (datos.length == 6) {
                tvAutor.setText("Autor: " + datos[0]);
                tvTitulo.setText("Título: " + datos[1]);
                tvTecnica.setText("Técnica: " + datos[2]);
                tvCategoria.setText("Categoría: " + datos[3]);
                tvDescripcion.setText("Descripción: " + datos[4]);
                tvAno.setText("Año: " + datos[5]);
            } else {
                Toast.makeText(getContext(), "No hay datos disponibles", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
