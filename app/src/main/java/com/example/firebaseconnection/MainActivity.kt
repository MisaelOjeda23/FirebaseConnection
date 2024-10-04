package com.example.firebaseconnection

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var sendPhoto: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = FirebaseFirestore.getInstance()
        db.collection("ciudades")
            .document("e2HcFn6SgCFusWCpTO3x")
            .get().addOnSuccessListener { document -> document?.let {
                Log.d("Firebase", "documentSnapshot data: ${document.data}")
                val ciudad = document.getString("ciudad")
                val poblacion = document.getLong("poblacion")
                Log.d("Firebase", "Ciudad: $ciudad")
                Log.d("Firebase", "Poblacion: $poblacion")

            }}.addOnFailureListener() { error -> Log.e("Firebase", "Error: ", error) }
    }
}
