// Clase N9: Calculadora de dos números
package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
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

class N9Calculator : ExerciseInterface {
    override var ID: Int = 9
    override var title: String = "Calculadora"
    override var description: String = "Realiza operaciones aritméticas con dos números con validación previa"

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

        // Contenedor horizontal para expresión (simetría centrada)
        val expressionLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        // Primer operando
        val firstOperandInput = EditText(context).apply {
            hint = "N1"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(16, 8, 16, 8)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
                setMargins(0, 0, 8, 0)
            }
        }

        // Operador
        val operatorInput = EditText(context).apply {
            hint = "Signo"
            inputType = InputType.TYPE_CLASS_TEXT
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            filters = arrayOf(InputFilter { source, _, _, dest, _, _ ->
                if (dest.isEmpty() && source.length == 1 && source.matches(Regex("[+\\-*/]"))) {
                    return@InputFilter source
                }
                return@InputFilter ""
            })
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
                setMargins(8, 0, 8, 0)
            }
        }

        // Segundo operando
        val secondOperandInput = EditText(context).apply {
            hint = "N2"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(16, 8, 16, 8)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
                setMargins(8, 0, 0, 0)
            }
        }

        // Agregar campos de expresión
        expressionLayout.apply {
            addView(firstOperandInput)
            addView(operatorInput)
            addView(secondOperandInput)
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

        // Botón para operar
        val computeButton = Button(context).apply {
            text = "Operar"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24); gravity = Gravity.CENTER_HORIZONTAL }

            setOnClickListener {
                // Validar antes de operar
                if (validateInputs(
                        firstOperandInput.text.toString(),
                        operatorInput.text.toString(),
                        secondOperandInput.text.toString(),
                        context
                    )
                ) {
                    try {
                        val num1 = firstOperandInput.text.toString().toDouble()
                        val num2 = secondOperandInput.text.toString().toDouble()
                        val op = operatorInput.text.toString()
                        val result = operation(num1, num2, op, context)
                        resultTextView.text = "El resultado es: $result"
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

        // Construir layout principal
        mainLayout.apply {
            addView(titleTextView)
            addView(expressionLayout)
            addView(computeButton)
            addView(resultTextView)
        }

        return mainLayout
    }

    // Validar que ninguno de los campos esté vacío
    private fun validateInputs(
        operand1: String,
        operator: String,
        operand2: String,
        context: Context
    ): Boolean {
        return if (operand1.isEmpty() || operator.isEmpty() || operand2.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, complete todos los campos antes de continuar.",
                Toast.LENGTH_SHORT
            ).show()
            false
        } else true
    }

    // Realiza la operación indicada
    private fun operation(
        input1: Double,
        input2: Double,
        operation: String,
        context: Context
    ): Double {
        var resultado = 0.0
        when (operation) {
            "+" -> resultado = input1 + input2
            "-" -> resultado = input1 - input2
            "x", "*" -> resultado = input1 * input2
            "/" -> if (input2 != 0.0) {
                resultado = input1 / input2
            } else {
                Toast.makeText(context, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
            }
        }
        return resultado
    }
}
