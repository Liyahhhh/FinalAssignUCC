package com.example.finalassignucc

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class FacultyMain : AppCompatActivity() {

    private var db = Firebase.firestore
    private lateinit var facultyList: ArrayList<FacultyInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_main2)

       //recycle view for inflation
        var rvFaculty: RecyclerView = findViewById(R.id.rvUsers)
        var back: ImageView = findViewById(R.id.back)
        rvFaculty.layoutManager = LinearLayoutManager(this)

        facultyList = arrayListOf()

        //firebase storage for access of information
        db = FirebaseFirestore.getInstance()

        db.collection("FacultyInfo")
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty) {
                    for (data in it.documents) {
                        val faculty: FacultyInfo? = data.toObject(FacultyInfo::class.java)
                        if (faculty != null) {
                            facultyList.add(faculty)
                        }
                    }
                    rvFaculty.adapter = MyAdapter(facultyList)

                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Dont Work", Toast.LENGTH_SHORT).show()
            }

        //back button
        back.setOnClickListener{
            finish()
        }

    }
}