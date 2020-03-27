package com.theo.ortapp2020

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Picture Search"
    }


    fun btClick(view: View) {
        val intent = Intent(this, PictureOfDayActivity::class.java)
        startActivity(intent)
    }

    fun btSearchClick(view: View) {
        val intent = Intent(this, SearchNasaPicture::class.java)
        startActivity(intent)
    }

}
