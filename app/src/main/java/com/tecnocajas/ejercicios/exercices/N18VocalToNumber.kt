package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tecnocajas.ejercicios.R

class N18VocalToNumber : ExerciseInterface {
    override var ID = 18
    override var title = "Vocales a números"
    override var description = "Convertir las vocales a números"

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

        // Entrada de texto
        val input = EditText(context).apply {
            hint = "Ingrese su texto"
            inputType = InputType.TYPE_CLASS_TEXT
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

        // Botón de conversión
        val buttonOperation = Button(context).apply {
            text = "Convierte esta cadena"
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
                        val result = vocalToNumber(input.text.toString())
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

        // Construcción del layout
        layout.apply {
            addView(titulo)
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }

        return layout
    }

    // Validación de que el campo no esté vacío
    private fun validation(input: String, context: Context): Boolean {
        if (input.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un texto.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // Método para convertir vocales a números
    private fun vocalToNumber(text: String): String {
        val vocalsNumbers = mapOf(
            'a' to '1',
            'e' to '2',
            'i' to '3',
            'o' to '4',
            'u' to '5'
        )
        var result = ""
        for (char in text) {
            if (char.lowercaseChar() in vocalsNumbers) {
                result += vocalsNumbers[char.lowercaseChar()]
            } else {
                result += char
            }
        }
        return result
    }
}