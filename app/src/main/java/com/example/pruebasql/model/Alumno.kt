package com.example.pruebasql.model

class Alumno: java.io.Serializable{
    var dni: String
    var nombre: String
    var apellidos: String
    var edad: Int
    var telefono: String? //La interrogacion es para indicar que nos puede llegar un telefono nulo

    constructor() {
        dni = ""
        apellidos = ""
        nombre = ""
        edad = 0
        telefono=""
    }

    constructor(dni: String, nombre: String, apellidos: String, edad: Int) {
        this.dni = dni
        this.nombre = nombre
        this.apellidos = apellidos
        this.edad = edad
        this.telefono=""
    }
    //Tambien debemos poner aqui la interrogacion para indicar que el tefelono va a ser nulo
    constructor(dni: String, nombre: String, apellidos: String, edad: Int, telefono: String?) {
        this.dni = dni
        this.nombre = nombre
        this.apellidos = apellidos
        this.edad = edad
        this.telefono = telefono
    }


    override fun toString(): String {
        return "Alumno{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                '}'
    }
}