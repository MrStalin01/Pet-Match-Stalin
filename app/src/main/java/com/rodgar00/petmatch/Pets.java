package com.rodgar00.petmatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class Pets extends Activity {
    ImageView closeMenu;
    NavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);

        DrawerLayout drawerLayout = findViewById(R.id.main);

        ImageView menuHamburguesa = findViewById(R.id.menuHamburguesa);
        closeMenu = findViewById(R.id.Close);
        navigationView = findViewById(R.id.navView);

        menuHamburguesa.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });

        closeMenu.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END);
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_profile) {
                startActivity(new Intent(Pets.this, Profile.class));
            } else if (id == R.id.nav_pets) {
                startActivity(new Intent(Pets.this, Pets.class));
            } else if (id == R.id.nav_refugio) {
                startActivity(new Intent(Pets.this, Refugio.class));
            } else if (id == R.id.nav_exit) {
                startActivity(new Intent(Pets.this, Usuarios.class));
            }

            drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });
    }

}


