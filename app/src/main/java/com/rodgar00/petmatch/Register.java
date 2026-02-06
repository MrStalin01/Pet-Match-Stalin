package com.rodgar00.petmatch;

import android.app.Activity;

public class Register extends Activity {

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    FormUtils formUtils = new FormUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputLayout registerTILemail = findViewById(R.id.RegisterTILEmail);
        TextInputLayout registerTILpassword = findViewById(R.id.RegisterTILPassword);
        TextInputLayout registerTILpasswordDoubleCheck = findViewById(R.id.RegisterTILPasswordConfirm);
        Button registerButton = findViewById(R.id.RegisterButton);
        TextView registerTVLogin = findViewById(R.id.RegisterTVLogin);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();


        registerTILemail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!formUtils.isEmailCorrect(registerTILemail)) {
                    registerTILemail.setErrorEnabled(true);
                    registerTILemail.setError("Tu email es inválido");
                } else {
                    registerTILemail.setErrorEnabled(false);
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        registerTILpassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (formUtils.isTILEmpty(registerTILpassword)) {
                    registerTILpassword.setErrorEnabled(true);
                    registerTILpassword.setError("Tu contraseña está vacía");
                } else {
                    registerTILpassword.setErrorEnabled(false);
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        registerButton.setOnClickListener(v -> {
            boolean canContinue = true;


            if (!formUtils.isEmailCorrect(registerTILemail)) {
                registerTILemail.setErrorEnabled(true);
                registerTILemail.setError("Tu email está mal escrito");
                canContinue = false;
            }

            if (!formUtils.arePasswordsTheSame(registerTILpassword, registerTILpasswordDoubleCheck)) {
                registerTILpasswordDoubleCheck.setErrorEnabled(true);
                registerTILpasswordDoubleCheck.setError("La contraseña no es válida");

                if (formUtils.isTILEmpty(registerTILpassword) && formUtils.isTILEmpty(registerTILpasswordDoubleCheck)) {
                    Toast.makeText(Register.this, "No has escrito nada en el campo contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Register.this, "Las dos contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                canContinue = false;
            }

            if (canContinue) {
                editor.putString("email", formUtils.getTILText(registerTILemail));
                editor.putString("password", formUtils.generateHashedPassword(formUtils.getTILText(registerTILpassword)));
                editor.apply();

                Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });

        registerTVLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        });
    }
}
