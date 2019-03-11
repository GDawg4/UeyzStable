package com.example.garoz.ueyz

import android.content.Intent
import android.os.Bundle

import android.view.View
import android.widget.Toast
import com.example.garoz.ueyz.Views.NewUserActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
// funcion que verifica si la informacion del usuario es correcta
    fun verrifyInfo(view: View) {
        var currentUserName = userInput.text.toString()
        var currentPassword = passwordInput.text.toString()
        var documentsRef = db.collection("usuarios")
        var succes=false
    //Obtiene todos los documentos de la base de datos
        documentsRef
                .get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        //recorre todos los documentos y verifica si uno tiene la informacion que se necesita
                        for (document in task.result!!) {
                            var correctUser=document["name"].toString()
                            var correctPassword=document["pasword"].toString()
                            if (currentUserName.equals(correctUser) && correctPassword.equals(currentPassword)){
                                Toast.makeText(applicationContext,"Usuario y Contraseña correctos",Toast.LENGTH_SHORT).show()
                                succes = true
                                break
                            }
                        }
                        if (!succes){
                            Toast.makeText(applicationContext,"Usuario o Contraseña incorrectos",Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
                    }
                })
    }
    fun sendToNewUserWindow(view: View){
        val intent= Intent(this,NewUserActivity::class.java)
        startActivity(intent)
    }
}
