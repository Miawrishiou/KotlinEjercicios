package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.Editable
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.tecnocajas.ejercicios.R

class N9Calculator : ExerciseInterface {
    override var ID = 9
    override var title = "Calculadora"
    override var description = "Realiza operaciones aritmeticas con dos numeros."
    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        /*Contenedor para los numero y la operacion que se introduzca*/
        val expression = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        val input1 = EditText(context).apply {
            hint = "Número 1"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
            layoutParams = LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }
        val operation = EditText(context).apply {
            hint = "Número 1"
            inputType = InputType.TYPE_CLASS_TEXT
            layoutParams = LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                if (source.matches(Regex("[+\\-*/]*"))) {
                    return@InputFilter source
                }
                return@InputFilter ""
            })
        }
        val input2 = EditText(context).apply {
            hint = "Número 2"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL  or InputType.TYPE_NUMBER_FLAG_SIGNED
            layoutParams = LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }
        /*aplicar a todo el contenedor*/
        expression.apply {
            addView(input1)
            addView(operation)
            addView(input2)
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Resultado: "
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val boton = Button(context).apply {
            text = "Operar"

            setOnClickListener {
                if (validation(input1.text.toString(), operation.text.toString(), input2.text.toString(), context)) {
                    try {
                        var input1 = input1.text.toString().toDouble()
                        var input2 = input2.text.toString().toDouble()
                        var operation = operation.text.toString()
                        var result = operation(input1, input2, operation, context)
                        resultado.text = "El resultado es: ${result}"
                    } catch (e : NumberFormatException) {
                        Toast.makeText(context, "Error en la conversion de valores a double, ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        val titulo = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
        }
        layout.apply {
            addView(titulo)
            addView(expression)
            addView(boton)
            addView(resultado)
        }
        return layout
    }

    private fun validation(input1: String, expression: String, input2: String, context : Context) : Boolean {
        if (input1.isEmpty() || expression.isEmpty() || input2.isEmpty()) {
            Toast.makeText(context, "Por favor, revisa los campos marcados. Hay valores nulos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun operation(input1: Double, input2: Double, operation: String, context: Context): Double {
        var resultado: Double = 0.0
        when (operation) {
            "+" -> resultado = input1 + input2
            "-" -> resultado = input1 - input2
            "x" -> resultado = input1 * input2
            "/" -> if (input2 != 0.0) {
                resultado = input1 / input2
            } else {
                Toast.makeText(context, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
            }
        }
        return resultado
    }
}