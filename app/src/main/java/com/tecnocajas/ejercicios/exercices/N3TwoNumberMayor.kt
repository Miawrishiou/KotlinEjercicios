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

class N3TwoNumberMayor : ExerciseInterface {
    override var ID = 3
    override var title = "Mayor de dos numeros"
    override var description = "Evaluar 2 numeros y devolver el mayor de ellos."

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
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
        val input2 = EditText(context).apply {
            hint = "Número 2"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Resultado: "
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val boton = Button(context).apply {
            text = "Promediar"

            setOnClickListener {
                if (validation(input1.text.toString(), input2.text.toString(), context)) {
                    try {
                        var input1 = input1.text.toString().toDouble()
                        var input2 = input2.text.toString().toDouble()

                        var result = mayorNumber(input1, input2)
                        resultado.text = "El mayor numero es el ${result}"
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
            addView(input2)
            addView(boton)
            addView(resultado)
        }
        return layout
    }
    private fun validation(input1: String, input2: String, context: Context) : Boolean {
        if (input1.isEmpty() || input2.isEmpty()){
            Toast.makeText(context, "Por favor, revisa los campos marcados. Hay valores nulos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun mayorNumber(input1: Double, input2: Double): String {
        var resultado:Any
        if (input1 > input2){
            resultado = input1
        } else if (input2 > input1) {
            resultado = input2
        } else {
            resultado = "Es igual cabron."
        }
        return resultado.toString()
    }
}