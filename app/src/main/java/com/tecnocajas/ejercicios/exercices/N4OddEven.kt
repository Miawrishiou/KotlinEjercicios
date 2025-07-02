// Clase N4: Par o impar
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

class N4OddEven : ExerciseInterface {
    override var ID: Int = 4
    override var title: String = "Par o impar"
    override var description: String = "Evaluar si un número es par o impar con validación previa"

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
            ).apply { setMargins(0, 8, 0, 24) }
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

        // Botón para evaluar par o impar
        val resultButton = Button(context).apply {
            text = "Evaluar par/impar"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER_HORIZONTAL
                setMargins(0, 0, 0, 24)
            }

            setOnClickListener {
                // Validar campo antes de evaluar
                if (validateInput(numberInput.text.toString(), context)) {
                    try {
                        val number = numberInput.text.toString().toInt()
                        val parity = oddEven(number)
                        resultTextView.text = "El número ingresado $parity"
                    } catch (e: NumberFormatException) {
                        // Notificación de error de conversión
                        Toast.makeText(
                            context,
                            "Error: formato numérico inválido. Detalle: ${e.message}",
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

    // Determinar si un número es par o impar
    private fun oddEven(
        number: Int
    ): String {
        return if (number % 2 == 0) "es par" else "es impar"
    }
}
