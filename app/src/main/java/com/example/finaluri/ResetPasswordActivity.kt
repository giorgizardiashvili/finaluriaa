package com.example.finaluri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var editTextResetEmailAddress:EditText
    private lateinit var buttonSend:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        init()

        registrationListener()
    }
    private fun init(){
        editTextResetEmailAddress = findViewById(R.id.editTextResetEmailAddress)
        buttonSend = findViewById(R.id.buttonSend)
    }
    private fun registrationListener(){
        buttonSend.setOnClickListener{
            val email = editTextResetEmailAddress.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this, "გთხოვთ შეავსოთ გრაფა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "შეამოწმეთ მეილი", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    else{
                        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }
}