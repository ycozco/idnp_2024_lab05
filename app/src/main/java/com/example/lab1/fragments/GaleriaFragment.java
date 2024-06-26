package com.example.lab1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab1.R;
import com.example.lab1.views.GalleryView;

public class GaleriaFragment extends Fragment {

    private static final String ARG_AREA = "area";
    private String area;

    public GaleriaFragment() {
        // Constructor vacío requerido
    }

    public static GaleriaFragment newInstance(String area, String param2) {
        GaleriaFragment fragment = new GaleriaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_AREA, area);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            area = getArguments().getString(ARG_AREA);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MapaFragment22222222222", "Area clicked: " + area); // Añadir este log
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        TextView textView = view.findViewById(R.id.galeriaTextView);
        textView.setText("Galería seleccionada: " + area);

        GalleryView galleryView = view.findViewById(R.id.galleryView);
        galleryView.setGalleryName(area);

        return view;
    }
}
