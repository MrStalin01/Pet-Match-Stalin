package com.rodgar00.petmatch;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button loginButton = findViewById(R.id.LoginButton);
        TextView loginTVInvitado = findViewById(R.id.LoginTVInvitado);
        TextView loginTVRegister = findViewById(R.id.LoginTVRegister);
        TextInputLayout loginTILUser = findViewById(R.id.LoginTILuserName);
        TextInputLayout loginTILPassword = findViewById(R.id.LoginTILpassword);

        loginTILPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();

                if (password.isEmpty()) {
                    loginTILPassword.setErrorEnabled(true);
                    loginTILPassword.setError("Tu contraseña está vacía");
                } else {
                    loginTILPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        loginTILUser.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String username = s.toString();
                if (username.isEmpty()) {
                    loginTILUser.setErrorEnabled(true);
                    loginTILUser.setError("Tu nombre está vacío");
                } else {
                    loginTILUser.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        FormUtils formUtils = new FormUtils();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String savedEmail = sharedPref.getString("email", "");
        String hashedPassword = sharedPref.getString("password", "");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canContinue = true;

                if (formUtils.isTILEmpty(loginTILUser)) {
                    loginTILUser.setErrorEnabled(true);
                    loginTILUser.setError("Necesitas acceder con un nombre de usuario.");
                    canContinue = false;
                }

                String inputText = loginTILUser.getEditText().getText().toString();

                if (!formUtils.checkUser(inputText, savedEmail)) {
                    loginTILUser.setErrorEnabled(true);
                    loginTILUser.setError("Usuario incorrecto.");
                    canContinue = false;
                }

                if (formUtils.isTILEmpty(loginTILPassword)) {
                    loginTILPassword.setErrorEnabled(true);
                    loginTILPassword.setError("La contraseña está vacía.");
                    canContinue = false;
                } else if (!formUtils.checkPassword(formUtils.getTILText(loginTILPassword), hashedPassword)) {
                    loginTILPassword.setErrorEnabled(true);
                    loginTILPassword.setError("La contraseña es incorrecta.");
                    canContinue = false;
                }

                if (canContinue) {
                    Toast.makeText(Login.this, "Bienvenido " + formUtils.getTILText(loginTILUser), Toast.LENGTH_SHORT).show();
                    Intent intentMain = new Intent(Login.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("nombre", String.valueOf(loginTILUser.getEditText().getText()));
                    intentMain.putExtras(bundle);
                    startActivity(intentMain);
                }
            }
        });

        loginTVInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(Login.this, MainActivity.class);
                startActivity(intentMain);
                finish();
            }
        });

        loginTVRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(Login.this, Register.class);
                startActivity(intentRegister);
            }
        });
    }
}