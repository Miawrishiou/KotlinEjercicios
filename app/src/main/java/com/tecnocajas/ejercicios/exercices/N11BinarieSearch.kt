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

class N11BinarieSearch : ExerciseInterface {
    private val sorter: N10SortArray = N10SortArray()
    override var ID = 11
    override var title = "Busqueda Binaria"
    override var description = "Realiza una busqueda binaria en un array de enteros ordenados"

    override fun makeContainer(context: Context): View {
        val arrayRandom = createRandomArray()
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        /*Mostrar el array*/
        val array = TextView(context).apply {
            text = arrayToString(arrayRandom)
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Numero a buscar*/
        val input = EditText(context).apply {
            hint = "Buscar el indice."
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Búscalo"
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val buttonOperation = Button(context).apply {
            text = "Buscalo"

            setOnClickListener {
                if (validation(input.text.toString(), context)) {
                    try {
                        var arrayEvaluate = arrayRandom
                        var goal = input.text.toString().toInt()
                        if (isInArray(arrayEvaluate, goal)) {
                            var result = binarySearch(arrayEvaluate, goal)
                            resultado.text = "Indice ${result}"
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
            addView(array)
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }
        return layout
    }
    private fun isInArray (array: List<Int>, goal: Int): Boolean {
        return goal.toInt() in array
    }
    private fun arrayToString(array: List<Int>): String {
        return array.joinToString(", ")
    }
    private fun validation(input1: String, context: Context): Boolean {
        if (input1.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un valor para hallar el indice del numero de arriba.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun createRandomArray(): List<Int> {
        //declarar las variables a usar
        var result = mutableListOf<Int>()
        var arrayLength: Int = 7
        //Bucle para rellenar el array cn numeros aleatorios
        for (i in 1..arrayLength) {
            val random = (1..200).random()
            result.add(random)
        }
        //Ordenar el array con el metodo construido
        var sortedArray = sorter.sort(result)
        //retornar el valor
        return sortedArray
    }
    private fun binarySearch(array: List<Int>, goal: Int): Int {
        //Implementacion del algoritmo de búsqueda binaria
        //Extremos
        var indexRight = array.size - 1
        var indexLeft = 0
        //Resultado
        var result: Int = -1
        //Implementación del bucle para encontrar el elemento
        while (indexLeft <= indexRight) {
            //Declaracion del indice a devolver
            val pivotIndex = (indexLeft + indexRight) / 2
            val pivotValue = array[pivotIndex]
            //Evaluamos si el pivote es igual a lo que buscamos
            if (goal == pivotValue) {
                result = pivotIndex
                return result
            } else if (goal > pivotValue) /*Caso contrario evaluamos si es mayor*/{
                indexLeft = pivotIndex + 1
            } else /*Por ultimo si es menor*/{
                indexRight = pivotIndex - 1
            }
        }
        return result
    }
}