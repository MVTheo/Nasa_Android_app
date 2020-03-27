package com.theo.ortapp2020

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.theo.ortapp2020.beans.ApodBeans
import com.theo.ortapp2020.utils.wsGetPictureOfDay
import kotlinx.android.synthetic.main.activity_picture_of_day.*

class PictureOfDayActivity : AppCompatActivity() {

    var monAt: MonAt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_of_day)
        setTitle("Picture Search")
        if (monAt == null || monAt!!.status == AsyncTask.Status.FINISHED) {
            monAt = MonAt()
            monAt?.execute()
        } else {
            Toast.makeText(this, "Tache déjà en cours", Toast.LENGTH_SHORT).show();
        }
    }

    inner class MonAt() : AsyncTask<Unit, Unit, Unit>() {
        var resultat: ApodBeans? = null
        var exception: Exception? = null
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Unit?) {
            try {
                resultat = wsGetPictureOfDay()
            } catch (e: Exception) {
                exception = e
                e.printStackTrace()
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Picasso.get().load(resultat?.url).into(apod)
            tv.text = resultat?.explanation
        }
    }
}
