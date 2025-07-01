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

class N17FactorialRecursivity : ExerciseInterface {
    override var ID = 17
    override var title = "Factorial con Recursividad"
    override var description = "Calcular el factorial de un numero con recursividad"

    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        /*Entradas de la cantidad de digitos para mostrar en el numero de fibonacci*/
        val input = EditText(context).apply {
            hint = "Ingrese su numero"
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
            text = "Factorial"

            setOnClickListener {
                if (validation(input.text.toString(), context)) {
                    try {
                        var result = factorial(1, input.text.toString().toInt())
                        resultado.text = "Resultado: ${result}"
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
    private fun factorial(acumulator: Int, number: Int): Int {
        if (number == 1) {
            return acumulator
        } else {
            var acumulatorResult = acumulator * number
            return factorial(acumulatorResult, number - 1)
        }
    }
}