package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.tecnocajas.ejercicios.R

class WithoutExercises : ExerciseInterface {
    override var ID = 0
    override var title = "Selecciona uno de los 20 ejercicios"
    override var description = "Puedes mover el carrusel de arriba para seleccionar los ejercicios que deseas resolver"

    override fun makeContainer(context: Context): View {
        // Cargar fuentes Rubik
        val rubikBold = ResourcesCompat.getFont(context, R.font.rubik_bold)
        val rubikRegular = ResourcesCompat.getFont(context, R.font.rubik_regular)

        // Layout principal
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }

        // Título
        val titulo = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.BLACK)
            typeface = rubikBold
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }

        // Descripción
        val descripcion = TextView(context).apply {
            text = description
            textSize = 18f
            setTextColor(Color.DKGRAY)
            typeface = rubikRegular
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
        // Agregar al layout
        layout.apply {
            addView(titulo)
            addView(descripcion)
        }

        return layout
    }
}