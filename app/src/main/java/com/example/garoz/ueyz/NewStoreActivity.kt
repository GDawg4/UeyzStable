package com.example.garoz.ueyz

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_new_store.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.garoz.ueyz.R.id.newName
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class NewStoreActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_store)
        val dropdown = findViewById<Spinner>(R.id.spinner)
        val items = arrayOf("Puesto de comida", "Librería", "Abarrotería", "Otros")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        var typeSelected = ""
        var isSelected = false
        dropdown.adapter = adapter

        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                isSelected = true
                typeSelected = items[position]
                Toast.makeText(this@NewStoreActivity, spinner.selectedItem.toString(), Toast.LENGTH_LONG).show()

            }
        }
    }

   fun createNewUser(v : View){
        if (new_store_name == null) {
            Toast.makeText(this@NewStoreActivity, "No se puede ingresar una tienda sin nombre", Toast.LENGTH_LONG).show()
        }else{
            val newActivity = Intent(this, MapsActivity::class.java)
            val data = Intent().apply {
                putExtra("newName", new_store_name.text.toString())
                putExtra("newDescription", spinner.toString())
            }

            setResult(Activity.RESULT_OK, data)
            startActivity(newActivity)
            finish()
        }
    }

    fun getLocation(v:View){
        val toMap = Intent(this, MapsActivity::class.java)
        startActivity(toMap)
    }
}
