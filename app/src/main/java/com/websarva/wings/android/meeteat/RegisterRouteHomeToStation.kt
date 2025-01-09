package com.websarva.wings.android.meeteat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterRouteHomeToStation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_route_home_to_station)
        // ボタンを取得
        val buttonContinue = findViewById<Button>(R.id.nextButton4)

        // ボタンがクリックされたらRegisterAddressAndRouteActivityに遷移
        buttonContinue.setOnClickListener {
            val intent = Intent(this, FinalConfirmationActivity::class.java)
            startActivity(intent) // 遷移を開始
        }
    }
}