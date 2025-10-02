package com.devst.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Button btnAcercaDe = findViewById(R.id.btnAcercaDe);

        // Acción del botón "Acerca de"
        btnAcercaDe.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, AcercaDeActivity.class);
            startActivity(intent);
        });


    }
}