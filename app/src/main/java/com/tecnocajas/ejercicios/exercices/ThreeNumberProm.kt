package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.view.View

class ThreeNumberProm : ExerciseInterface{
    override var ID: Int = 2
    override var title: String = "Promedio de tres Numeros"
    override var description: String = "Sacar el prmedio de tres numeros previamente validados"

    override fun makeContainer(context: Context): View {


        return layout
    }
}