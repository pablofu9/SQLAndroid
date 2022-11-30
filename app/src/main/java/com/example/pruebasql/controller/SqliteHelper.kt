package com.example.pruebasql.controller

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pruebasql.model.Alumno
import com.example.pruebasql.model.AlumnosContract

class SqliteHelper(context: Context?) :
    SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "Alumnos.db"
        private const val VERSION = 3
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE " +
                      AlumnosContract.TABLE_NAME+ " ( "
                    + AlumnosContract.DNI + " TEXT PRIMARY KEY, "
                    + AlumnosContract.NOMBRE + " TEXT NOT NULL, "
                    + AlumnosContract.APELLIDOS + " TEXT NOT NULL, "
                    + AlumnosContract.EDAD + " INTEGER NOT NULL,"
                    + AlumnosContract.TELEFONO + " TEXT)"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if(oldVersion==2 && newVersion==3){

            sqLiteDatabase.execSQL(
                "ALTER TABLE "+
                        AlumnosContract.TABLE_NAME +" ADD COLUMN "+
                        AlumnosContract.TELEFONO+" TEXT "
            )
            //Insertar columna telefono

        }else if(oldVersion==1){
            //Borrar base de datos
            sqLiteDatabase.execSQL("DROP TABLE " + AlumnosContract.TABLE_NAME)
            onCreate(sqLiteDatabase)
        }
    }

    fun anyadirAlumno(alumno: Alumno): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(AlumnosContract.DNI, alumno.dni)
        values.put(AlumnosContract.NOMBRE, alumno.nombre)
        values.put(AlumnosContract.APELLIDOS, alumno.apellidos)
        values.put(AlumnosContract.EDAD, alumno.edad)
        values.put(AlumnosContract.TELEFONO, alumno.telefono)
        return db.insert(AlumnosContract.TABLE_NAME, null, values)

        /*SI no quisieramos añadir todos los datos
        val datos = arrayOf(AlumnosContract.DNI, AlumnosContract.NOMBRE, AlumnosContract.APELLIDOS)
        return db.insert(AlumnosContract.TABLE_NAME, datos.toString(), values)*/
        /*
        val sql = "insert into " + AlumnosContract.TABLE_NAME + " values ("+alumno.dni +","+alumno.nombre +","+alumno.apellidos +","+alumno.edad +")"
        db.execSQL(sql)
         */
    }

    fun leer(): Cursor {
        val db = readableDatabase
        return db.query(AlumnosContract.TABLE_NAME, null, null,
            null, null, null, null)

        /*
        val sql = "select * from " + AlumnosContract.TABLE_NAME
        return db.rawQuery(sql, null)
         */
    }
}