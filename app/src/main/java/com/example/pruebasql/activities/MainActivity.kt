package com.example.pruebasql.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pruebasql.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var insertar: Button
    private lateinit var leer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insertar = findViewById(R.id.insert)
        leer = findViewById(R.id.leer)

        insertar.setOnClickListener(this)
        leer.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.insert -> {
                var intent = Intent(this, InsertarActivity::class.java)
                startActivity(intent)
            }
            R.id.leer -> {
                intent = Intent(this, LeerActivity::class.java)
                startActivity(intent)
            }
        }
    }
}