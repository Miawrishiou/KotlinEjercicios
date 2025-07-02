package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tecnocajas.ejercicios.R

class N20DecimalToRoman : ExerciseInterface {
    override var ID = 20
    override var title = "Decimal a romano"
    override var description = "Realiza la conversión de un número en base decimal a un número romano"

    override fun makeContainer(context: Context): View {
        // Cargar tipografías Rubik
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

        // Título
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

        // Entrada numérica
        val input = EditText(context).apply {
            hint = "Ingrese su número."
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

        // Resultado
        val resultado = TextView(context).apply {
            text = "Presiona el botón para convertir"
            textSize = 18f
            setTextColor(Color.DKGRAY)
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Botón de acción
        val buttonOperation = Button(context).apply {
            text = "Convierte esta cadena"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER_HORIZONTAL
                setMargins(0, 16, 0, 24)
            }

            setOnClickListener {
                if (validation(input.text.toString(), context)) {
                    try {
                        val result = numberToRoman(input.text.toString())
                        resultado.text = "Resultado: ${result}"
                    } catch (e: NumberFormatException) {
                        Toast.makeText(
                            context,
                            "Error en la conversión de entrada, ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // Añadir elementos al layout
        layout.apply {
            addView(titulo)
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }

        return layout
    }

    // Validación de entrada
    private fun validation(input: String, context: Context): Boolean {
        if (input.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un número.", Toast.LENGTH_SHORT).show()
            return false
        }
        val value = input.toInt()
        if (value < 1 || value > 3999) {
            Toast.makeText(context, "Por favor, ingrese un valor entre 1 y 3999", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // Conversión a número romano
    private fun numberToRoman(num: String): String {
        val units = mapOf(1 to "I", 2 to "II", 3 to "III", 4 to "IV", 5 to "V", 6 to "VI", 7 to "VII", 8 to "VIII", 9 to "IX")
        val tens = mapOf(1 to "X", 2 to "XX", 3 to "XXX", 4 to "XL", 5 to "L", 6 to "LX", 7 to "LXX", 8 to "LXXX", 9 to "XC")
        val hundreds = mapOf(1 to "C", 2 to "CC", 3 to "CCC", 4 to "CD", 5 to "D", 6 to "DC", 7 to "DCC", 8 to "DCCC", 9 to "CM")
        val thousands = mapOf(1 to "M", 2 to "MM", 3 to "MMM")

        val result = StringBuilder("")
        val number = numberComposition(num)

        for (index in number.length - 1 downTo 0) {
            val digit = number[index].toString().toInt()
            when (index) {
                0 -> result.insert(0, thousands.getOrDefault(digit, ""))
                1 -> result.insert(0, hundreds.getOrDefault(digit, ""))
                2 -> result.insert(0, tens.getOrDefault(digit, ""))
                3 -> result.insert(0, units.getOrDefault(digit, ""))
            }
        }

        return result.toString()
    }

    // Composición del número como string de 4 dígitos
    private fun numberComposition(number: String): String {
        val numberComposed = StringBuilder(number)
        while (numberComposed.length < 4) {
            numberComposed.insert(0, "0")
        }
        return numberComposed.toString()
    }
}