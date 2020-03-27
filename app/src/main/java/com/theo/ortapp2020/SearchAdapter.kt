package com.theo.ortapp2020

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.theo.ortapp2020.beans.Item

class SearchAdapter(
    val data: ArrayList<Item>,
    val listener: SearchAdapterListener
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv1 = itemView.findViewById<TextView>(R.id.tv1)
//        val tv2 = itemView.findViewById<TextView>(R.id.tv2)
        val iv: ImageView = itemView.findViewById(R.id.iv)
        val root: View = itemView.findViewById(R.id.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_search, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datum = data[position]
        holder.tv1.text = datum.data[0].title
//        holder.tv2.text = datum.data[0].description

        Picasso.get().load(datum.links[0].href).into(holder.iv)

        holder.root.setOnClickListener {
            listener.onSearchBeanClick(datum)
        }

    }


    override fun getItemCount() = data.size

    interface SearchAdapterListener {
        fun onSearchBeanClick(item: Item)
        fun onSearchBeanLongClick(item: Item)
    }

}