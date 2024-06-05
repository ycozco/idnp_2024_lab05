package com.example.lab1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab1.R;
import com.example.lab1.adapters.CuadrosAdapter;
import com.example.lab1.models.Cuadro;

import java.util.ArrayList;
import java.util.List;

public class CuadrosFragment extends Fragment {

    private RecyclerView recyclerViewCuadros;
    private CuadrosAdapter adapter;
    private List<Cuadro> listaDeCuadros;

    public CuadrosFragment() {
        // Constructor vacío requerido
    }

    public static CuadrosFragment newInstance() {
        return new CuadrosFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuadros, container, false);
        recyclerViewCuadros = view.findViewById(R.id.recyclerViewCuadros);
        recyclerViewCuadros.setLayoutManager(new LinearLayoutManager(getContext()));
        inicializarListaDeCuadros();
        adapter = new CuadrosAdapter(getContext(), listaDeCuadros);
        recyclerViewCuadros.setAdapter(adapter);
        return view;
    }

    private void inicializarListaDeCuadros() {
        listaDeCuadros = new ArrayList<>();
        // Aquí se añaden los cuadros manualmente, pero podrías cargarlos desde una base de datos o servicio web
        listaDeCuadros.add(new Cuadro("Mona Lisa", "Leonardo da Vinci", R.drawable.monalisa));
        listaDeCuadros.add(new Cuadro("La noche estrellada", "Vincent Van Gogh", R.drawable.monalisa));
    }
}
