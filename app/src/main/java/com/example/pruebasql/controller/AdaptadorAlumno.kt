package com.example.pruebasql.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.pruebasql.R
import com.example.pruebasql.model.Alumno
import java.util.ArrayList

class AdaptadorAlumno : ArrayAdapter<Alumno> {
    private var lista: ArrayList<Alumno>

    constructor(context: Context, lista: ArrayList<Alumno>)
            : super(
        context, R.layout.item, lista) {
        this.lista = lista
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        var convertView = inflater.inflate(R.layout.item, null)

        val dni = convertView.findViewById<TextView>(R.id.dni)
        val nombre = convertView.findViewById<TextView>(R.id.nombre)
        val apellidos = convertView.findViewById<TextView>(R.id.apellidos)
        val edad = convertView.findViewById<TextView>(R.id.edad)
        val telefono = convertView.findViewById<TextView>(R.id.telefono)

        dni.text = lista[position].dni
        nombre.text = lista[position].nombre
        apellidos.text = lista[position].apellidos
        edad.text = lista[position].edad.toString()
        telefono.text=lista[position].telefono

        return convertView
    }
}
