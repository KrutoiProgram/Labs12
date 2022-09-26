package com.bignerdranch.android.labs11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OutputActivity : AppCompatActivity() {
    private val Weath : MutableList<Weather> = mutableListOf()
    private lateinit var rv: RecyclerView
    private var index: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)
        getWeath()
        Weath.forEach{
            Log.d("fff",it.toString())
        }
        val adapter = WeatherRVAdapter(this,Weath)
        val rvListener = object: WeatherRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(this@OutputActivity, WeatherActivity::class.java)
                intent.putExtra("index",position)
                index = position
                Toast.makeText(this@OutputActivity, "position: $position",Toast.LENGTH_SHORT).show()
                startActivity(intent)

            }
        }
        adapter.setOnClickListener(rvListener)
        rv = findViewById(R.id.recylerView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

    }
    private fun getWeath()
    {
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json : String = ""
        if(!preferences.contains("json")){
            return
        }
        else
        {
            json = preferences.getString("json","NOT_JSON").toString()

        }
        val WeathList = Gson().fromJson<List<Weather>>(json, object: TypeToken<List<Weather>>(){}.type)
        Weath.addAll(WeathList)
    }

    override fun onResume() {
        super.onResume()
        if(index != -1)
        {
            Weath.clear()
            getWeath()
            rv.adapter?.notifyItemChanged(index)

        }


    }
}