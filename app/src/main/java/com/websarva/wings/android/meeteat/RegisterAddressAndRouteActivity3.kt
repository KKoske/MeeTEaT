package com.websarva.wings.android.meeteat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterAddressAndRouteActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_address_and_route3)
        // ボタンを取得
        val buttonContinue = findViewById<Button>(R.id.nextButton4)

        // ボタンがクリックされたらRegisterAddressAndRouteActivityに遷移
        buttonContinue.setOnClickListener {
            val intent = Intent(this, RegisterAddressAndRouteActivity4::class.java)
            startActivity(intent) // 遷移を開始
        }
    }
}