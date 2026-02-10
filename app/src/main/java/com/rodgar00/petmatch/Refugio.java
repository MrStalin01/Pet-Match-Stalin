package com.rodgar00.petmatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Refugio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugio);

        final DrawerLayout drawerLayout = findViewById(R.id.main);
        ImageView menuHamburguesa = findViewById(R.id.menuHamburguesa);

        menuHamburguesa.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });

        CardView cardRefugio = findViewById(R.id.cardrefugio1);
        final LinearLayout layoutDetalles = findViewById(R.id.layoutDetalles);

        cardRefugio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDetalles.getVisibility() == View.GONE) {
                    layoutDetalles.setVisibility(View.VISIBLE);
                } else {
                    layoutDetalles.setVisibility(View.GONE);
                }
            }
        });
        CardView cardRefugio2 = findViewById(R.id.cardrefugio2);
        final LinearLayout layoutDetalles2 = findViewById(R.id.layoutDetalles2);

        cardRefugio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDetalles2.getVisibility() == View.GONE) {
                    layoutDetalles2.setVisibility(View.VISIBLE);
                } else {
                    layoutDetalles2.setVisibility(View.GONE);
                }
            }
        });
        CardView cardRefugio3 = findViewById(R.id.cardrefugio3);
        final LinearLayout layoutDetalles3 = findViewById(R.id.layoutDetalles3);

        cardRefugio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDetalles3.getVisibility() == View.GONE) {
                    layoutDetalles3.setVisibility(View.VISIBLE);
                } else {
                    layoutDetalles3.setVisibility(View.GONE);
                }
            }
        });
        CardView cardRefugio4 = findViewById(R.id.cardrefugio4);
        final LinearLayout layoutDetalles4 = findViewById(R.id.layoutDetalles4);

        cardRefugio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDetalles4.getVisibility() == View.GONE)  { //Gone se encarga de no ocupar espacio
                    layoutDetalles4.setVisibility(View.VISIBLE); //Aqui se encarga que cuando haga CLICK sea visible
                } else {
                    layoutDetalles4.setVisibility(View.GONE);
                }
            }
        });

    }
}