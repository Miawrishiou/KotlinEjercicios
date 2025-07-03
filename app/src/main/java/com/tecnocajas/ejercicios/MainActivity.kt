package com.tecnocajas.ejercicios

import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import android.os.Bundle
import android.renderscript.Script
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecnocajas.ejercicios.exercices.N10SortArray
import com.tecnocajas.ejercicios.exercices.N11BinarieSearch
import com.tecnocajas.ejercicios.exercices.N12ElementsArraySum
import com.tecnocajas.ejercicios.exercices.N13HigherElement
import com.tecnocajas.ejercicios.exercices.N14ElementTimesInArray
import com.tecnocajas.ejercicios.exercices.N15RevertArray
import com.tecnocajas.ejercicios.exercices.N16Fibonacci
import com.tecnocajas.ejercicios.exercices.N17FactorialRecursivity
import com.tecnocajas.ejercicios.exercices.N18VocalToNumber
import com.tecnocajas.ejercicios.exercices.N19PasswordValidation
import com.tecnocajas.ejercicios.exercices.N2ThreeNumberProm
import com.tecnocajas.ejercicios.exercices.N1TwoNumSum
import com.tecnocajas.ejercicios.exercices.N20DecimalToRoman
import com.tecnocajas.ejercicios.exercices.N3TwoNumberMayor
import com.tecnocajas.ejercicios.exercices.N4OddEven
import com.tecnocajas.ejercicios.exercices.N5UnitConverter
import com.tecnocajas.ejercicios.exercices.N6PositivNegativeZero
import com.tecnocajas.ejercicios.exercices.N7Factorial
import com.tecnocajas.ejercicios.exercices.N8PrimeNumber
import com.tecnocajas.ejercicios.exercices.N9Calculator
import com.tecnocajas.ejercicios.exercices.WithoutExercises

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var exercisesCarousel: RecyclerView
    private var cardExercise = mutableListOf<CardItem>()
    private lateinit var title: TextView
    private lateinit var scrollViewMain: ScrollView
    private val TwoNumSum: N1TwoNumSum = N1TwoNumSum()
    private val WithoutExercises: WithoutExercises = WithoutExercises()
    private val ThreeNumberProm: N2ThreeNumberProm = N2ThreeNumberProm()
    private val TwoNumberMayor: N3TwoNumberMayor = N3TwoNumberMayor()
    private val OddEven: N4OddEven = N4OddEven()
    private val UnitConvert: N5UnitConverter = N5UnitConverter()
    private val PositiveNegativeZero: N6PositivNegativeZero = N6PositivNegativeZero()
    private val Factorial: N7Factorial = N7Factorial()
    private val PrimeNumber: N8PrimeNumber = N8PrimeNumber()
    private val Calculator: N9Calculator = N9Calculator()
    private val SortArray: N10SortArray = N10SortArray()
    private val BinarieSearch: N11BinarieSearch = N11BinarieSearch()
    private val ElementsArraySum: N12ElementsArraySum = N12ElementsArraySum()
    private val HigherElement: N13HigherElement = N13HigherElement()
    private val ElementTimesInArray: N14ElementTimesInArray = N14ElementTimesInArray()
    private val RevertArray: N15RevertArray = N15RevertArray()
    private val Fibonacci: N16Fibonacci = N16Fibonacci()
    private val FactorialRecursivity: N17FactorialRecursivity = N17FactorialRecursivity()
    private val VocalToNumber: N18VocalToNumber = N18VocalToNumber()
    private val PasswordValidation: N19PasswordValidation = N19PasswordValidation()
    private val DecimalToRoman: N20DecimalToRoman = N20DecimalToRoman()
    private lateinit var vista: View
    private lateinit var dynamicContainer: FrameLayout
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
        scrollViewMain = findViewById(R.id.srv_main)
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
            CardItem(ThreeNumberProm.ID, ThreeNumberProm.title, ThreeNumberProm.description),
            CardItem(TwoNumberMayor.ID, TwoNumberMayor.title, TwoNumberMayor.description),
            CardItem(OddEven.ID, OddEven.title, OddEven.description),
            CardItem(UnitConvert.ID, UnitConvert.title, UnitConvert.description),
            CardItem(PositiveNegativeZero.ID, PositiveNegativeZero.title, PositiveNegativeZero.description),
            CardItem(Factorial.ID, Factorial.title, Factorial.description),
            CardItem(PrimeNumber.ID, PrimeNumber.title, PrimeNumber.description),
            CardItem(Calculator.ID, Calculator.title, Calculator.description),
            CardItem(SortArray.ID, SortArray.title, SortArray.description),
            CardItem(BinarieSearch.ID, BinarieSearch.title, BinarieSearch.description),
            CardItem(ElementsArraySum.ID, ElementsArraySum.title, ElementsArraySum.description),
            CardItem(HigherElement.ID, HigherElement.title, HigherElement.description),
            CardItem(ElementTimesInArray.ID, ElementTimesInArray.title, ElementTimesInArray.description),
            CardItem(RevertArray.ID, RevertArray.title, RevertArray.description),
            CardItem(Fibonacci.ID, Fibonacci.title, Fibonacci.description),
            CardItem(FactorialRecursivity.ID, FactorialRecursivity.title, FactorialRecursivity.description),
            CardItem(VocalToNumber.ID, VocalToNumber.title, VocalToNumber.description),
            CardItem(PasswordValidation.ID, PasswordValidation.title, PasswordValidation.description),
            CardItem(DecimalToRoman.ID, DecimalToRoman.title, DecimalToRoman.description)
        )
        val adapter = Adapter(cardExercise) { card ->
            when (card.ID) {
                1 -> twoNumSum()
                2 -> threeNumberProm()
                3 -> twoNumberMayor()
                4 -> oddEven()
                5 -> unitConvert()
                6 -> positiveNegativeZero()
                7 -> factorial()
                8 -> primeNumber()
                9 -> calculator()
                10 -> sortArray()
                11 -> binarieSearch()
                12 -> elementsArraySum()
                13 -> higherElement()
                14 -> elementTimesInArray()
                15 -> revertArray()
                16 -> fibonacci()
                17 -> factorialRecursivity()
                18 -> vocalToNumber()
                19 -> passwordValidation()
                20 -> decimalToRoman()
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
        setDynamicExercise(TwoNumSum.makeContainer(this))
    }
    /*Promedio de 3 numeros*/
    private fun threeNumberProm(){
        setDynamicExercise(ThreeNumberProm.makeContainer(this))
    }
    /*Mayor de tres numeros*/
    private fun twoNumberMayor(){
        setDynamicExercise(TwoNumberMayor.makeContainer(this))
    }
    /*Mayor de tres numeros*/
    private fun oddEven(){
        setDynamicExercise(OddEven.makeContainer(this))
    }
    /*Conversor de unidades*/
    private fun unitConvert(){
        setDynamicExercise(UnitConvert.makeContainer(this))
    }
    /*numero positivo, negativo o cero*/
    private fun positiveNegativeZero(){
        setDynamicExercise(PositiveNegativeZero.makeContainer(this))
    }
    /*factorial de un numero*/
    private fun factorial() {
        setDynamicExercise(Factorial.makeContainer(this))
    }
    /*Prime Number*/
    private fun primeNumber() {
        setDynamicExercise(PrimeNumber.makeContainer(this))
    }
    /*Calculator*/
    private fun calculator() {
        setDynamicExercise(Calculator.makeContainer(this))
    }
    /*Sort Array*/
    private fun sortArray() {
        setDynamicExercise(SortArray.makeContainer(this))
    }
    /*Binerie Search*/
    private fun binarieSearch() {
        setDynamicExercise(BinarieSearch.makeContainer(this))
    }
    /*Elements array sum*/
    private fun elementsArraySum() {
        setDynamicExercise(ElementsArraySum.makeContainer(this))
    }
    /*Higher element*/
    private fun higherElement() {
        setDynamicExercise(HigherElement.makeContainer(this))
    }
    /*Element Times in Array*/
    private fun elementTimesInArray() {
        setDynamicExercise(ElementTimesInArray.makeContainer(this))
    }
    /*Revertir un Array*/
    private fun revertArray() {
        setDynamicExercise(RevertArray.makeContainer(this))
    }
    /*Fibonacci con recursividad*/
    private fun fibonacci() {
        setDynamicExercise(Fibonacci.makeContainer(this))
    }
    /*Factorial con recursividad*/
    private fun factorialRecursivity() {
        setDynamicExercise(FactorialRecursivity.makeContainer(this))
    }
    /*Vocal a número*/
    private fun vocalToNumber() {
        setDynamicExercise(VocalToNumber.makeContainer(this))
    }
    /*Decimal a romanos*/
    private fun decimalToRoman() {
        setDynamicExercise(DecimalToRoman.makeContainer(this))
    }
    /*Validacion de una contraseña*/
    private fun passwordValidation() {
        setDynamicExercise(PasswordValidation.makeContainer(this))
    }
    /*Super metodo para hacer las vistas*/
    private fun setDynamicExercise(view: View) {
        dynamicContainer.removeAllViews()
        dynamicContainer.addView(view)
    }
}