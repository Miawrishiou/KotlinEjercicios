package com.tecnocajas.ejercicios.exercices
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class N1TwoNumSum : ExerciseInterface {
    override var title = "Suma de dos numeros"
    override var description = "Sumar dos numeros validando que estos esten correctamente adecuados."
    override var ID = 1

    override fun makeContainer(context: Context): View {
        /*Declaracion de los elementos y sus contenidos*/
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }
        /*Titulo del ejercicio*/
        val titulo = TextView(context).apply {
            text = "Suma de dos números"
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
        /*Realizar la suma*/
        val boton = Button(context).apply {
            text = "Sumar"

            setOnClickListener {
                if (validation(input1.text.toString(), input2.text.toString(), context)) {
                    var input1 = input1.text.toString().toDouble()
                    var input2 = input2.text.toString().toDouble()
                    var result = sum(input1, input2)
                    resultado.text = "El resultado de la suma es: ${result}"
                }
            }
        }
        /*Cargar los elementos*/
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
            Toast.makeText(context, "Por favor, revisa los campos marcados.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun sum(number1: Double, number2: Double): Double {
        val sumation: Double = number1 + number2
        return sumation
    }
}