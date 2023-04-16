package com.example.finalassignucc

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.NonDisposableHandle.parent
import org.w3c.dom.Text
import java.io.File

class MyAdapter(private val facultyList: ArrayList<FacultyInfo>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    //recycler view to initialize all variables to be used
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.f_name)
        val course:TextView = itemView.findViewById(R.id.f_course)
        val infoBox: LinearLayout = itemView.findViewById(R.id.info_box)
        val fImage: CircleImageView = itemView.findViewById(R.id.f_image)
        val img_id: TextView = itemView.findViewById(R.id.id_no)
        val descrip: TextView = itemView.findViewById(R.id.f_preview)
        var email: String? = null
        var phone: String? = null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.faculty_main_display, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = facultyList[position].FName.toString() + " " +facultyList[position].LName.toString()
        holder.course.text = facultyList[position].Department
        holder.descrip.text = facultyList[position].Description
        holder.img_id.text = facultyList[position].img_id
        holder.email = facultyList[position].Email
        holder.phone = facultyList[position].Phone

        //code to retrieve an image from firebase storage
        val img_id = holder.img_id.text
        var storage = Firebase.storage
        var storageRef = storage.reference
        var imageRef = storageRef.child("images/$img_id.png")
        val localfile = File.createTempFile("tempImage", "png")

        imageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.fImage.setImageBitmap(bitmap)
            println("${facultyList[position]}")

        }
            .addOnFailureListener{
                println("FAIL")


            }

        //transferring information to another activity on click
        holder.infoBox.setOnClickListener {v ->
            val infoPosition = facultyList[position]

            val intent = Intent(v.context, FacultySecond::class.java)

            intent.putExtra("fname", infoPosition.FName)
            intent.putExtra("lname", infoPosition.LName)
            intent.putExtra("department", infoPosition.Department)
            intent.putExtra("phone", infoPosition.Phone)
            intent.putExtra("email", infoPosition.Email)
            intent.putExtra("description", infoPosition.Description)
            intent.putExtra("img_id", infoPosition.img_id)

            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return facultyList.size
    }
    }