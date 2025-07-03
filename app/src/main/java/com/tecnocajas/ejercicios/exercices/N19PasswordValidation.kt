package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import com.tecnocajas.ejercicios.R

class N19PasswordValidation : ExerciseInterface {
    override var ID = 19
    override var title = "Validación de contraseña"
    override var description = "Valida si una contraseña es segura o no"

    override fun makeContainer(context: Context): View {
        // Cargar fuentes Rubik
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

        //Contenedor para la contraseña y el Icono de ver contraseña
        val layoutPassword = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.FILL_HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        // Entrada de la contraseña
        val input = EditText(context).apply {
            hint = "Contraseña"
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            transformationMethod = PasswordTransformationMethod.getInstance()
            typeface = fontRegular
            setTextColor(Color.parseColor("#222222"))
            setHintTextColor(Color.parseColor("#888888"))
            setBackgroundColor(Color.parseColor("#EEEEEE"))
            setPadding(16, 8, 16, 8)
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            ).apply { setMargins(0, 0, 30, 24) }
        }
        //Boton toggle para mostrar o ocultar la contraseña
        val togglePassword = Button(context).apply {
            var state: Boolean = false
            text = "👁️"
            background = ContextCompat.getDrawable(context, R.drawable.button_activities)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(8,0,0,0)
            }
            setOnClickListener {
                state = !state
                if (state) {
                    input.transformationMethod = null
                    text = "🙈"
                } else {
                    input.transformationMethod = PasswordTransformationMethod.getInstance()
                    text = "👁️"
                }
                input.setSelection(input.text.length)
            }
        }
        //Aplicar los elementos al layout
        layoutPassword.apply {
            addView(input)
            addView(togglePassword)
        }
        //Declaracion de los campos que se mostraran
        val length = makeResultTextView(context, fontRegular, "Mínimo 8 caracteres")
        val mayusLetter = makeResultTextView(context, fontRegular, "Al menos una mayúscula")
        val number = makeResultTextView(context, fontRegular, "Al menos un número")
        val simbol = makeResultTextView(context, fontRegular, "Al menos un símbolo")
        // Texto de resultado
        val resultsLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            addView(length)
            addView(mayusLetter)
            addView(number)
            addView(simbol)
        }
        input.doOnTextChanged {
            text, _, _, _ ->
            isPasswordSecure(text.toString(), length, mayusLetter, number, simbol)
        }
        // Agregar todos los elementos al layout
        layout.apply {
            addView(titulo)
            addView(layoutPassword)
            addView(resultsLayout)
        }

        return layout
    }
    //Metodo para crear las etiquetas donde se mostrarán los resultados de la contraseña
    private fun makeResultTextView(context: Context, font: android.graphics.Typeface?, initText: String): TextView {
        return TextView(context).apply {
            text = initText
            textSize = 16f
            setTextColor(Color.DKGRAY)
            typeface = font
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 0)
            }
        }
    }
    //Metodo para la validación de la contraseña
    private fun isPasswordSecure(password: String, length: TextView, mayusLetter: TextView, number: TextView, simbol: TextView) {
        if (isValidLength(password)) {
            length.text = "✅ Mínimo 8 caracteres"
            length.setTextColor(Color.GREEN)
        } else {
            length.text = "⛔ Mínimo 8 caracteres"
            length.setTextColor(Color.RED)
        }

        if (hasMayusLetter(password)) {
            mayusLetter.text = "✅ Al menos una mayúscula"
            mayusLetter.setTextColor(Color.GREEN)
        } else {
            mayusLetter.text = "⛔ Al menos una mayúscula"
            mayusLetter.setTextColor(Color.RED)
        }

        if (hasNumber(password)) {
            number.text = "✅ Al menos un número"
            number.setTextColor(Color.GREEN)
        } else {
            number.text = "⛔ Al menos un número"
            number.setTextColor(Color.RED)
        }

        if (hasSimbol(password)) {
            simbol.text = "✅ Al menos un símbolo"
            simbol.setTextColor(Color.GREEN)
        } else {
            simbol.text = "⛔ Al menos un símbolo"
            simbol.setTextColor(Color.RED)
        }
    }
    //Metodo para la validacion de la longitud
    private fun isValidLength(password: String): Boolean {
        return password.length >= 8
    }
    //Metodo para validar que Tenga al menos una mayúscula
    private fun hasMayusLetter(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }
    //Metodo para validar que tenga un número
    private fun hasNumber(password: String): Boolean {
        return password.any { it.isDigit() }
    }
    //Metodo para validar que tenga al menos un símbolo
    private fun hasSimbol(password: String): Boolean {
        val symbols = "!@#$%^&*()_+~`|}{[]\\:;?><,./-="
        return password.any { symbols.contains(it) }
    }
}