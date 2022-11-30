package com.example.pruebasql.model

import android.provider.BaseColumns

object AlumnosContract: BaseColumns {
    const val TABLE_NAME = "alumnos"
    const val DNI = "dni"
    const val NOMBRE = "nombre"
    const val APELLIDOS = "apellidos"
    const val EDAD = "edad"
    const val TELEFONO ="telefono"
}