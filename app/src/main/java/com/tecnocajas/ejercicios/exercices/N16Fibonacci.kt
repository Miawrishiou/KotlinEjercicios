package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.text.InputFilter
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tecnocajas.ejercicios.R

class N16Fibonacci : ExerciseInterface {
    override var ID = 16
    override var title = "Fibonacci con Recursividad"
    override var description = "Calcula el número de Fibonacci con recursividad"

    override fun makeContainer(context: Context): View {
        // Cargar fuentes Rubik
        val fontBold = ResourcesCompat.getFont(context, R.font.rubik_bold)
        val fontRegular = ResourcesCompat.getFont(context, R.font.rubik_regular)
        val fontSemiBold = ResourcesCompat.getFont(context, R.font.rubik_semibold)

        // Contenedor principal
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

        // Entrada del índice para generar Fibonacci
        val input = EditText(context).apply {
            hint = "Longitud de la secuencia (1 a 20)"
            inputType = InputType.TYPE_CLASS_NUMBER
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

        // Texto de resultado
        val resultado = TextView(context).apply {
            text = "Presiona el botón para calcular la secuencia"
            textSize = 18f
            setTextColor(Color.DKGRAY)
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Botón de operación
        val buttonOperation = Button(context).apply {
            text = "Fibonacci"
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
                        val result = fibonacci(
                            input.text.toString().toInt(),
                            listOf(),
                            0,
                            1
                        )
                        resultado.text = "Resultado: ${result.joinToString(", ")}"
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

        // Agregar todos los elementos al layout
        layout.apply {
            addView(titulo)
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }

        return layout
    }

    // Validación del input
    private fun validation(input: String, context: Context): Boolean {
        if (input.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un valor para la longitud de la secuencia.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (input.toInt() < 1 || input.toInt() > 20) {
            Toast.makeText(context, "Por favor, ingrese un valor mayor a 1 y menor a 20", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // Generación recursiva de la secuencia de Fibonacci
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