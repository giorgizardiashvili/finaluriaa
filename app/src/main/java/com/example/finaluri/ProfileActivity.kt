package com.example.finaluri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ProfileActivity : AppCompatActivity() {
    private lateinit var img: ImageView
    private lateinit var buttonResetPassword :Button
    private lateinit var buttonLogout :Button
    private lateinit var editTextURL: EditText
    private lateinit var editTextPersonName: EditText
    private lateinit var editTextPersonLastName: EditText
    private lateinit var editTextPersonAge: EditText
    private lateinit var buttonUpdate:Button
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var textViewName : TextView
    private lateinit var textViewLastname : TextView
    private lateinit var textViewAge : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        registerListener()
        setValueEventListener()
    }

    private fun init(){
        buttonLogout = findViewById(R.id.buttonLogout)
        buttonResetPassword = findViewById(R.id.buttonResetPassword)
        img = findViewById(R.id.img)
        editTextURL = findViewById(R.id.editTextURL)
        editTextPersonAge = findViewById(R.id.editTextPersonAge)
        editTextPersonName = findViewById(R.id.editTextPersonName)
        editTextPersonLastName = findViewById(R.id.editTextPersonLastName)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        textViewAge = findViewById(R.id.textViewAge)
        textViewLastname = findViewById(R.id.textViewLastname)
        textViewName = findViewById(R.id.textViewName)
        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("UserInfo")
    }
    private fun registerListener(){
        buttonLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        buttonResetPassword.setOnClickListener{
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }
        buttonUpdate.setOnClickListener {
            val url = editTextURL.text.toString()
            val name = editTextPersonName.text.toString()
            val lastname = editTextPersonLastName.text.toString()
            val age = editTextPersonAge.text.toString()

            val namee = UserInfo(name, lastname, age, url)


            auth.currentUser?.uid?.let { uid ->
                db.child(uid).setValue(namee)

            }

        }
    }
    private fun setValueEventListener(){
        auth.currentUser?.uid?.let { uid ->
            db.child(uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userInfo = snapshot.getValue(UserInfo::class.java)?:return
                    textViewAge.text = userInfo.age.toString()
                    textViewLastname.text = userInfo.lastname.toString()
                    textViewName.text = userInfo.name.toString()
                    Glide.with(this@ProfileActivity)
                        .load(userInfo.url)
                        .into(img)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }
    }


}