// Clase N8: Número primo
package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
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

class N8PrimeNumber : ExerciseInterface {
    override var ID: Int = 8
    override var title: String = "Número primo"
    override var description: String = "Determina si un número es primo o no con validación previa"

    override fun makeContainer(context: Context): View {
        // Cargar tipografías Rubik
        val fontBold = ResourcesCompat.getFont(context, R.font.rubik_bold)
        val fontRegular = ResourcesCompat.getFont(context, R.font.rubik_regular)
        val fontSemiBold = ResourcesCompat.getFont(context, R.font.rubik_semibold)

        // Contenedor principal vertical centrado
        val mainLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(32, 32, 32, 32)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        // Título del ejercicio
        val titleTextView = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.parseColor("#222222"))
            typeface = fontBold
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        // Campo de entrada para el número
        val numberInput = EditText(context).apply {
            hint = "Ingrese un número"
            inputType = InputType.TYPE_CLASS_NUMBER
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(24, 16, 24, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        // Texto de resultado
        val resultTextView = TextView(context).apply {
            text = "Resultado: "
            textSize = 16f
            setTextColor(Color.DKGRAY)
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Botón para evaluar primalidad
        val resultButton = Button(context).apply {
            text = "¿Es primo?"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24); gravity = Gravity.CENTER_HORIZONTAL }

            setOnClickListener {
                // Validar entrada antes de evaluar
                val inputText = numberInput.text.toString()
                if (validateInput(inputText, context)) {
                    try {
                        val inputNumber = inputText.toInt()
                        val resultString = primeNumber(inputNumber)
                        resultTextView.text = "El número $resultString"
                    } catch (e: NumberFormatException) {
                        // Notificación de error de conversión
                        Toast.makeText(
                            context,
                            "Error: formato de número inválido. Detalle: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // Agregar vistas al layout principal
        mainLayout.apply {
            addView(titleTextView)
            addView(numberInput)
            addView(resultButton)
            addView(resultTextView)
        }

        return mainLayout
    }

    // Validar que el campo no esté vacío
    private fun validateInput(
        value: String,
        context: Context
    ): Boolean {
        return if (value.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, ingrese un número antes de continuar.",
                Toast.LENGTH_SHORT
            ).show()
            false
        } else true
    }

    // Determina si un número es primo contando divisores
    private fun primeNumber(
        input1: Int
    ): String {
        var resultado = "${input1} no es Primo"
        var divisors = 0
        when (input1) {
            0 -> resultado = "${input1} no es primo"
            1 -> resultado = "${input1} no es primo"
            else -> {
                for (i in 1..input1) {
                    if (input1 % i == 0) {
                        divisors++
                    }
                }
                if (divisors == 2) {
                    resultado = "${input1} es Primo"
                }
            }
        }
        return resultado
    }
}
