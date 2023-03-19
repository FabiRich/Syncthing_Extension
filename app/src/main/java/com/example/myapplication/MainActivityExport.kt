package com.example.myapplication

import Requester
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class MainActivityExport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_export)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)


        val button = findViewById<Button>(R.id.button)

        button?.setOnClickListener {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_exportr, SyncthingConnectFragment())

            ft.commit()
        }

        var btnRequest = findViewById<Button>(R.id.btnRequest)
        btnRequest?.setOnClickListener {
            val requester = Requester()
            val response = requester.getHealth()
            Toast.makeText(
                this@MainActivityExport,
                response.body()?.string() ?: "",
                Toast.LENGTH_LONG
            ).show()
        }

        var valueSelector = findViewById<NumberPicker>(R.id.timer_selector_value)
        var values = arrayOf("minutes", "hours", "days", "weeks")
        valueSelector.minValue = 0
        valueSelector.maxValue = values.size - 1
        valueSelector.displayedValues = values

        var unitselector = findViewById<NumberPicker>(R.id.timer_selector_unit)
        unitselector.minValue = 0
        unitselector.maxValue = 100


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}

