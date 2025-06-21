package com.tecnocajas.ejercicios.exercices
import android.content.Context
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
import com.tecnocajas.ejercicios.R
import androidx.core.content.ContextCompat

class N5UnitConverter : ExerciseInterface {
    override var ID = 5
    override var title = "Conversor de Unidades, metros a centimetros."
    override var description = "Convierte cualquier tipo de unidad a centimetro o metros."
    private var operation: String = "cm"
    override fun makeContainer(context: Context): View {
        /*Layout principal*/
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        val units = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(5, 5, 5, 5)
        }
        val unity = TextView(context).apply {

            text = "CM"
            textSize = 18f
            setTextColor(Color.RED)
            setPadding(5, 5, 5, 5)
        }
        val a = TextView(context).apply {
            text = " a "
            textSize = 18f
            setTextColor(Color.BLACK)
            setPadding(5, 5, 5, 5)
        }
        val toConvert = TextView(context).apply {
            text = "M"
            textSize = 18f
            setTextColor(Color.RED)
            setPadding(5, 5, 5, 5)
        }
        val changeConvertion = Button(context).apply {
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            text = "Cambiar"
            setOnClickListener {
                if (operation == "cm") {
                    operation = "m"
                    unity.text = "M"
                    toConvert.text = "CM"
                } else {
                    operation = "cm"
                    unity.text = "CM"
                    toConvert.text = "M"
                }
            }
        }
        /*aplicar a todo el contenedor*/
        units.apply {
            addView(unity)
            addView(a)
            addView(toConvert)
            addView(changeConvertion)
        }
        /*Entradas de los numeros*/
        val input1 = EditText(context).apply {
            hint = "Número 1"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
        /*Resultados*/
        val resultado = TextView(context).apply {
            text = "Resultado: "
            setTextColor(Color.LTGRAY)
            textSize = 18f
        }
        /*Realizar la el promedio*/
        val boton = Button(context).apply {
            text = "Conviertelo"

            setOnClickListener {
                if (validation(input1.text.toString(), context)) {
                    try {
                        var input1 = input1.text.toString().toDouble()

                        var result = convert(input1)
                        resultado.text = "De ${unity.text} a ${toConvert.text} es: ${result}"
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
        val descripcion = TextView(context).apply {
            text = description
            textSize = 18f
            setTextColor(Color.LTGRAY)
        }
        layout.apply {
            addView(titulo)
            addView(units)
            addView(input1)
            addView(boton)
            addView(resultado)
        }
        return layout
    }
    private fun validation(input: String, context: Context) : Boolean {
        if (input.isEmpty()) {
            Toast.makeText(context, "Por favor, revisa los campos marcados. Hay valores nulos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun convert(input1: Double): Double {
        var resultado: Double = 0.0
        when (operation) {
            "cm" -> resultado = input1.toDouble() / 100
            "m" -> resultado =  input1.toDouble() * 100
        }
        return resultado
    }
}