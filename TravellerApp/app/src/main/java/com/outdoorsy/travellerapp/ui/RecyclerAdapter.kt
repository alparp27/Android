package com.outdoorsy.travellerapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.outdoorsy.travellerapp.R
import com.outdoorsy.travellerapp.model.Rental

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var items : List<Rental> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_icon_name,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].getTitle()?.let {
            holder.title.text = it
        }
        items[position].getImageUrl()?.let {
            Glide.with(context).load(it)
                .apply(RequestOptions().centerCrop())
                .into(holder.image)
        }
    }

    fun setListItems(movieList: List<Rental>){
        this.items = movieList;
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView!!.findViewById(R.id.title)
        val image: ImageView = itemView!!.findViewById(R.id.image)

    }
}