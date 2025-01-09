package com.websarva.wings.android.meeteat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StationExitAdjustmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_exit_adjustment)
        // ボタンを取得
        val buttonContinue = findViewById<Button>(R.id.nextButton2)

        // ボタンがクリックされたらRegisterAddressAndRouteActivityに遷移
        buttonContinue.setOnClickListener {
            val intent = Intent(this, RegisterRouteHomeToStation::class.java)
            startActivity(intent) // 遷移を開始
        }
    }
}