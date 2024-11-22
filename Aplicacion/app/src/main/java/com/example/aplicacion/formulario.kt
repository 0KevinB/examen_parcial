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

class formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navegar()
        val calcuar = findViewById<Button>(R.id.button_aceptar)
        val pesoInput = findViewById<TextView>(R.id.peso)
        val alturaInput = findViewById<TextView>(R.id.altura)
        val resultado = findViewById<TextView>(R.id.resultado)
        val circulo = findViewById<TextView>(R.id.circulo)

        calcuar.setOnClickListener {
            val peso = pesoInput.text.toString().toFloatOrNull()
            val altura = alturaInput.text.toString().toFloatOrNull()

            if (peso != null && altura != null) {
                val imc = calcularIMC(peso, altura)
                val categoria = obtenerCategoriaIMC(imc)

                resultado.text = String.format(
                    "Tu IMC es de %s", categoria
                )
                circulo.text = String.format(
                    "%.2f",  imc
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
    private fun calcularIMC(peso: Float, altura: Float): Float {
        print(peso)
        print(altura)
        var imc = peso / (altura * altura)
        return imc
    }
    private fun obtenerCategoriaIMC(imc: Float): String {
        return when {
            imc < 18.5 -> "Bajo peso"
            imc < 25-> "Peso normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidad"
        }
    }
}