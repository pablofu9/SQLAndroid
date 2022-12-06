package com.example.pruebasql.activities

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import android.widget.Toast
import com.example.pruebasql.R
import com.example.pruebasql.controller.AdaptadorAlumno
import com.example.pruebasql.controller.SqliteHelper
import com.example.pruebasql.model.Alumno
import com.example.pruebasql.model.AlumnosContract

class LeerActivity : AppCompatActivity() {

    private lateinit var lista : ListView
    private lateinit var arrayList: ArrayList<Alumno>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leer)

        val helper = SqliteHelper(this)
        lista = findViewById(R.id.lista)





        registerForContextMenu(lista)

    }

    override fun onResume() {
        //Aqui recorremos el arraylist para poder llamarlo luego en el menu, cuando eliminamos y modificamos.
        super.onResume()
        val helper = SqliteHelper(this)
        arrayList= ArrayList<Alumno>()
        val c: Cursor = helper.leer()
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


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info =item.menuInfo as AdapterContextMenuInfo
        val helper = SqliteHelper(this)
        when(item.itemId){
            R.id.modificar->{
                intent = Intent(this, InsertarActivity::class.java).apply {
                    val l2 = lista?.adapter?.getItem(info.position) as Alumno
                    putExtra("alumno",l2)
                }
                startActivity(intent)
            }
            //BORRAR DE LA BD
            R.id.eliminar->{
                val a1 =lista?.adapter?.getItem(info.position) as Alumno
                helper.eliminarAlumno(a1)
                Toast.makeText(this, "Usuario borrado", Toast.LENGTH_LONG).show()
                onResume()

            }
        }
        return super.onContextItemSelected(item)
    }
}