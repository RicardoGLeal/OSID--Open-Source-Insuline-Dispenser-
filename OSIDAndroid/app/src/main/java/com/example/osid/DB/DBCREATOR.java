package com.example.osid.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCREATOR extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DE_DATOS = "OSID_DB";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public static final String TABLA_USUARIO = "User"
            , TABLA_INSULINE = "Insuline"
            , TABLA_GLUCOSE = "Glucose";

    public DBCREATOR(Context context){
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(Id_Usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nombre TEXT,PrimerApellido TEXT,SegundoApellido TEXT,Edad INTEGER, Peso FLOAT, Basal FLOAT," +
                " Genero BOOLEAN, PGPU FLOAT, insulinaRestante FLOAT)",TABLA_USUARIO));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(Id_Glucose INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FechaHora DATETIME, Glucosa INTEGER)",TABLA_GLUCOSE));

        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(Id_Insuline INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FechaHora DATETIME, Insuline FLOAT)",TABLA_INSULINE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
/*
    public boolean insertdata(float valuex, float valuey){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("xValue", valuex);
        contentValues.put("yValue", valuey);
        sqLiteDatabase.insert("Glucose", null, contentValues);
    }
    */

}
