package com.websarva.wings.android.meeteat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondRegisterDammyPhoneNumber : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_dammy_phone_number)

        // ボタンを取得
        val buttonContinue = findViewById<Button>(R.id.btn_continue_2)

        // ボタンがクリックされたらRegisterAddressAndRouteActivityに遷移
        buttonContinue.setOnClickListener {
            val intent = Intent(this, RegisterAddressAndRouteActivity::class.java)
            startActivity(intent) // 遷移を開始
        }
    }
}