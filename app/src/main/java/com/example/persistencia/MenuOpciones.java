package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MenuOpciones extends AppCompatActivity {

    Button registros,login;

    TextView user, clave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        registros = findViewById(R.id.btnIrRegistros);
        login = findViewById(R.id.btnLogin);

        user = findViewById(R.id.txtUsuario);
        clave = findViewById(R.id.txtContraseña);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irRegistros = new Intent(MenuOpciones.this,RegistroUsuarios.class);
                startActivity(irRegistros);
            }
        });
    }
}