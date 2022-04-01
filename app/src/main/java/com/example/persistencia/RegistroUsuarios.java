package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsuarios extends AppCompatActivity {

    EditText documento, nombre;
    Button regresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        documento = findViewById(R.id.txtDocumento);
        nombre = findViewById(R.id.txtNombre);
        regresar = findViewById(R.id.btnRegresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intRegresar = new Intent(RegistroUsuarios.this,MenuOpciones.class);
                startActivity(intRegresar);
            }
        });

    }

    public void registrar(View view){ //Otra forma de configurar un boton.

        AdminDataBase dataBase = new AdminDataBase(getApplicationContext(),"Universidad",1);

        SQLiteDatabase db = dataBase.getWritableDatabase();

        String doc = documento.getText().toString();
        String nom = nombre.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put("doc", doc);
        valores.put("nombre",nom);

        long newRorId = db.insert("estudiantes",null,valores);

        documento.setText("");
        nombre.setText("");

        if (newRorId ==-1){
            Toast.makeText(this,"No se puedo guardar en la base de datos",Toast.LENGTH_LONG).show();
        }
        else   {
            Toast.makeText(this,"Datos guardados satisfactoriamente", Toast.LENGTH_LONG).show();
        }
    }
    public void actualizar (View view){

        AdminDataBase dataBase = new AdminDataBase(getApplicationContext(),"Universidad",1);

        SQLiteDatabase db = dataBase.getWritableDatabase();

    }
    public void consultar (View view){
        AdminDataBase dataBase = new AdminDataBase(getApplicationContext(),"Universidad",1);

        SQLiteDatabase db = dataBase.getWritableDatabase();

        String id = documento.getText().toString();

        if(!id.isEmpty()){
            Cursor fila = db.rawQuery("select * from estudiantes where doc =" +id,null);
            if (fila.moveToFirst()){
                nombre.setText(fila.getString(1));
            }else{
                documento.setText("");
                nombre.setText((""));
                Toast.makeText(getApplicationContext(),"Este número de identificación no existe",Toast.LENGTH_LONG).show();
            }
        }else  {
            Toast.makeText(getApplicationContext(),"Ingrese un número de documento para la busqueda",Toast.LENGTH_LONG).show();
        }
    }
    public void eliminar (View view){

        AdminDataBase dataBase = new AdminDataBase(getApplicationContext(),"Universidad",1);

        SQLiteDatabase db = dataBase.getWritableDatabase();

    }
}