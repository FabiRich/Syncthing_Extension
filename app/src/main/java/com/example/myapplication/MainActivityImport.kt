package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
class MainActivityImport : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_import)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)


        setupHyperlink()


        var btnRequest = findViewById<Button>(R.id.buttonImportSyncthing)
        btnRequest?.setOnClickListener {
            if (isPackageInstalledAndEnabled(
                    getString(R.string.syncthingPackage),
                    packageManager
                )
            ) {
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.your_placeholder, SyncthingConnectFragment())

                ft.commit()
                var buttonRestore = findViewById<Button>(R.id.button5)
                buttonRestore?.visibility = View.VISIBLE
            } else
                Toast.makeText(
                    this@MainActivityImport,
                    "Syncthing is not enabled or installed",
                    Toast.LENGTH_LONG
                ).show()
        }

    }
    private fun setupHyperlink() {
        val linkTextView = findViewById<TextView>(R.id.textView2)
        linkTextView.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun isPackageInstalledAndEnabled(packagename: String, packageManager: PackageManager): Boolean {
        return try {
            packageManager.getApplicationInfo(packageName, 0).enabled
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}