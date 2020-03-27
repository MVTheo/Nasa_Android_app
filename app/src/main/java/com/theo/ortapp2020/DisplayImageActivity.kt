package com.theo.ortapp2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_image.*

class DisplayImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_image)
        val b = intent.extras
        var title: String? = ""
        var link: String? = ""
        var description : String? = ""
        if (b != null) title = b.getString("Title")
        if (b != null) link = b.getString("Link")
        if (b != null) description = b.getString("Description")
        Picasso.get().load(link).into(iv)
        tvTitle.text = title
        tv.text = description
    }
}
