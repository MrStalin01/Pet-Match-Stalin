package com.rodgar00.petmatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileDog extends Activity {


    ImageView imageView;
    TextView nombre, duenyo, categoria, refugio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_animal);

        imageView = findViewById(R.id.TvDogPicture);
        nombre = findViewById(R.id.tvDogName);
        duenyo = findViewById(R.id.tvDogDuenio);
        categoria = findViewById(R.id.tvDogCategoria);
        refugio = findViewById(R.id.tvDogRefugio);

        // Recibir datos
        Intent intent = getIntent();

        String nombreDog = intent.getStringExtra("nombre");
        String duenyoDog = intent.getStringExtra("duenyo");
        String categoriaDog = intent.getStringExtra("categoria");
        String refugioDog = intent.getStringExtra("refugio");
        String imagenDog = intent.getStringExtra("imagen");

        // Asignar datos
        nombre.setText(nombreDog);
        duenyo.setText("Dueño: " + duenyoDog);
        categoria.setText("Categoría: " + categoriaDog);
        refugio.setText("Refugio: " + refugioDog);

        Glide.with(this)
                .load(imagenDog)
                .into(imageView);
    }
}
