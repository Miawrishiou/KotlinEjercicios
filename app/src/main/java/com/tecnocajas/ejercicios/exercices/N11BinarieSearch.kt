package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tecnocajas.ejercicios.R

class N11BinarieSearch : ExerciseInterface {
    // Se reutiliza el ordenamiento del ejercicio N10 para asegurar un array ordenado
    private val sorter: N10SortArray = N10SortArray()

    override var ID = 11
    override var title = "Busqueda Binaria"
    override var description = "Realiza una búsqueda binaria en un array de enteros ordenados"

    override fun makeContainer(context: Context): View {
        // Cargar tipografías Rubik desde res/font/
        val fontBold = ResourcesCompat.getFont(context, R.font.rubik_bold)
        val fontRegular = ResourcesCompat.getFont(context, R.font.rubik_regular)
        val fontSemiBold = ResourcesCompat.getFont(context, R.font.rubik_semibold)

        // Generar un array aleatorio ya ordenado
        val arrayRandom = createRandomArray()

        // Contenedor principal vertical
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(32, 32, 32, 32)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        // Título del ejercicio
        val titulo = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.BLACK)
            typeface = fontBold
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        // Mostrar el array generado al usuario
        val arrayTextView = TextView(context).apply {
            text = arrayToString(arrayRandom)
            setTextColor(Color.DKGRAY)
            textSize = 18f
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        // Campo de entrada para el número a buscar
        val input = EditText(context).apply {
            hint = "Buscar el índice..."
            inputType = InputType.TYPE_CLASS_NUMBER or
                    InputType.TYPE_NUMBER_FLAG_SIGNED or
                    InputType.TYPE_NUMBER_FLAG_DECIMAL
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(16, 8, 16, 8)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        // Campo para mostrar el resultado
        val resultado = TextView(context).apply {
            text = "Búscalo"
            textSize = 18f
            setTextColor(Color.DKGRAY)
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 24, 0, 0) }
        }

        // Botón de acción para ejecutar la búsqueda
        val buttonOperation = Button(context).apply {
            text = "Búscalo"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { gravity = Gravity.CENTER_HORIZONTAL }

            setOnClickListener {
                if (validation(input.text.toString(), context)) {
                    try {
                        val goal = input.text.toString().toInt()
                        if (isInArray(arrayRandom, goal)) {
                            val result = binarySearch(arrayRandom, goal)
                            resultado.text = "Índice: $result"
                        } else {
                            resultado.text = "El número no se encuentra en el array"
                        }
                    } catch (e: NumberFormatException) {
                        Toast.makeText(
                            context,
                            "Error en la conversión de entrada: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // Estructura final del layout
        layout.apply {
            addView(titulo)
            addView(arrayTextView)
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }

        return layout
    }

    // Validación de entrada: no vacío
    private fun validation(input1: String, context: Context): Boolean {
        if (input1.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, ingrese un valor para hallar el índice.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    // Verificar si el número está presente en el array
    private fun isInArray(array: List<Int>, goal: Int): Boolean {
        return goal in array
    }

    // Convertir un array a una cadena legible con comas
    private fun arrayToString(array: List<Int>): String {
        return array.joinToString(", ")
    }

    // Crear un array aleatorio y ordenarlo
    private fun createRandomArray(): List<Int> {
        val result = mutableListOf<Int>()
        val arrayLength = 7
        for (i in 1..arrayLength) {
            val random = (1..200).random()
            result.add(random)
        }
        return sorter.sort(result)
    }

    // Algoritmo de búsqueda binaria clásico
    private fun binarySearch(array: List<Int>, goal: Int): Int {
        var indexLeft = 0
        var indexRight = array.size - 1

        while (indexLeft <= indexRight) {
            val pivotIndex = (indexLeft + indexRight) / 2
            val pivotValue = array[pivotIndex]

            if (goal == pivotValue) {
                return pivotIndex
            } else if (goal > pivotValue) {
                indexLeft = pivotIndex + 1
            } else {
                indexRight = pivotIndex - 1
            }
        }

        return -1 // No encontrado
    }
}