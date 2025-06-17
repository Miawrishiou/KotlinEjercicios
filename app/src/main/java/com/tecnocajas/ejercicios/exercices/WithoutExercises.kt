package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class WithoutExercises : ExerciseInterface {
    override var ID = 0
    override var title = "Selecciona uno de los 20 ejercicios"
    override var description = "Puedes mover el carrusel de arriba para seleccionar los ejercicios que deseas resolver"

    override fun makeContainer(context: Context): View {
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }
        /*Titulo del ejercicio*/
        val title = TextView(context).apply {
            text = title
            textSize = 20f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            setTextAlignment(View.TEXT_ALIGNMENT_CENTER)
        }
        /*Descripcion del ejercicio*/
        val description = TextView(context).apply {
            text = description
            textSize = 12f
            setTextColor(Color.BLACK)
            setTextAlignment(View.TEXT_ALIGNMENT_CENTER)
        }
        /*Cargar los elementos al contendor padre para que puedan ser renderizados*/
        layout.apply {
            addView(title)
            addView(description)
        }
        return layout
    }
}