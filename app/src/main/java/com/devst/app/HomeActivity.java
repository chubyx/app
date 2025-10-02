package com.devst.app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;



public class HomeActivity extends AppCompatActivity {

    private String emailusuario = "";
    private TextView tvBienvenida;

    //Funcion para capturar resultados para el perfil
    private final ActivityResultLauncher<Intent> editarPerfilLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
               if (result.getResultCode() == RESULT_OK && result.getData() != null){
                   String nombre = result.getData().getStringExtra("nombre-editado");
                   if(nombre != null){
                       tvBienvenida.setText("Hola, " + nombre);
                   }
               }
            });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        //Referencias
        tvBienvenida = findViewById(R.id.tvBienvenida);
        Button btnIrPerfil = findViewById(R.id.btnIrPerfil);
        Button btnAbrirWeb = findViewById(R.id.btnAbrirWeb);
        Button btnEnviarCorreo = findViewById(R.id.btnEnviarCorreo);
        Button btnCompartir = findViewById(R.id.btnCompartir);

        //Recibir datos desde el login
        emailusuario = getIntent().getStringExtra("email_usuario");
        if(emailusuario == null) emailusuario = "";
        tvBienvenida.setText("Bienvenida: " + emailusuario);

        //evento explicita iniciatr vista perfil
        btnIrPerfil.setOnClickListener(View ->{
            Intent perfil = new Intent(HomeActivity.this, PerfilActivity.class);
            perfil.putExtra("email_usuario", emailusuario);
            editarPerfilLauncher.launch(perfil);
        });

        //Evento Implicitos para abrir una pagina web
        btnAbrirWeb.setOnClickListener(View ->{
            Uri url = Uri.parse("http://www.santotomas.cl");
            Intent viewWeb = new Intent(Intent.ACTION_VIEW, url);
            startActivity(viewWeb);
        });

        btnEnviarCorreo.setOnClickListener(view -> {
            Intent correo = new Intent(Intent.ACTION_SENDTO);
            correo.setData(Uri.parse("mailto:"));
            correo.putExtra(Intent.EXTRA_EMAIL, new String[]{emailusuario});
            correo.putExtra((Intent.EXTRA_SUBJECT),"Prueba de correo");
            correo.putExtra(Intent.EXTRA_TEXT, "Hola mundo desde el btnEnviarCorreo");
            startActivity(Intent.createChooser(correo, "Enviar correo a: "));
        });

        //Evento implicito compartir
        btnCompartir.setOnClickListener(view -> {
        Intent compartir = new Intent(Intent.ACTION_SEND);
        compartir.setType("text/plain");
        compartir.putExtra(Intent.EXTRA_TEXT, "HOLA MUNDO DESDE ANDROID 🫡");
        startActivity(Intent.createChooser(compartir,"Compartiendo....."));
        });

    }

}