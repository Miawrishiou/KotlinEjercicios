package com.tecnocajas.ejercicios

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.helper.widget.Carousel
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecnocajas.ejercicios.exercices.TwoNumSum
import com.tecnocajas.ejercicios.exercices.WithoutExercises

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var exercisesCarousel: RecyclerView
    private var cardExercise = mutableListOf<CardItem>()
    private lateinit var title: TextView
    private val TwoNumSum: TwoNumSum = TwoNumSum()
    private lateinit var vista: View
    private lateinit var dynamicContainer: FrameLayout
    private val WithoutExercises: WithoutExercises = WithoutExercises()
    private var exitExerciseButton: AppCompatButton?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*inicializacion de las variables*/
        exercisesCarousel = findViewById(R.id.exercises_carousel)
        title = findViewById(R.id.resalt_text)
        dynamicContainer = findViewById(R.id.dynamic_container)
        exitExerciseButton = findViewById(R.id.btn_exit)

        /*inicializacion de componentes*/
        initComponents()
    }
    /*metodo donde se inician los componentes iniciales necesarias para la app*/
    private fun initComponents() {
        videoLoop()
        inmersive()
        layoutManagerCarousel()
        loadExerciseCards()
        resaltTextStyle()
        defaultViewDynamicCont()

        exitExerciseButton?.setOnClickListener {
            exitExerciseButton()
        }
    }
    /*boton para regresar y que el dynamic container obetenga el valor por default*/
    private fun exitExerciseButton(){
        defaultViewDynamicCont()
    }
    /*Vista por Default para la caja dinamica*/
    private fun defaultViewDynamicCont(){
        val defaultView = WithoutExercises.makeContainer(this)
        dynamicContainer.removeAllViews()
        dynamicContainer.addView(defaultView)
    }
    /*Hacer el entorno inmersivo ocultando la barra de notificaciones*/
    private fun inmersive() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }
    /*metodo para que el video se repita infinitamente*/
    private fun videoLoop() {
        /*Ruta del video*/
        videoView = findViewById(R.id.video_background)
        val uri = Uri.parse("android.resource://${packageName}/${R.raw.background}")
        videoView.setVideoURI(uri)

        /*Reproducir en Loop*/
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            mp.setVolume(0f, 0f)
        }
        videoView.start()
    }
    /*metodos para el volumen*/
    override fun onResume() {
        super.onResume()
        videoView.start()
    }
    override fun onPause() {
        super.onPause()
        videoView.pause()
    }
    /*layout manager carousel*/
    private fun layoutManagerCarousel() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exercisesCarousel.layoutManager = layoutManager
    }
    /*carga dinamica de las cards*/
    private fun loadExerciseCards() {
        cardExercise = mutableListOf(
            CardItem(TwoNumSum.ID, TwoNumSum.title, TwoNumSum.description),
            CardItem(TwoNumSum.ID, TwoNumSum.title, TwoNumSum.description)
        )
        val adapter = Adapter(cardExercise){ card ->
            when (card.ID) {
                1 -> twoNumSum()
            }
        }
        exercisesCarousel.adapter = adapter

        Toast.makeText(this, "Cargando ${cardExercise.size} ejercicios", Toast.LENGTH_SHORT).show()
    }
    /*estilos para algunas clases de textos*/
    private fun resaltTextStyle(){
        val rojo = ContextCompat.getColor(this, R.color.red)
        val rojoOscuro = ContextCompat.getColor(this, R.color.black_red)
        val white = ContextCompat.getColor(this, R.color.white)
        val shader = LinearGradient(
            0f, 0f, 0f, title.textSize, intArrayOf(rojo, rojoOscuro), null, Shader.TileMode.CLAMP)
        title.paint.shader = shader
        title.setShadowLayer(30f,0f,0f, white)
    }
    /*Ejercicios*/
    /*Suma de dos numeros*/
    private fun twoNumSum(){
        vista = TwoNumSum.makeContainer(this)
        dynamicContainer.removeAllViews()
        dynamicContainer.addView(vista)
    }
}