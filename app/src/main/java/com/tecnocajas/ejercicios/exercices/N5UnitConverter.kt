// Clase N5: Conversor de unidades (metros ↔ centímetros)
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

class N5UnitConverter : ExerciseInterface {
    override var ID: Int = 5
    override var title: String = "Conversor de unidades: metros ↔ centímetros"
    override var description: String = "Convierte valores entre metros y centímetros con validación previa"

    // Operación actual: "cm" convierte de cm a m, "m" convierte de m a cm
    private var conversionOperation: String = "cm"

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

        // Layout horizontal para unidades (simetría centrada)
        val unitsLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        // TextView unidad origen
        val unitFromTextView = TextView(context).apply {
            text = "CM"
            textSize = 18f
            setTextColor(Color.parseColor("#DD2C00"))
            typeface = fontSemiBold
            setPadding(8, 0, 8, 0)
        }

        // Separador
        val arrowTextView = TextView(context).apply {
            text = "→"
            textSize = 18f
            setTextColor(Color.parseColor("#444444"))
            setPadding(8, 0, 8, 0)
        }

        // TextView unidad destino
        val unitToTextView = TextView(context).apply {
            text = "M"
            textSize = 18f
            setTextColor(Color.parseColor("#DD2C00"))
            typeface = fontSemiBold
            setPadding(8, 0, 8, 0)
        }

        // Botón para cambiar dirección de conversión
        val changeButton = Button(context).apply {
            text = "Cambiar"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            setOnClickListener {
                // Intercambiar operación y etiquetas
                if (conversionOperation == "cm") {
                    conversionOperation = "m"
                    unitFromTextView.text = "M"
                    unitToTextView.text = "CM"
                } else {
                    conversionOperation = "cm"
                    unitFromTextView.text = "CM"
                    unitToTextView.text = "M"
                }
            }
        }

        unitsLayout.apply {
            addView(unitFromTextView)
            addView(arrowTextView)
            addView(unitToTextView)
            addView(changeButton)
        }

        // Campo de entrada de valor
        val numberInput = EditText(context).apply {
            hint = "Ingrese el valor a convertir"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
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

        // Botón para convertir unidad
        val convertButton = Button(context).apply {
            text = "Convertir"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24); gravity = Gravity.CENTER_HORIZONTAL }

            setOnClickListener {
                // Validar entrada antes de convertir
                if (validateInput(numberInput.text.toString(), context)) {
                    try {
                        val value = numberInput.text.toString().toDouble()
                        val converted = convert(value)
                        resultTextView.text = "${unitFromTextView.text} → ${unitToTextView.text}: $converted"
                    } catch (e: NumberFormatException) {
                        Toast.makeText(
                            context,
                            "Error: formato numérico inválido. Detalle: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // Agregar todas las vistas al layout principal
        mainLayout.apply {
            addView(titleTextView)
            addView(unitsLayout)
            addView(numberInput)
            addView(convertButton)
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
                "Por favor, ingrese un valor para convertir.",
                Toast.LENGTH_SHORT
            ).show()
            false
        } else true
    }

    // Lógica de conversión entre cm y m
    private fun convert(
        input: Double
    ): Double {
        return if (conversionOperation == "cm") {
            // Centímetros a metros
            input / 100
        } else {
            // Metros a centímetros
            input * 100
        }
    }
}