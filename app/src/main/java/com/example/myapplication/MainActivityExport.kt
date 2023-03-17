package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import getDefaultFolder
import getHealth
import okhttp3.guide.GetExample

class MainActivityExport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_export)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)



        // storing ID of the button
        // in a variable
        val button = findViewById<Button>(R.id.button)

        // operations to be performed
        // when user tap on the button
        button?.setOnClickListener()
        {
            // displaying a toast message
            Toast.makeText(this@MainActivityExport, R.string.message, Toast.LENGTH_LONG).show()
        }

        var btnRequest = findViewById(R.id.btnRequest) as Button?
        btnRequest?.setOnClickListener(){
            val response = getHealth()
            Toast.makeText(this@MainActivityExport, response.body()?.string() ?: "", Toast.LENGTH_LONG).show()
        }

        var picker1 = findViewById<NumberPicker>(R.id.numberPicker);
        picker1.setMaxValue(60);
        picker1.setMinValue(0);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

