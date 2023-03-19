package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonImport = findViewById<Button>(R.id.buttonImport)

        buttonImport?.setOnClickListener()
        {
            val i = Intent(this@MainActivity, MainActivityImport::class.java)
            startActivity(i)

        }

        val buttonExport = findViewById<Button>(R.id.buttonExport)

        buttonExport?.setOnClickListener()
        {
            val i = Intent(this@MainActivity, MainActivityExport::class.java)
            startActivity(i)
        }

    }

}
