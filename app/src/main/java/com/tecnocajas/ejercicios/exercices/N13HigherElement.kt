package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tecnocajas.ejercicios.R

class N13HigherElement : ExerciseInterface {
    override var ID = 13
    override var title = "Elemento mayor de un array"
    override var description = "Encuentra el elemento mayor dentro de un array de números"

    override fun makeContainer(context: Context): View {
        // Cargar tipografías Rubik
        val fontBold = ResourcesCompat.getFont(context, R.font.rubik_bold)
        val fontRegular = ResourcesCompat.getFont(context, R.font.rubik_regular)
        val fontSemiBold = ResourcesCompat.getFont(context, R.font.rubik_semibold)

        // Layout principal
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

        // Campo de entrada de array
        val arrayInput1 = EditText(context).apply {
            hint = "Ej: 3,1,4,1,5"
            inputType = InputType.TYPE_CLASS_TEXT
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(16, 8, 16, 8)
            filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                if (source.all { it.isDigit() || it == ',' }) null else ""
            })
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
        }

        // Texto de resultado
        val resultado = TextView(context).apply {
            text = "Presiona el botón para obtener el mayor"
            textSize = 18f
            setTextColor(Color.DKGRAY)
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 24, 0, 0) }
        }

        // Botón para obtener el mayor elemento
        val buttonOperation = Button(context).apply {
            text = "Obtener el mayor"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { gravity = Gravity.CENTER_HORIZONTAL }

            setOnClickListener {
                if (validation(arrayInput1.text.toString(), context)) {
                    try {
                        val arrayInputUser1 = arrayInput1.text.toString()
                            .split(",")
                            .filter { it.trim().isNotEmpty() }
                            .map { it.toInt() }

                        val result = higherElement(arrayInputUser1)
                        resultado.text = "El elemento mayor es: $result"
                    } catch (e: NumberFormatException) {
                        Toast.makeText(
                            context,
                            "Error en la conversión de entrada: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // Agregar componentes al layout
        layout.apply {
            addView(titulo)
            addView(arrayInput1)
            addView(buttonOperation)
            addView(resultado)
        }

        return layout
    }

    // Validación de entrada vacía
    private fun validation(input1: String, context: Context): Boolean {
        if (input1.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, ingrese un array de números.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    // Lógica para encontrar el elemento mayor
    private fun higherElement(array: List<Int>): Int {
        var higherActualElement = -1
        for (i in 0 until array.size) {
            if (array[i] >= higherActualElement) {
                higherActualElement = array[i]
            }
        }
        return higherActualElement
    }
}