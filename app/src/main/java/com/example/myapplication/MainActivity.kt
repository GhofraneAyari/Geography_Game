package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener {
            val json = readJsonFromAssets(this, "Country.json")
            val countryList = parseJsonToCountryList(json)

            val intent = Intent(this, QuizActivity::class.java)
            intent.putParcelableArrayListExtra("countryList", ArrayList(countryList))
            startActivity(intent)
        }

        val settingsButton = findViewById<Button>(R.id.settingsButton)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun readJsonFromAssets(context: Context, fileName: String): String {
        val json: String
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return json
    }

    private fun parseJsonToCountryList(json: String): List<Country> {
        val gson = Gson()
        val listType = object : TypeToken<List<Country>>() {}.type
        return gson.fromJson(json, listType)
    }
}
