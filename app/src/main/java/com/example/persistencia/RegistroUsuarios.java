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

    EditText documento, nombre, clave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        documento = findViewById(R.id.txtDocumento);
        nombre = findViewById(R.id.txtNombre);
        clave = findViewById(R.id.txtpassword);



    }

    public void registrar(View view) { //Otra forma de configurar un boton.

        AdminDataBase dataBase = new AdminDataBase(getApplicationContext(), "Universidad", 1);

        SQLiteDatabase db = dataBase.getWritableDatabase();

        String doc = documento.getText().toString();
        String nom = nombre.getText().toString();
        String pass = clave.getText().toString();



        ContentValues valores = new ContentValues();
        valores.put("doc", doc);
        valores.put("username", nom);
        valores.put("password",pass);



        long newRorId = db.insert("usuarios", null, valores);

        if (newRorId == -1) {
            Toast.makeText(this, "No se puedo guardar en la base de datos", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Datos guardados satisfactoriamente", Toast.LENGTH_LONG).show();
        }
        documento.setText("");
        nombre.setText("");
        clave.setText("");

    }

    public void actualizar(View view) {

        AdminDataBase dataBase = new AdminDataBase(getApplicationContext(), "Universidad", 1);

        SQLiteDatabase db = dataBase.getWritableDatabase();

        String doc = documento.getText().toString();
        String nom = nombre.getText().toString();
        String pass = clave.getText().toString();

        if (!doc.isEmpty()) {
            ContentValues valActualizar = new ContentValues();
            valActualizar.put("username", nom);
            valActualizar.put("password",pass);
            int idActualizar = db.update("usuarios", valActualizar, "doc=" + doc, null);

            if (idActualizar != 0) {
                Toast.makeText(this, "Usuario Actualizado con exito", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Ingrese el documento a actualizar", Toast.LENGTH_LONG).show();

        }
    }

    public void consultar(View view) {

        AdminDataBase dataBase = new AdminDataBase(getApplicationContext(), "Universidad", 1);

        SQLiteDatabase db = dataBase.getWritableDatabase();

        String id = documento.getText().toString();

        if (!id.isEmpty()) {
            Cursor fila = db.rawQuery("select * from usuarios where doc =" + id, null);
            if (fila.moveToFirst()) {
                nombre.setText(fila.getString(1));
            } else {
                documento.setText("");
                nombre.setText((""));
                Toast.makeText(getApplicationContext(), "Este número de identificación no existe", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Ingrese un número de documento para la busqueda", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminar(View view) {

        AdminDataBase dataBase = new AdminDataBase(getApplicationContext(), "Universidad", 1);

        SQLiteDatabase db = dataBase.getWritableDatabase();

        String id = documento.getText().toString();

        if (!id.isEmpty()) {

            int idEliminar = db.delete("usuarios", "doc=" + id, null);

            if (idEliminar != 0) {
                Toast.makeText(this, "Usuario eliminado con exito", Toast.LENGTH_LONG).show();
                documento.setText("");
                nombre.setText("");
            } else {
                documento.setText("");
                nombre.setText("");
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_LONG).show();
            }

        } else {

            Toast.makeText(getApplicationContext(), "Ingrese el documento a eliminar", Toast.LENGTH_LONG).show();
        }
    }
}