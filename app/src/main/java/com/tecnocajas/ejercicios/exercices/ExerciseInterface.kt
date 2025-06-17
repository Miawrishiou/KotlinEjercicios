package com.tecnocajas.ejercicios.exercices

import android.content.Context
import android.view.View
import android.widget.Button

interface ExerciseInterface {
    public var ID: Int
    public var title: String
    public var description: String
    fun makeContainer(context: Context) : View
}