package com.example.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDataBase extends SQLiteOpenHelper {
    //public static final int DATABASE_VERSION = 1;
    //public static final String DATABASE_NAME = "FeedReader.db";
    private static final String SQL_CREATE = "create table Usuarios (doc integer primary key," +
            "username text,password text)";


    public AdminDataBase(Context context, String name, int version) {


        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase creaActua) {

        creaActua.execSQL(SQL_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase creaActua, int i, int i1) {

        creaActua.execSQL("drop table if exists estudiantes");
        creaActua.execSQL(SQL_CREATE);

    }
}
