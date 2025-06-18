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

class N4OddEven : ExerciseInterface {
    override var ID = 4
    override var title = "Par o impar"
    override var description = "Evaluar si un numero es par o impar. Con una validacion previa."

    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }
        /*Titulo del ejercicio*/
        val titulo = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
        }
        /*Entradas de los numeros*/
        val input1 = EditText(context).apply {
            hint = "Número 1"
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Resultado: "
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val boton = Button(context).apply {
            text = "Par o Impar"

            setOnClickListener {
                if (validation(input1.text.toString(), context)) {
                    try {
                        var input1 = input1.text.toString().toInt()

                        var result = oddEven(input1)
                        resultado.text = "El numero que ingreso ${result}"
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
    private fun oddEven(input1: Int): String {
        val resultado = if (input1 % 2 == 0) "Es par" else "Es impar"
        return resultado
    }
}