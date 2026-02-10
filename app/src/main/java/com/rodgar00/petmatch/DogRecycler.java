package com.rodgar00.petmatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DogRecycler extends RecyclerView.Adapter<DogRecycler.DogViewHolder> {

    private Context context;
    private ArrayList<DogModel> dogModels;

    public DogRecycler(Context context, ArrayList<DogModel> dogModels) {
        this.context = context;
        this.dogModels = dogModels;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.tarjeta, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        DogModel dog = dogModels.get(position);

        holder.nombreText.setText(dog.getNombre());

        // Mostrar dueño si existe
        if (dog.getDuenyo() != null && !dog.getDuenyo().isEmpty()) {
            holder.duenyoText.setText("Dueño: " + dog.getDuenyo());
            holder.duenyoText.setVisibility(View.VISIBLE);
        } else {
            holder.duenyoText.setVisibility(View.GONE);
        }

        holder.categoriaText.setText("Categoría: " + dog.getCategoria());
        holder.refugioText.setText("Refugio: " + dog.getEsRefugio());

        Glide.with(context)
                .load(dog.getImagen())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.dogImage);
    }

    @Override
    public int getItemCount() {
        return dogModels.size();
    }

    static class DogViewHolder extends RecyclerView.ViewHolder {

        TextView nombreText, duenyoText, categoriaText, refugioText;
        ImageView dogImage;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreText = itemView.findViewById(R.id.textoNombre);
            duenyoText = itemView.findViewById(R.id.textoDuenyo);
            categoriaText = itemView.findViewById(R.id.textoCategoria);
            refugioText = itemView.findViewById(R.id.textoRefugio);
            dogImage = itemView.findViewById(R.id.imagenTarjetaDog);
        }
    }
}
