package com.rodgar00.petmatch;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText searchBar;
    RecyclerView recyclerView;
    DogRecycler adapter;
    ArrayList<DogModel> dogList = new ArrayList<>();

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuHamburguesa, closeMenu;
    ApiInterface api;

    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.searchBar);
        recyclerView = findViewById(R.id.characterRecycler);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.navView);
        menuHamburguesa = findViewById(R.id.menuHamburguesa);

        closeMenu = navigationView.getHeaderView(0).findViewById(R.id.Close);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new DogRecycler(this, dogList);
        recyclerView.setAdapter(adapter);

        api = ApiClient.getClient().create(ApiInterface.class);

        btn1.setOnClickListener(v -> seleccionarBoton(btn1, "husky"));
        btn2.setOnClickListener(v -> seleccionarBoton(btn2, "labrador"));
        btn3.setOnClickListener(v -> seleccionarBoton(btn3, "beagle"));
        btn4.setOnClickListener(v -> seleccionarBoton(btn4, "pug"));

        seleccionarBoton(btn1, "husky");

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String breed = s.toString().trim().toLowerCase();
                if (breed.length() >= 3) {
                    buscarPerro(breed);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        menuHamburguesa.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.END));

        if (closeMenu != null) {
            closeMenu.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.END));
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_profile) {
                Toast.makeText(this, "Mi Perfil", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_pets) {
                Toast.makeText(this, "Mascotas", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_exit) {
                Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
            }
            drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });
    }

    private void seleccionarBoton(Button boton, String raza) {
        desmarcarTodos();
        boton.setSelected(true);
        buscarPerro(raza);
    }

    private void desmarcarTodos() {
        btn1.setSelected(false);
        btn2.setSelected(false);
        btn3.setSelected(false);
        btn4.setSelected(false);
    }

    private void buscarPerro(String breed) {
        int NUM_IMAGENES = 20;
        api.getDogsByBreed(breed, NUM_IMAGENES).enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dogList.clear();
                    for (String url : response.body().getImageUrls()) {
                        dogList.add(new DogModel(breed, url));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    dogList.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}