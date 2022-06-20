package com.example.finaluri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var editTextNewPassword:EditText
    private lateinit var editTextNewPassword2:EditText
    private lateinit var buttonChangePassword:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        init()
        registerListener()
    }

    private fun init(){
        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        editTextNewPassword2 = findViewById(R.id.editTextNewPassword2)
        buttonChangePassword = findViewById(R.id.buttonChangePassword)
    }

    private fun registerListener(){
        buttonChangePassword.setOnClickListener{
            val newPassword = editTextNewPassword.text.toString()
            val repeatNewPassword = editTextNewPassword2.text.toString()

            if (newPassword.isEmpty() || repeatNewPassword.isEmpty()){
                Toast.makeText(this, "გთხოვთ შეავსოთ ყველა გრაფა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (newPassword != repeatNewPassword){
                Toast.makeText(this, "შეამოწმეთ პაროლები", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .currentUser
                ?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "პაროლი შეიცვალა", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT ).show()
                    }
                }
        }
    }
}