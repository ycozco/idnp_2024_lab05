package com.example.lab1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab1.R;
import com.example.lab1.models.Cuadro;

import java.util.List;

public class CuadrosAdapter extends RecyclerView.Adapter<CuadrosAdapter.ViewHolder> {

    private Context context;
    private List<Cuadro> listaCuadros;

    public CuadrosAdapter(Context context, List<Cuadro> listaCuadros) {
        this.context = context;
        this.listaCuadros = listaCuadros;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuadro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cuadro cuadro = listaCuadros.get(position);
        holder.nombreTextView.setText(cuadro.getNombre());
        holder.autorTextView.setText(cuadro.getAutor());
        holder.imagenImageView.setImageResource(cuadro.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return listaCuadros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreTextView;
        public TextView autorTextView;
        public ImageView imagenImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreCuadro);
            autorTextView = itemView.findViewById(R.id.autorCuadro);
            imagenImageView = itemView.findViewById(R.id.imagenCuadro);
        }
    }
}
