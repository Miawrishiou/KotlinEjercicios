package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class N6PositivNegativeZero : ExerciseInterface {
    override var ID = 6
    override var title = "Positivo, negativo o cero"
    override var description = "Evaluar si un numero es positivo, negativo o cero."

    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        /*Titulo del ejercicio*/
        val titulo = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }
        /*Entradas de los numeros*/
        val input1 = EditText(context).apply {
            hint = "Ingrese su numero"
            inputType = InputType.TYPE_CLASS_NUMBER  or InputType.TYPE_NUMBER_FLAG_SIGNED
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Resultado: "
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val boton = Button(context).apply {
            text = "¿Que numero es?"
            setOnClickListener {
                if (validation(input1.text.toString(), context)) {
                    try {
                        var input1 = input1.text.toString().toDouble()
                        var result = positiveNegativeZero(input1)
                        resultado.text = "El numero que ingresó ${result}"
                    } catch (e : NumberFormatException) {
                        Toast.makeText(context, "Error en la conversion de valores a double, ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        /*Cargar los elementos dentro del layout para renderizar por bloque*/
        layout.apply {
            addView(titulo)
            addView(input1)
            addView(boton)
            addView(resultado)
        }
        return layout
    }
    private fun validation(input1: String, context: Context) : Boolean {
        if (input1.isEmpty()){
            Toast.makeText(context, "Por favor, revisa los campos marcados. Hay valores nulos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun positiveNegativeZero(input1: Double): String {
        var resultado = ""
        if (input1 > 0) {
            resultado = "Es positivo"
        } else if (input1 < 0) {
            resultado = "Es negativo"
        } else {
            resultado = "Es cero"
        }
        return resultado
    }
}