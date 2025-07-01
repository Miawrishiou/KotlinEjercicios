package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class N18VocalToNumber : ExerciseInterface {
    override var ID = 18
    override var title = "Vocales a numeros"
    override var description = "Convertir las vocales a numeros"

    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        /*Entradas de la cantidad de digitos para mostrar en el numero de fibonacci*/
        val input = EditText(context).apply {
            hint = "Ingrese su texto"
            inputType = InputType.TYPE_CLASS_TEXT
            setTextColor(Color.BLACK)
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Dale al boton."
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val buttonOperation = Button(context).apply {
            text = "Convierte esta cadena"

            setOnClickListener {
                if (validation(input.text.toString(), context)) {
                    try {
                        var result = vocalToNumber(input.text.toString())
                        resultado.text = "Resultado: ${result}"
                    } catch (e : NumberFormatException) {
                        Toast.makeText(context, "Error en la conversion de entrada a array, ${e.message}", Toast.LENGTH_SHORT).show()
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
            addView(input)
            addView(buttonOperation)
            addView(resultado)
        }
        return layout
    }
    /*Validacion de que las entradas sean las correctas*/
    private fun validation(input: String, context: Context): Boolean {
        //Validacion de que el elemento ingresado al menos tenga un numero
        if (input.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese un valor para la longitud de la secuencia.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    /*Metodo para convertir las vocales a numeros*/
    private fun vocalToNumber(text: String) : String{
        val vocalsNumbers = mapOf(
            'a' to '1',
            'e' to '2',
            'i' to '3',
            'o' to '4',
            'u' to '5'
        )
        var result = ""
        for (char in text) {
            if (char.toLowerCase() in vocalsNumbers) {
                result += vocalsNumbers[char.toLowerCase()]
            } else {
                result += char
            }
        }
        return result
    }
}