// Clase N2: Promedio de tres números
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

class N2ThreeNumberProm : ExerciseInterface {
    override var ID: Int = 2
    override var title: String = "Promedio de tres números"
    override var description: String = "Calcular el promedio de tres números con validación previa"

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

        // Primera entrada de número
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

        // Segunda entrada de número
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
            ).apply { setMargins(0, 8, 0, 8) }
        }

        // Tercera entrada de número
        val numberInput3 = EditText(context).apply {
            hint = "Ingrese el tercer número"
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

        // Botón para calcular promedio
        val resultButton = Button(context).apply {
            text = "Calcular promedio"
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
                // Validar entradas antes de calcular
                if (validateInputs(
                        numberInput1.text.toString(),
                        numberInput2.text.toString(),
                        numberInput3.text.toString(),
                        context
                    )
                ) {
                    try {
                        val num1 = numberInput1.text.toString().toDouble()
                        val num2 = numberInput2.text.toString().toDouble()
                        val num3 = numberInput3.text.toString().toDouble()

                        val average = calculateAverage(num1, num2, num3)
                        resultTextView.text = "El promedio de los tres números es: $average"
                    } catch (e: NumberFormatException) {
                        // Notificación de error en conversión de valores
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
            addView(numberInput1)
            addView(numberInput2)
            addView(numberInput3)
            addView(resultButton)
            addView(resultTextView)
        }

        return mainLayout
    }

    // Validar que los tres campos no estén vacíos
    private fun validateInputs(
        value1: String,
        value2: String,
        value3: String,
        context: Context
    ): Boolean {
        return if (value1.isEmpty() || value2.isEmpty() || value3.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, complete los tres campos antes de continuar.",
                Toast.LENGTH_SHORT
            ).show()
            false
        } else true
    }

    // Calcular promedio de tres números
    private fun calculateAverage(
        number1: Double,
        number2: Double,
        number3: Double
    ): Double {
        return (number1 + number2 + number3) / 3
    }
}