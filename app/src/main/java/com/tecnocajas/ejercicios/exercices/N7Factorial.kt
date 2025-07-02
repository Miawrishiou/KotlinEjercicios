// Clase N7: Factorial de un número
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

class N7Factorial : ExerciseInterface {
    override var ID: Int = 7
    override var title: String = "Factorial"
    override var description: String = "Calcular el factorial de un número con validación previa"

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

        // Botón para calcular factorial
        val resultButton = Button(context).apply {
            text = "Calcular factorial"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24); gravity = Gravity.CENTER_HORIZONTAL }

            setOnClickListener {
                // Validar entrada antes de calcular
                val inputText = numberInput.text.toString()
                if (validateInput(inputText, context)) {
                    try {
                        val value = inputText.toInt()
                        val factorialResult = factorial(value)
                        resultTextView.text = "El factorial de $value es: $factorialResult"
                    } catch (e: NumberFormatException) {
                        // Notificación de error de formato
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

    // Calcular factorial de un número (lógica intacta)
    private fun factorial(
        input: Int
    ): Int {
        var acumulator = input
        for (i in 1 .. input) {
            if (i == input) {
                continue
            }
            acumulator*=i
        }
        return acumulator
    }
}