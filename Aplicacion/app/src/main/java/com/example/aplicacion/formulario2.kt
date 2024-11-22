package com.example.aplicacion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class formulario2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navegar()
        val calcuar = findViewById<Button>(R.id.button_aceptar)
        val sexoInput = findViewById<TextView>(R.id.sexo)
        val alturaInput = findViewById<TextView>(R.id.altura)
        val resultado = findViewById<TextView>(R.id.resultado)
        val circulo = findViewById<TextView>(R.id.circulo)

        calcuar.setOnClickListener {
            val sexo = sexoInput.text.toString()
            val altura = alturaInput.text.toString().toFloatOrNull()

            if (sexo != null && altura != null) {
                val ideal = calcularPesoIdeal(sexo , altura)

                resultado.text = String.format(
                    "Tu peso ideal es de %s kg", ideal
                )
                circulo.text = String.format(
                    "%.2f kg",  ideal
                )
            } else {
                Toast.makeText(this, "Por favor ingrese peso y altura", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navegar() {
        val buttonRegresar = findViewById<Button>(R.id.button_regresar)
        buttonRegresar.setOnClickListener() {
            val cambiar_ventana = Intent(this, MainActivity::class.java)
            startActivity(cambiar_ventana)
        }
    }
    private fun calcularPesoIdeal(sexo: String, altura: Float): Float {
        if (altura < 100.0){
            altura * 100
        }
        var ideal: Float
        if(sexo == "hombre"){
            ideal = (altura - 100)
            return ideal

        } else {
            ideal = (altura - 105)
            return ideal
        }
    }
}