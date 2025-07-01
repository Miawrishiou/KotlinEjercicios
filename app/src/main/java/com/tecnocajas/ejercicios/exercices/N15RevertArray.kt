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

class N15RevertArray : ExerciseInterface {
    override var ID = 15
    override var title = "Reverso de un array"
    override var description = "Devuelve el reverso de un array de numeros"

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
            inputType = InputType.TYPE_CLASS_TEXT
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
            text = "Invertir"

            setOnClickListener {
                if (validation(arrayInput.text.toString(), context)) {
                    try {
                        var arrayInputUser = arrayInput.text.toString().split(",").filter { it.trim().isNotEmpty() }.map { it.toInt() }
                        var result = revertArray(arrayInputUser)
                        resultado.text = "El array invertido  es: ${result.joinToString(", ")}"
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
    private fun revertArray (array: List<Int>) : List<Int> {
        var reverted = mutableListOf<Int>()
        for (i in array.size-1 downTo 0) {
            reverted.add(array[i])
        }
        return reverted
    }
}