package com.example.finaluri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextRegistrationEmailAddress:EditText
    private lateinit var editTextRegistrationPassword:EditText
    private lateinit var editTextRepeatPassword:EditText
    private lateinit var buttonRegist:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        init()

        registerListeners()
    }

    private fun init(){
        editTextRegistrationEmailAddress = findViewById(R.id.editTextRegistrationEmailAddress)
        editTextRegistrationPassword = findViewById(R.id.editTextRegistrationPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
        buttonRegist = findViewById(R.id.buttonRegist)
    }
    private fun registerListeners(){
        buttonRegist.setOnClickListener{
            val email = editTextRegistrationEmailAddress.text.toString()
            val password = editTextRegistrationPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()|| repeatPassword.isEmpty()){
                Toast.makeText(this, "გთხოვთ შეავსოთ ყველა გრაფა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 8 ){
                Toast.makeText(this, "პაროლი უნდა შედგებოდეს არა ნაკლებ 8 სიმბოლოთი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != repeatPassword){
                Toast.makeText(this, "შეამოწმეთ პაროლი ორივე გრაფაში", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this, "გთხოვთ შეამოწმოთ ყველა გრაფა", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}