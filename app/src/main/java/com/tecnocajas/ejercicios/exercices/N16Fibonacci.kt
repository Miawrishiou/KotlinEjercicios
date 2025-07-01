package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class N16Fibonacci : ExerciseInterface {
    override var ID = 16
    override var title = "Fibonacci con Recursividad"
    override var description = "Calcula el numero de fibonacci con recursividad"

    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        /*Entradas de la cantidad de digitos para mostrar en el numero de fibonacci*/
        val input = EditText(context).apply {
            hint = "Buscar el indice."
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Dale al boton."
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val buttonOperation = Button(context).apply {
            text = "Fibonacci"

            setOnClickListener {
                if (validation(input.text.toString(), context)) {
                    try {
                        var result = fibonacci(input.text.toString().toInt(), listOf(), 0, 1)
                        resultado.text = "Resultado: ${result.joinToString(", ")}"
                    } catch (e : NumberFormatException) {
                        Toast.makeText(context, "Error en la conversion de entrada a array, ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        val titulo = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }
        layout.apply {
            addView(titulo)
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }
        return layout
    }
    /*Validacion de que las entradas sean las correctas*/
    private fun validation(input: String, context: Context): Boolean {
        //Validacion de que el elemento ingresado al menos tenga un numero
        if (input.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un valor para la longitud de la secuencia.", Toast.LENGTH_SHORT).show()
            return false
        }
        //Vaildacion de que el elemento ingresado este dentro de los rangos esperados
        if (input.toInt() < 1 || input.toInt() > 20) {
            Toast.makeText(context, "Por favor, ingrese un valor mayor a 1 y menor a 20", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    /*Secuencia de fibonacci*/
    private fun fibonacci(goal: Int, list: List<Int>, previousNumber: Int, actualNumber: Int): List<Int> {
        if (list.size == goal) {
            return list
        } else {
            val list = list.toMutableList()
            list.add(actualNumber)
            val previousNumberList = actualNumber
            val actualNumberList = previousNumber + actualNumber
            return fibonacci(goal, list, previousNumberList, actualNumberList)
        }
    }
}