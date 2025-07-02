// Clase N3: Mayor de dos números
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

class N3TwoNumberMayor : ExerciseInterface {
    override var ID: Int = 3
    override var title: String = "Mayor de dos números"
    override var description: String = "Evaluar dos números y devolver el mayor de ellos"

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

        // Campo de entrada para primer número
        val numberInput1 = EditText(context).apply {
            hint = "Ingrese el primer número"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(24, 16, 24, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 8, 0, 8) }
        }

        // Campo de entrada para segundo número
        val numberInput2 = EditText(context).apply {
            hint = "Ingrese el segundo número"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
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

        // Botón para calcular el mayor
        val resultButton = Button(context).apply {
            text = "Calcular mayor"
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
                // Validar que ambos campos estén completos
                if (validateInputs(
                        numberInput1.text.toString(),
                        numberInput2.text.toString(),
                        context
                    )
                ) {
                    try {
                        val num1 = numberInput1.text.toString().toDouble()
                        val num2 = numberInput2.text.toString().toDouble()
                        val mayor = mayorNumber(num1, num2)
                        resultTextView.text = "El número mayor es: $mayor"
                    } catch (e: NumberFormatException) {
                        // Notificación de error de formato numérico
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
            addView(numberInput1)
            addView(numberInput2)
            addView(resultButton)
            addView(resultTextView)
        }

        return mainLayout
    }

    // Validar que los dos campos no estén vacíos
    private fun validateInputs(
        value1: String,
        value2: String,
        context: Context
    ): Boolean {
        return if (value1.isEmpty() || value2.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, complete ambos campos antes de continuar.",
                Toast.LENGTH_SHORT
            ).show()
            false
        } else true
    }

    // Determinar cuál número es mayor o igualdad
    private fun mayorNumber(
        number1: Double,
        number2: Double
    ): String {
        return when {
            number1 > number2 -> number1.toString()
            number2 > number1 -> number2.toString()
            else -> "Los números son iguales"
        }
    }
}
