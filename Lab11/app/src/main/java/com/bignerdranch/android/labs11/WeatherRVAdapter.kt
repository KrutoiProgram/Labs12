package com.bignerdranch.android.labs11

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherRVAdapter(context: Context?, val data: MutableList<Weather>): RecyclerView.Adapter<WeatherRVAdapter.WeatherViewHolder?>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    private var iClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: View = layoutInflater.inflate(R.layout.item_weather,parent,false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = data[position]
        holder.dateTime.text = item.Date
        holder.dayTemp.text = item.Daytime_Temperature
        holder.nightTemp.text = item.NightTemp
    }

    override fun getItemCount(): Int = data.size
    inner class WeatherViewHolder(item: View): RecyclerView.ViewHolder(item), View.OnClickListener {
        var dateTime = item.findViewById<TextView>(R.id.DateTime)
        var dayTemp = item.findViewById<TextView>(R.id.Day_Temp)
        var nightTemp = item.findViewById<TextView>(R.id.Night_Temp)
        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View?){
            iClickListener?.onItemClick(view,adapterPosition)
        }
    }
    fun setOnClickListener(itemClickListener: ItemClickListener?){
        iClickListener = itemClickListener
    }
    interface ItemClickListener {
        fun onItemClick(view: View?,position: Int)
    }
}