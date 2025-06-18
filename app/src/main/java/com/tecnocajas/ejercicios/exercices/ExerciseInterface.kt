package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.view.View
import android.widget.Button

interface ExerciseInterface {
    var ID: Int
    var title: String
    var description: String
    fun makeContainer(context: Context) : View
}