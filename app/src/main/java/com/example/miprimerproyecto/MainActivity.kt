package com.example.miprimerproyecto

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button:Button = findViewById(R.id.buttonChangeText)
        button.setOnClickListener{selectTextChange()}
    }

    private fun selectTextChange() {
        var randomTextId = Random.nextInt(5) + 1
        val randomTextRL = Random.nextInt(2)
        if (randomTextRL == 0)
        {
            val textLeft:TextView = findViewById(R.id.textLeft)
            val numberString = getNumberResource(textLeft.text.toString())
            while (randomTextId == numberString)
                randomTextId = Random.nextInt(5) + 1
            changeText(textLeft,randomTextId)
        }
        else
        {
            val textRight:TextView = findViewById(R.id.textRight)
            val numberString = getNumberResource(textRight.text.toString())
            while (randomTextId == numberString)
                randomTextId = Random.nextInt(5) + 1
            changeText(textRight,randomTextId)
        }

    }

    private fun getNumberResource(text: String): Int? {
        val fields = R.string::class.java.fields
        var number: Int? = null
        for (field in fields) {
            val resourceName = field.name
            if (resources.getString(field.getInt(null)) == text) {
                number = resourceName.last().toString().toIntOrNull()
                break
            }
        }
        return number
    }

    private fun changeText(textChange:TextView, stringNumber:Int) {
        val resourceId = when (stringNumber) {
            1 -> R.string.text1
            2 -> R.string.text2
            3 -> R.string.text3
            4 -> R.string.text4
            5 -> R.string.text5
            else -> 0
        }
        if (resourceId != 0)
            textChange.text = getString(resourceId)
    }
}