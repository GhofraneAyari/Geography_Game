package com.example.myapplication


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import java.util.Random


class QuizActivity : AppCompatActivity() {
    private val flagImageView: ImageView by lazy {
        findViewById(R.id.flagImageView)
    }
    private lateinit var randomFlagButton: Button
    private lateinit var skipFlagButton: Button
    private var countryList: List<Country>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        countryList = intent.getParcelableArrayListExtra("countryList")

        randomFlagButton = findViewById(R.id.randomFlag)
        randomFlagButton.setOnClickListener {
            loadRandomCountryFlag()
        }

        skipFlagButton = findViewById(R.id.skipFlagButton)
        skipFlagButton.setOnClickListener {
            loadRandomCountryFlag()
        }
    }

    private fun loadRandomCountryFlag() {
        val random = Random()
        if (!countryList.isNullOrEmpty()) {
            // Shuffle the list to randomize it
            countryList = countryList!!.shuffled()

            val randomCountry = countryList!![random.nextInt(countryList!!.size)]

            val countryNameTextView = findViewById<TextView>(R.id.countryNameTextView)
            countryNameTextView.text = randomCountry.country

            val randomFlagUrl = randomCountry.flag
            Log.e("IMAGE URL", randomFlagUrl.toString())

            val imageView = findViewById<ImageView>(R.id.flagImageView)

            val imageLoader = ImageLoader.Builder(this)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()

            imageView.load(randomFlagUrl, imageLoader) {
                crossfade(true)
                error(R.drawable.quizlogo)
            }
        }
    }
}