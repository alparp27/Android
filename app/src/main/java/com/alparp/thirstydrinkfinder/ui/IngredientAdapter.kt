package com.alparp.thirstydrinkfinder.ui

import android.R.color
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alparp.thirstydrinkfinder.R


class IngredientAdapter(val context: Context) :
    RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    var items: List<String> = listOf()

    companion object {
        val colors = arrayListOf<Int>(
            R.color.design_default_color_primary,
            R.color.design_default_color_secondary,
            R.color.design_default_color_on_secondary,
            R.color.design_default_color_error,
            R.color.material_on_surface_stroke,
            R.color.material_on_surface_disabled,
            R.color.design_default_color_primary,
            R.color.design_default_color_secondary,
            R.color.design_default_color_on_secondary,
            R.color.design_default_color_primary,
            R.color.design_default_color_secondary,
            R.color.design_default_color_on_secondary,
            R.color.design_default_color_primary,
            R.color.design_default_color_secondary,
            R.color.design_default_color_on_secondary
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_text, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position]?.let {
            holder.title.text = it
            for (drawable in holder.title.compoundDrawables) {
                if (drawable != null) {
                    drawable.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(
                            holder.title.context,
                            colors[position]
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }
    }

    fun setListItems(items: List<String>) {
        this.items = items;
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView!!.findViewById(R.id.title)
    }
}