package com.example.finalassignucc

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.finalassignucc.FacultyMain
import com.example.finalassignucc.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.units.qual.A

class MainActivity : AppCompatActivity() {


    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    private val mail: String = "ithod@ucc.edu.jm"
    private var db = Firebase.firestore

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aboutUs: TextView = findViewById(R.id.aboutUs)
/*
        //retrieval of firebase data
        db = FirebaseFirestore.getInstance()

        db.collection("AboutUcc").document("QENdPqysc7NalyeVZ3yE")
            .get()
            .addOnSuccessListener {info ->
                aboutUs.text = info.data.toString()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to produce about",Toast.LENGTH_SHORT).show()
            }

 */


        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //menu navigation
        var n_view : NavigationView = findViewById(R.id.navView)
        n_view.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.faculty -> {
                    //Toast.makeText(this@MainActivity, "Faculty Clicked!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, FacultyMain::class.java))
                }

                R.id.courses ->
                {
                    //Toast.makeText(this, "Courses Clicked", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, CourseMain::class.java))
                }
                R.id.admission ->
                {
                    //Toast.makeText(this, "Admission Clicked", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Admissions::class.java))
                }
                R.id.social ->
                {
                    //Toast.makeText(this, "Social Media Clicked", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Socials::class.java))
                }

            }
            true
        }

        //floating action bar for sending email to hod
        val sendEmail: FloatingActionButton = findViewById(R.id.fab)
        sendEmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mail, null))
            intent.putExtra(Intent.EXTRA_EMAIL, mail)
            startActivity(intent)
        }
    }
    //open and close nav bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            true
        }
        else super.onOptionsItemSelected(item)
    }
}