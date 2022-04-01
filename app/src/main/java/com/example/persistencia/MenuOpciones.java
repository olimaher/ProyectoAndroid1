package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuOpciones extends AppCompatActivity {

    Button registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);


        registros = findViewById(R.id.btnRegistros);

        registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intRegistros =new Intent(MenuOpciones.this,RegistroUsuarios.class);
                startActivity(intRegistros);
            }
        });
    }
}