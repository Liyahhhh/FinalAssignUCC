package com.example.finalassignucc

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Admissions : AppCompatActivity() {
    private val url: String = "https://ucc.edu.jm/apply/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admissions)

        val adminInfo: TextView = findViewById(R.id.adminInfo)
        val applyButton: TextView = findViewById(R.id.applyB)
        val back: ImageView = findViewById(R.id.back)

/*
        //Retrieval of Firebase data on UCC's admission requirements
        var db = Firebase.firestore

        db.collection("AdmissionInfo").document("s3mHzWJu4OicyVMbtz2U")
            .get()
            .addOnSuccessListener { info ->
                adminInfo.text = info.data.toString()
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }

 */


        //apply button on click command
        applyButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(url))
                startActivity(intent)
        }

        //back button
        back.setOnClickListener{
            finish()
        }

    }
}