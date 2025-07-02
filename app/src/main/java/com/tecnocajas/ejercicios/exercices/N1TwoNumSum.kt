// Clase N1: Suma de dos números
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

class N1TwoNumSum : ExerciseInterface {
    override var ID: Int = 1
    override var title: String = "Suma de dos números"
    override var description: String = "Suma dos números validando que los datos ingresados sean correctos."

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

        // Botón para mostrar resultado
        val resultButton = Button(context).apply {
            text = "Mostrar resultado"
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
                val value1 = numberInput1.text.toString()
                val value2 = numberInput2.text.toString()

                // Validar que ambos campos estén completos
                if (validateInputs(value1, value2, context)) {
                    try {
                        val num1 = value1.toDouble()
                        val num2 = value2.toDouble()
                        val sumResult = sum(num1, num2)
                        resultTextView.text = "Resultado: $sumResult"
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
            addView(numberInput1)
            addView(numberInput2)
            addView(resultButton)
            addView(resultTextView)
        }

        return mainLayout
    }

    // Validar que los dos campos no estén vacíos
    private fun validateInputs(
        input1: String,
        input2: String,
        context: Context
    ): Boolean {
        return if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, complete ambos campos antes de continuar.",
                Toast.LENGTH_SHORT
            ).show()
            false
        } else true
    }

    // Operación suma de dos números
    private fun sum(
        number1: Double,
        number2: Double
    ): Double {
        return number1 + number2
    }
}
