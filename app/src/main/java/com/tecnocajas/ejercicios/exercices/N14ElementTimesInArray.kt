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

class N14ElementTimesInArray : ExerciseInterface {
    private val sorter: N10SortArray = N10SortArray()
    override var ID = 14
    override var title = "Elemento mayor de un array"
    override var description = "Realiza la suma de los elementos de un array"

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
            filters = arrayOf(InputFilter { source, _, _, _, _, _,->
                //solo números y comas
                if (source.all { it.isDigit() || it == ',' }) null else ""
            })
        }
        /*Numero a buscar*/
        val input = EditText(context).apply {
            hint = "Buscar el indice."
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Resultado"
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val buttonOperation = Button(context).apply {
            text = "Buscalo"
            setOnClickListener {
                if (validation(input.text.toString(), arrayInput.text.toString(), context)) {
                    try {
                        var arrayInputUser = arrayInput.text.toString().split(",").filter { it.trim().isNotEmpty() }.map { it.toInt() }
                        var goal = input.text.toString().toInt()
                        if (isInArray(arrayInputUser, goal)) {
                            var result = elementTimesInArray(arrayInputUser, goal)
                            resultado.text = "Veces que aparece: ${result}"
                        } else {
                            resultado.text = "El numero no se encuentra en el array"
                        }
                    } catch (e : NumberFormatException) {
                        Toast.makeText(context, "Error en la conversion de entrada a array, ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        val title = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }
        layout.apply {
            addView(title)
            addView(arrayInput)
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }
        return layout
    }
    private fun isInArray (array: List<Int>, goal: Int): Boolean {
        return goal.toInt() in array
    }
    /*Validacion de que las entradas sean las correctas*/
    private fun validation(arrayInput: String, input1: String, context: Context): Boolean {
        if (input1.isEmpty() || arrayInput.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un valor para hallar el indice del numero de arriba.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun elementTimesInArray(array: List<Int>, goal: Int): Int {
        //resultado para almacenar las veces que aparece el elemento en el arreglo
        var result = 0
        //recorrer el array para hallar las veces que aparece el elemento ingresados
        for (i in 0 until array.size) {
            //verificar que el elemento que encontremos
            if (array[i] == goal) {
                result++
            }
        }
        //retornar el valor
        return result
    }
}