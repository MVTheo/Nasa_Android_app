package com.theo.ortapp2020

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import androidx.recyclerview.widget.GridLayoutManager
import com.theo.ortapp2020.beans.Item
import com.theo.ortapp2020.utils.wsGetPictureSearch
import kotlinx.android.synthetic.main.activity_search_nasa_picture.*
import kotlinx.android.synthetic.main.row_search.*


class SearchNasaPicture : AppCompatActivity(), SearchAdapter.SearchAdapterListener  {

    val data = ArrayList<Item>()
    val adapter = SearchAdapter(data, this)
    var monAt: MonAt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_nasa_picture)
        setTitle("Picture Search")
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(this, 1)
    }


    override fun onSearchBeanClick(item: Item) {
        val intent = Intent(this, DisplayImageActivity::class.java)
        intent.putExtra("Title", item.data[0].title)
        intent.putExtra("Link",item.links[0].href)
        intent.putExtra("Description",item.data[0].description)
        val p1 : androidx.core.util.Pair<View, String> = androidx.core.util.Pair(iv, "profile")
        val p2 : androidx.core.util.Pair<View, String> = androidx.core.util.Pair(tv, "title")
        val options = makeSceneTransitionAnimation(
            this,
            p1,
            p2
        )
        startActivity(intent, options.toBundle())
    }

    override fun onSearchBeanLongClick(item: Item) {
        TODO("Not yet implemented")
    }



    fun onBtChargerClick(view: View) {
        if (monAt == null || monAt!!.status == AsyncTask.Status.FINISHED) {
            monAt = MonAt(et.text.toString())
            monAt?.execute()
        } else {
            Toast.makeText(this, "Tache déjà en cours", Toast.LENGTH_SHORT).show();
        }
    }
    inner class MonAt(val search: String) : AsyncTask<Unit, Unit, Unit>() {

        var resultat: ArrayList<Item>? = null
        var exception: Exception? = null

        override fun onPreExecute() {
            super.onPreExecute()
            pb.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: Unit?) {
            try {
                resultat = wsGetPictureSearch(search)
            } catch (e: Exception) {
                exception = e
                e.printStackTrace()
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            pb.visibility = View.GONE
            exception?.let {
                Toast.makeText(this@SearchNasaPicture, "Une erreur : ${it.message}", Toast.LENGTH_SHORT).show();
            }
            resultat?.let {
                data.clear()
                data.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
