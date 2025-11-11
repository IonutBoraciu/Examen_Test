package com.example.pregatiretest_2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var coords_data = ""
    var number_coords = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toggleButton = ButtonClickListener()

        val buttons = listOf(
            R.id.north, R.id.east, R.id.west,
            R.id.south
        )

        for (id in buttons) {
            findViewById<View>(id).setOnClickListener(toggleButton)
        }


    }

    inner class ButtonClickListener : android.view.View.OnClickListener {
        override fun onClick(v: View?) {
            if (v == null) return
            var text_coords = findViewById<TextView>(R.id.coords)
            if(coords_data != "") {
                coords_data += ", "
            }
            if (v.id == R.id.north) {
                coords_data += "North"
                number_coords++
            } else if(v.id == R.id.south) {
                coords_data += "South"
                number_coords++
            } else if(v.id == R.id.east) {
                coords_data += "East"
                number_coords++
            } else if(v.id == R.id.west) {
                coords_data += "West"
                number_coords++
            }
            Log.w("[ButtonClick]", "pressed $number_coords")
            text_coords.text = coords_data

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("number_coords", number_coords)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        number_coords = savedInstanceState.getInt("number_coords")
    }
}