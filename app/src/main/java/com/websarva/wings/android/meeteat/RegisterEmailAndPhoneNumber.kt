package com.websarva.wings.android.meeteat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegisterEmailAndPhoneNumber : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_email_and_phone_number)

        val textPhoneNumber: TextView = findViewById(R.id.txt_phone_number_Label)

        textPhoneNumber.setOnClickListener {
            val intent = Intent(this, SecondRegisterDammyPhoneNumber::class.java)
            startActivity(intent)
        }
    }
}