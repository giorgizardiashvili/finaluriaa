package com.example.finaluri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegistration: Button
    private lateinit var buttonReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
        }

        init()

        registerListener()
    }

    private fun init(){
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegistration = findViewById(R.id.buttonRegistration)
        buttonReset = findViewById(R.id.buttonReset)
    }

    private fun registerListener(){
        buttonLogin.setOnClickListener{
            val email = editTextEmailAddress.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "გთხოვთ შეავსოთ ყველა გრაფა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 8 ){
                Toast.makeText(this, "შეამოწმეთ პაროლი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this, "შეამოწმეთ ყველა გრაფა/რეგისტრაცია გაიარეთ", Toast.LENGTH_SHORT).show()
                    }
                }


        }

        buttonRegistration.setOnClickListener{
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
        buttonReset.setOnClickListener{
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }
}