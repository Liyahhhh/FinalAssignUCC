package com.example.finalassignucc

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CourseMain : AppCompatActivity() {
    private var db = Firebase.firestore
    private lateinit var courseList: ArrayList<CourseInfo>
    private lateinit var c_names: Array<String>
    private lateinit var mutableNames: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_main)

        //containers for the lists
        courseList = arrayListOf()
        mutableNames = mutableListOf()

        val back: ImageView = findViewById(R.id.back)
        val courseFrame = CourseFragment()

        db.collection("CourseInfo")
            .get()
            .addOnSuccessListener {
                //block to retreive values from the cloud database and add to an arrayList
                if (!it.isEmpty) {
                    for (data in it.documents) {
                        val course: CourseInfo? = data.toObject(CourseInfo::class.java)
                        if (course != null) {
                            courseList.add(course)
                        }
                    }
                    //Transfer of data to a mutable list for read and write privileges
                    for (data in courseList) {
                        c_names = arrayOf(data.c_name.toString())
                        mutableNames.add(data.c_name.toString())
                    }

                    //adapter to blow up the data into a list container
                    val adapter =
                        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mutableNames)
                    val listview: ListView = findViewById(R.id.c_lv)

                    listview.adapter = adapter

                    listview.setOnItemClickListener{ parent, view, position, id ->

                        val credits = courseList[position].c_credits
                        val code = courseList[position].c_id
                        val descrip = courseList[position].c_desrip
                        val name = courseList[position].c_name
                        val prereq = courseList[position].c_prereq

                        val bundle = Bundle()
                        bundle.putString("credits", credits)
                        bundle.putString("code", code)
                        bundle.putString("descrip", descrip)
                        bundle.putString("name", name)
                        bundle.putString("prereq", prereq)

                        courseFrame.arguments = bundle
                        supportFragmentManager.beginTransaction().apply{
                            replace(R.id.frame, courseFrame)
                            commit()
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
        //back button
        back.setOnClickListener{
            finish()
        }
    }
}

