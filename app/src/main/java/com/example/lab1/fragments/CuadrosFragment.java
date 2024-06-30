package com.example.lab1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.Navigation;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuadros, container, false);
        recyclerViewCuadros = view.findViewById(R.id.recyclerViewCuadros);
        recyclerViewCuadros.setLayoutManager(new LinearLayoutManager(getContext()));
        inicializarListaDeCuadros();
        adapter = new CuadrosAdapter(getContext(), listaDeCuadros);
        recyclerViewCuadros.setAdapter(adapter);

        Button btnRegistrarNuevoCuadro = view.findViewById(R.id.btnRegistrarNuevoCuadro);
        btnRegistrarNuevoCuadro.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_cuadrosFragment_to_registroFragment);
        });

        return view;
    }

    private void inicializarListaDeCuadros() {
        listaDeCuadros = new ArrayList<>();
        // Aquí se añaden los cuadros manualmente, pero podrías cargarlos desde una base de datos o servicio web
        listaDeCuadros.add(new Cuadro("Mona Lisa", "Leonardo da Vinci", R.drawable.monalisa));
        listaDeCuadros.add(new Cuadro("La noche estrellada", "Vincent Van Gogh", R.drawable.monalisa));
    }
}
