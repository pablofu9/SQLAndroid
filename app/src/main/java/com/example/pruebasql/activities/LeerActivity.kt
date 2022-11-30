package com.example.pruebasql.activities

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.pruebasql.R
import com.example.pruebasql.controller.AdaptadorAlumno
import com.example.pruebasql.controller.SqliteHelper
import com.example.pruebasql.model.Alumno
import com.example.pruebasql.model.AlumnosContract

class LeerActivity : AppCompatActivity() {

    private lateinit var lista : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leer)

        val helper = SqliteHelper(this)
        lista = findViewById(R.id.lista)

        val c: Cursor = helper.leer()

        val arrayList: ArrayList<Alumno> = ArrayList<Alumno>()
        while (c.moveToNext()) {
            arrayList.add(
                Alumno(
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.DNI)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.APELLIDOS)),
                    c.getInt(c.getColumnIndexOrThrow(AlumnosContract.EDAD)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.TELEFONO))
                )
            )
        }

        val adaptador = AdaptadorAlumno(this, arrayList)
        lista.adapter = adaptador
    }
}