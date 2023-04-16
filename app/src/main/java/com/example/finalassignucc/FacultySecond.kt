package com.example.finalassignucc

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class FacultySecond : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_second)

        val bundle: Bundle? = intent.extras

        var nameInput: TextView = findViewById(R.id.f_name)
        var departInput: TextView = findViewById(R.id.f_department)
        var phone: TextView = findViewById(R.id.f_phone)
        var email: TextView = findViewById(R.id.f_email)
        var info: TextView = findViewById(R.id.f_preview)
        var image: CircleImageView = findViewById(R.id.img2)
        var back: ImageView = findViewById(R.id.back)

        var img_id: String? = null

        nameInput.setText(bundle?.getString("fname"))
        departInput.setText(bundle?.getString("department"))
        phone.setText(bundle?.getString("phone"))
        email.setText(bundle?.getString("email"))
        info.setText(bundle?.getString("description"))
        img_id = bundle?.getString("img_id")

        //image retrieval code
        var storage = Firebase.storage
        var storageRef = storage.reference
        var imageRef = storageRef.child("images/$img_id.png")
        val localfile = File.createTempFile("tempImage", "png")

        imageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            image.setImageBitmap(bitmap)

        }
            .addOnFailureListener{
                println("FAIL")
            }

        //code to forward a message to the faculty clicked
        email.setOnClickListener{
            val mail = email.toString()
            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null))
            intent.putExtra(Intent.EXTRA_EMAIL, mail)
            startActivity(intent)
        }
        //back button
        back.setOnClickListener{
            finish()
        }


    }
}