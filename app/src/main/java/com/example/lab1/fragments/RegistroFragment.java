package com.example.lab1.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.lab1.R;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegistroFragment extends Fragment {

    private EditText edtAutor, edtTitulo, edtTecnica, edtCategoria, edtDescripcion, edtAno;
    private Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        edtAutor = view.findViewById(R.id.edtAutor);
        edtTitulo = view.findViewById(R.id.edtTitulo);
        edtTecnica = view.findViewById(R.id.edtTecnica);
        edtCategoria = view.findViewById(R.id.edtCategoria);
        edtDescripcion = view.findViewById(R.id.edtDescripcion);
        edtAno = view.findViewById(R.id.edtAno);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String autor = edtAutor.getText().toString();
            String titulo = edtTitulo.getText().toString();
            String tecnica = edtTecnica.getText().toString();
            String categoria = edtCategoria.getText().toString();
            String descripcion = edtDescripcion.getText().toString();
            String ano = edtAno.getText().toString();

            if (TextUtils.isEmpty(autor) || TextUtils.isEmpty(titulo) || TextUtils.isEmpty(tecnica)
                    || TextUtils.isEmpty(categoria) || TextUtils.isEmpty(descripcion) || TextUtils.isEmpty(ano)) {
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            guardarDatos(autor, titulo, tecnica, categoria, descripcion, ano);
        });

        return view;
    }

    private void guardarDatos(String autor, String titulo, String tecnica, String categoria, String descripcion, String ano) {
        String filename = "ultima_pintura.txt";
        String fileContents = autor + "," + titulo + "," + tecnica + "," + categoria + "," + descripcion + "," + ano;

        try (FileOutputStream fos = getContext().openFileOutput(filename, getContext().MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
            Toast.makeText(getContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
