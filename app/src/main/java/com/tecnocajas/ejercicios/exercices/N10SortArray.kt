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
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tecnocajas.ejercicios.R

class N10SortArray : ExerciseInterface {
    override var ID: Int = 10
    override var title: String = "Ordenar un Array"
    override var description: String = "Ordena un array de números enteros de menor a mayor"

    override fun makeContainer(context: Context): View {
        // Cargar tipografías Rubik desde res/font/
        val fontBold = ResourcesCompat.getFont(context, R.font.rubik_bold)
        val fontRegular = ResourcesCompat.getFont(context, R.font.rubik_regular)
        val fontSemiBold = ResourcesCompat.getFont(context, R.font.rubik_semibold)

        // Contenedor principal vertical, centrado y con padding
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

        // Campo de entrada para el array de números (como texto)
        val arrayInput = EditText(context).apply {
            hint = "Ej: 3,1,4,1,5"
            inputType = InputType.TYPE_CLASS_TEXT
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(16, 8, 16, 8)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }

            // Filtro para permitir solo números y comas
            filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                if (source.all { it.isDigit() || it == ',' }) null else ""
            })
        }

        // Resultado mostrado al usuario después de ordenar
        val resultado = TextView(context).apply {
            text = "Dale a Ord. para Ordenar"
            textSize = 18f
            setTextColor(Color.DKGRAY)
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 24, 0, 0) }
        }

        // Botón que ejecuta la ordenación
        val buttonOperation = Button(context).apply {
            text = "Ordenar"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER_HORIZONTAL
            }

            setOnClickListener {
                // Validar entrada
                if (validation(arrayInput.text.toString(), context)) {
                    try {
                        // Convertir texto a lista de enteros
                        val arrayInputUser = arrayInput.text.toString()
                            .split(",")
                            .filter { it.trim().isNotEmpty() }
                            .map { it.toInt() }

                        // Ordenar usando QuickSort recursivo
                        val result = sort(arrayInputUser)

                        // Mostrar resultado
                        resultado.text = "El array ordenado es: ${result.joinToString(", ")}"
                    } catch (e: NumberFormatException) {
                        Toast.makeText(
                            context,
                            "Error en la conversión de entrada a array, ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // Agregar los elementos visuales al layout en orden lógico
        layout.apply {
            addView(titulo)
            addView(arrayInput)
            addView(buttonOperation)
            addView(resultado)
        }

        return layout
    }

    // Validación de campo vacío
    private fun validation(input1: String, context: Context): Boolean {
        if (input1.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un array de números.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // Algoritmo QuickSort funcional y recursivo
    fun sort(arrayNumbers: List<Int>): List<Int> {
        if (arrayNumbers.size < 2) return arrayNumbers

        val pivot = arrayNumbers[arrayNumbers.size / 2]
        val greater = arrayNumbers.filter { it > pivot }
        val equal = arrayNumbers.filter { it == pivot }
        val lesser = arrayNumbers.filter { it < pivot }

        return sort(lesser) + equal + sort(greater)
    }
}
