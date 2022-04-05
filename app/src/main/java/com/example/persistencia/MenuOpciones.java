package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MenuOpciones extends AppCompatActivity {

    Button registros;

    TextView user, clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        registros = findViewById(R.id.btnIrRegistros);

        Button login = findViewById(R.id.btnLogin);

        user = findViewById(R.id.txtUsuario);
        clave = findViewById(R.id.txtContraseña);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdminDataBase dataBase = new AdminDataBase(getApplicationContext(), "Universidad", 1);

                SQLiteDatabase db = dataBase.getWritableDatabase();

                String usuario = user.getText().toString();
                String claveUsuario = clave.getText().toString();

                if (!usuario.isEmpty()) {
                    Cursor fila = db.rawQuery("select * from usuarios where username = '" + usuario + "'", null);
                    if (fila.moveToFirst()) {

                        if (usuario.equals(fila.getString(1)) && claveUsuario.equals(fila.getString(2))) {

                            Intent next = new Intent(MenuOpciones.this, MenuUsuarioRegistrado.class);
                            startActivity(next);
                        } else {
                            Toast.makeText(getApplicationContext(), "la contraseña es incorrecta", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        user.setText("");
                        clave.setText((""));
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese un usuario para la validación", Toast.LENGTH_LONG).show();
                }

            }
        });
        registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irRegistros = new Intent(MenuOpciones.this, RegistroUsuarios.class);
                startActivity(irRegistros);
            }
        });
    }
}