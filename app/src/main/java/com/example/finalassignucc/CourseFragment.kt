package com.example.finalassignucc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.checkerframework.checker.units.qual.s
import java.util.zip.Inflater


class CourseFragment : Fragment(R.layout.fragment_course) {
    private  lateinit var c_name: TextView
    private  lateinit var c_code: TextView
    private  lateinit var c_descrip: TextView
    private  lateinit var c_prereq: TextView
    private  lateinit var c_credits: TextView
    //private  lateinit var back: ImageView

    //inflater code to create a fragment of courses info
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course, container, false)
        c_code = view.findViewById(R.id.c_code)
        c_name = view.findViewById(R.id.c_name)
        c_descrip = view.findViewById(R.id.c_info)
        c_credits = view.findViewById(R.id.c_credits)
        c_prereq = view.findViewById(R.id.prereq)
        //back = view.findViewById(R.id.back)

        val data = arguments

        c_prereq.text = data!!.get("prereq").toString()
        c_credits.text = data!!.get("credits").toString()
        c_name.text = data!!.get("name").toString()
        c_descrip.text = data!!.get("descrip").toString()
        c_code.text = data!!.get("code").toString()


        return view
    }
}