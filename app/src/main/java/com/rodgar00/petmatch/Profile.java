package com.rodgar00.petmatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class Profile extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivty_profile);

        ImageView logoApp = findViewById(R.id.logoApp);

        logoApp.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
        });

    }
}
