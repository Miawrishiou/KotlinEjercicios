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

class N12ElementsArraySum : ExerciseInterface {
    override var ID = 12
    override var title = "Suma de elementos de un array"
    override var description = "Realiza la suma de los elementos de un array"

    override fun makeContainer(context: Context): View {
        // Cargar tipografías Rubik desde res/font/
        val fontBold = ResourcesCompat.getFont(context, R.font.rubik_bold)
        val fontRegular = ResourcesCompat.getFont(context, R.font.rubik_regular)
        val fontSemiBold = ResourcesCompat.getFont(context, R.font.rubik_semibold)

        // Contenedor principal vertical
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

        // Campo de entrada para el primer array
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
            ).apply { setMargins(0, 0, 0, 16) }
        }

        // Campo de entrada para el segundo array
        val arrayInput2 = EditText(context).apply {
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

        // Resultado visual de la suma
        val resultado = TextView(context).apply {
            text = "Dale al botón."
            textSize = 18f
            setTextColor(Color.DKGRAY)
            typeface = fontRegular
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 24, 0, 0) }
        }

        // Botón para operar la suma de arrays
        val buttonOperation = Button(context).apply {
            text = "Sumar los dos arreglos"
            typeface = fontSemiBold
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { gravity = Gravity.CENTER_HORIZONTAL }

            setOnClickListener {
                if (validation(arrayInput1.text.toString(), arrayInput2.text.toString(), context)) {
                    try {
                        val arrayUser1 = arrayInput1.text.toString()
                            .split(",").filter { it.trim().isNotEmpty() }.map { it.toInt() }

                        val arrayUser2 = arrayInput2.text.toString()
                            .split(",").filter { it.trim().isNotEmpty() }.map { it.toInt() }

                        val result = sumTwoArrays(arrayUser1, arrayUser2)
                        resultado.text = "El array sumado es: ${result.joinToString(", ")}"
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

        // Agregar todos los elementos al layout
        layout.apply {
            addView(titulo)
            addView(arrayInput1)
            addView(arrayInput2)
            addView(buttonOperation)
            addView(resultado)
        }

        return layout
    }

    // Validación de campos: ambos deben estar llenos
    private fun validation(input1: String, input2: String, context: Context): Boolean {
        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(
                context,
                "Por favor, ingrese un array de números en ambos campos.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    // Sumar dos arrays elemento por elemento
    private fun sumTwoArrays(array1: List<Int>, array2: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        val mayorIndex = maxOf(array1.size, array2.size)
        for (i in 0 until mayorIndex) {
            val actualSum = array1.getOrElse(i) { 0 } + array2.getOrElse(i) { 0 }
            result.add(actualSum)
        }
        return result
    }
}