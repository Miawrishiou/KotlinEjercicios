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

class N7Factorial : ExerciseInterface {
    override var ID = 7
    override var title = "Factorial"
    override var description = "Calcular el factorial de un numero."

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
            text = "Factorial"

            setOnClickListener {
                if (validation(input1.text.toString(), context)) {
                    try {
                        var input1 = input1.text.toString().toInt()
                        var result = factorial(input1)
                        resultado.text = "El Factorial es: ${result}"
                    } catch (e : NumberFormatException) {
                        Toast.makeText(context, "Error en la conversion de valores a integer, ${e.message}", Toast.LENGTH_SHORT).show()
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
    private fun factorial(input1: Int): Int {
        var acumulator = input1
        for (i in 1 .. input1) {
            if (i == input1) {
                continue
            }
            acumulator*=i
        }
        return acumulator
    }
}