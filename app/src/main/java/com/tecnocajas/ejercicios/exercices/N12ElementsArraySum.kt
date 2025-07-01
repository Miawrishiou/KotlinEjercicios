package com.tecnocajas.ejercicios.exercices

import android.R
import android.content.Context
import android.content.res.ColorStateList
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

class N12ElementsArraySum : ExerciseInterface {
    override var ID = 12
    override var title = "Suma de elementos de un array"
    override var description = "Realiza la suma de los elementos de un array"

    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        /*Entradas del array por parte del usuario*/
        val arrayInput1 = EditText(context).apply {
            hint = "Ej: 3,1,4,1,5"
            setTextColor(Color.BLACK)
            inputType = InputType.TYPE_CLASS_TEXT
            filters = arrayOf(InputFilter { source, _, _, _, _, _,->
                // solo números y comas
                if (source.all { it.isDigit() || it == ',' }) null else ""
            })
        }
        /*Entradas del array por parte del usuario*/
        val arrayInput2 = EditText(context).apply {
            hint = "Ej: 3,1,4,1,5"
            setTextColor(Color.BLACK)
            inputType = InputType.TYPE_CLASS_TEXT
            filters = arrayOf(InputFilter { source, _, _, _, _, _,->
                // solo números y comas
                if (source.all { it.isDigit() || it == ',' }) null else ""
            })
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Dale al boton."
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val buttonOperation = Button(context).apply {
            text = "Sumar los dos arreglos"

            setOnClickListener {
                if (validation(arrayInput1.text.toString(), arrayInput2.text.toString(), context)) {
                    try {
                        var arrayInputUser1 = arrayInput1.text.toString().split(",").filter { it.trim().isNotEmpty() }.map { it.toInt() }
                        var arrayInputUser2 = arrayInput2.text.toString().split(",").filter { it.trim().isNotEmpty() }.map { it.toInt() }
                        var result = sumTwoArrays(arrayInputUser1, arrayInputUser2)
                        resultado.text = "El array ordenado es: ${result.joinToString(", ")}"
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
            addView(arrayInput1)
            addView(arrayInput2)
            addView(buttonOperation)
            addView(resultado)
        }
        return layout
    }

    private fun validation(input1: String, input2: String, context: Context): Boolean {
        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un array de números en los espacios.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun sumTwoArrays(array1: List<Int>, array2: List<Int>): List<Int> {
        var result = mutableListOf<Int>()
        var mayorIndex = if (array1.size > array2.size) array1.size else array2.size
        for (i in 0 until mayorIndex) {
            var actualSum = array1.getOrElse(i) { 0 } + array2.getOrElse(i) { 0 }
            result.add(actualSum)
        }

        return result
    }
}