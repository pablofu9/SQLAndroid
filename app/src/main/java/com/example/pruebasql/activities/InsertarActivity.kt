package com.example.pruebasql.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pruebasql.R
import com.example.pruebasql.controller.SqliteHelper
import com.example.pruebasql.model.Alumno

class InsertarActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var dni: EditText
    private lateinit var nombre: EditText
    private lateinit var apellidos: EditText
    private lateinit var edad: EditText
    private lateinit var telefono: EditText
    private lateinit var guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar)

        dni = findViewById(R.id.dni)
        nombre = findViewById(R.id.nombre)
        apellidos = findViewById(R.id.apellidos)
        edad = findViewById(R.id.edad)
        guardar = findViewById(R.id.guardar)
        telefono = findViewById(R.id.telefono)
        guardar.setOnClickListener(this)


        //Si venimos de modificar, hacemos esto
        if(intent.hasExtra("alumno")){
            val alRecibed =intent?.getSerializableExtra("alumno") as Alumno
            dni.setText(alRecibed.dni)
            nombre.setText(alRecibed.nombre)
            apellidos.setText(alRecibed.apellidos)
            edad.setText(alRecibed.edad.toString())
            telefono.setText(alRecibed.telefono)
            dni.isEnabled = false
            guardar.text = "Modificar"

        }
    }

    override fun onClick(view: View) {
        if (dni.text.toString().isNotEmpty() && nombre.text.toString().isNotEmpty()
            && apellidos.text.toString().isNotEmpty() && edad.text.toString().isNotEmpty()
        ) {
            val helper = SqliteHelper(this)
            //Para que el boton actue de manera distinta si venimos de modificar
            if(intent.hasExtra("alumno")){
                val alModificado = Alumno(dni.text.toString(), nombre.text.toString(), apellidos.text.toString(),
                edad.text.toString().toInt(), telefono.text.toString())
                helper.modificarAlumno(alModificado)
                finish()
            }else {
                try {
                    if (helper.anyadirAlumno(
                            Alumno(
                                dni.text.toString(),
                                nombre.text.toString(),
                                apellidos.text.toString(),
                                edad.text.toString().toInt(),
                                telefono.text.toString()
                            )
                        ) != (-1).toLong()

                    ) {
                        Toast.makeText(this, "Insertado", Toast.LENGTH_SHORT).show()
                        finish()
                    } else
                        Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show()
                } catch (ex: NumberFormatException) {
                    Toast.makeText(this, "La edad tiene que ser númerica", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            }
        }
    }
