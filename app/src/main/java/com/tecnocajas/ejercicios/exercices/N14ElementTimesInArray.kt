package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tecnocajas.ejercicios.R

class N14ElementTimesInArray : ExerciseInterface {
    private val sorter: N10SortArray = N10SortArray()

    override var ID = 14
    override var title = "Frecuencia de un número en un array"
    override var description = "Calcula cuántas veces aparece un número en un array"

    override fun makeContainer(context: Context): View {
        // Cargar tipografías Rubik
        val fontBold = ResourcesCompat.getFont(context, R.font.rubik_bold)
        val fontRegular = ResourcesCompat.getFont(context, R.font.rubik_regular)
        val fontSemiBold = ResourcesCompat.getFont(context, R.font.rubik_semibold)

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

        // Entrada del array
        val arrayInput = EditText(context).apply {
            hint = "Ej: 3,1,4,1,5"
            inputType = InputType.TYPE_CLASS_TEXT
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(16, 8, 16, 8)
            filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                if (source.all { it.isDigit() || it == ',' }) null else ""
            })
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
        }

        // Entrada del número objetivo
        val input = EditText(context).apply {
            hint = "Número a buscar"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
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

        // Resultado de la operación
        val resultado = TextView(context).apply {
            text = "Resultado"
            textSize = 18f
            setTextColor(Color.DKGRAY)
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Botón para buscar ocurrencias
        val buttonOperation = Button(context).apply {
            text = "Buscar frecuencia"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { gravity = Gravity.CENTER_HORIZONTAL }

            setOnClickListener {
                if (validation(input.text.toString(), arrayInput.text.toString(), context)) {
                    try {
                        val arrayInputUser = arrayInput.text.toString()
                            .split(",")
                            .filter { it.trim().isNotEmpty() }
                            .map { it.toInt() }

                        val goal = input.text.toString().toInt()

                        if (isInArray(arrayInputUser, goal)) {
                            val result = elementTimesInArray(arrayInputUser, goal)
                            resultado.text = "Veces que aparece: $result"
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

        // Agregar vistas al layout principal
        layout.apply {
            addView(titulo)
            addView(arrayInput)
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }

        return layout
    }

    // Verifica si el número está presente en el array
    private fun isInArray(array: List<Int>, goal: Int): Boolean {
        return goal in array
    }

    // Validación de que ambos campos estén completos
    private fun validation(arrayInput: String, input1: String, context: Context): Boolean {
        if (input1.isEmpty() || arrayInput.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, complete ambos campos.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    // Cuenta cuántas veces aparece un número en el array
    private fun elementTimesInArray(array: List<Int>, goal: Int): Int {
        var result = 0
        for (i in 0 until array.size) {
            if (array[i] == goal) {
                result++
            }
        }
        return result
    }
}