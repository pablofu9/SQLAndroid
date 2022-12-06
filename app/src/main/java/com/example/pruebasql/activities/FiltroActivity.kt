package com.example.pruebasql.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.pruebasql.R

class FiltroActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

       private lateinit var txtMinima : TextView
       private lateinit var txtMaxima:TextView
       private lateinit var seekMinima : SeekBar
       private lateinit var seekMaxima : SeekBar
       private lateinit var groupFiltro : RadioGroup
       private lateinit var radioEmpieza : RadioButton
       private lateinit var radioContiene : RadioButton
       private lateinit var radioBuscar : RadioButton

       private lateinit var txtBuscar : EditText
       private lateinit var btnBuscar : Button
       private var eMinima :Int = 0
       private var eMaxima :Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtro)

        txtMinima=findViewById(R.id.txtMinima)
        txtMaxima=findViewById(R.id.txtMaxima)
        seekMinima=findViewById(R.id.seekMinima)
        seekMaxima= findViewById(R.id.seekMaxima)
        groupFiltro=findViewById(R.id.groupFiltro)
        radioEmpieza=findViewById(R.id.radioEmpieza)
        radioContiene=findViewById(R.id.radioContiene)
        radioBuscar=findViewById(R.id.radioBuscar)
        txtBuscar=findViewById(R.id.txtBuscar)
        btnBuscar=findViewById(R.id.btnBuscar)

        seekMinima.setOnSeekBarChangeListener(this)
        seekMaxima.setOnSeekBarChangeListener(this)

        txtMinima.text="Edad minima: "+eMinima
        txtMaxima.text="Edad maxima: "+eMaxima
    }

    //Control de los seekbar y su funcionamiento

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        eMinima=seekMinima.progress
        eMaxima=seekMaxima.progress

        txtMinima.text="Edad minima: "+eMinima
        txtMaxima.text="Edad maxima: "+eMaxima
        if(seekMinima.progress>seekMaxima.progress){

            Toast.makeText(this,"La edad minima no puede ser mayor que la maxima",Toast.LENGTH_LONG).show()
            seekMaxima.progress=100
            seekMinima.progress=0
        }

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}