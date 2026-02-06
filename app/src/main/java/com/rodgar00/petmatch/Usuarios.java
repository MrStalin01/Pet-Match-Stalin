package com.rodgar00.petmatch;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class Usuarios extends AppCompatActivity {

    MaterialButton iniciarSesionButton, crearCuentaButton, invitadoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        iniciarSesionButton = findViewById(R.id.IniciarSesionButton);
        crearCuentaButton = findViewById(R.id.CrearCuentaButton);
        invitadoButton = findViewById(R.id.InvitadoButton);

        iniciarSesionButton.setOnClickListener(v -> {
            Intent intent = new Intent(Usuarios.this, Login.class);
            startActivity(intent);
            finish();
        });

        crearCuentaButton.setOnClickListener(v -> {
            Intent intent = new Intent(Usuarios.this, Register.class);
            startActivity(intent);
            finish();
        });

        invitadoButton.setOnClickListener(v -> {
            Intent intent = new Intent(Usuarios.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

}
