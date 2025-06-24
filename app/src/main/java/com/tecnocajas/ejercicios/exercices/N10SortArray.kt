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
import kotlinx.coroutines.delay

class N10SortArray : ExerciseInterface {
    override var ID: Int = 10
    override var title: String = "Ordenar un Array"
    override var description: String = "Ordena un array de números enteros de menor a mayor"

    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        /*Entradas del array por parte del usuario*/
        val arrayInput = EditText(context).apply {
            hint = "Ej: 3,1,4,1,5"
            setTextColor(Color.BLACK)
            inputType = InputType.TYPE_CLASS_TEXT
            setPadding(5, 5, 5, 5)
            filters = arrayOf(InputFilter { source, _, _, _, _, _,->
                // solo números y comas
                if (source.all { it.isDigit() || it == ',' }) null else ""
            })
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Dale a Ord. para Ordenar"
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val buttonOperation = Button(context).apply {
            text = "Ordenar"

            setOnClickListener {
                if (validation(arrayInput.text.toString(), context)) {
                    try {
                        var arrayInputUser = arrayInput.text.toString().split(",").filter { it.trim().isNotEmpty() }.map { it.toInt() }
                        var result = sort(arrayInputUser, resultado)
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
            addView(arrayInput)
            addView(buttonOperation)
            addView(resultado)
        }
        return layout
    }

    private fun validation(input1: String, context: Context): Boolean {
        if (input1.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un array de números.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }
    private fun sort(arrayNumbers: List<Int>, resultado: TextView): List<Int> {
        //Implementamos el algoritmo de ordenamiento QUICK SORT
        //Si el usuario inserta solo un numero o no inserta nada (aunque esta validado)
        if (arrayNumbers.size < 2) {
            return arrayNumbers
        }

        //Pivote
        var pivot = arrayNumbers[arrayNumbers.size/2]
        var mayorThanPivot = arrayNumbers.filter { it > pivot }
        var equalToPivot = arrayNumbers.filter { it == pivot }
        var lessThanPivot = arrayNumbers.filter { it < pivot }

        return sort(lessThanPivot, resultado) + equalToPivot + sort(mayorThanPivot, resultado)
    }
}