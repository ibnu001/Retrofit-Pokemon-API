package com.ibnu.latihanpokeapi.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibnu.latihanpokeapi.ListPokemonActivity
import com.ibnu.latihanpokeapi.R
import com.ibnu.latihanpokeapi.ResultsItem
import com.ibnu.latihanpokeapi.SecondActivity
import com.ibnu.latihanpokeapi.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import kotlin.math.hypot

class PokemonAdapter(
    private val listPokemon: List<String>,
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvItemPoke: TextView = v.findViewById(R.id.tvItemPoke)
//        val ivItemImagePoke: ImageView = v.findViewById(R.id.ivItemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poke, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPokemon[position]

        holder.tvItemPoke.text = item
//        Glide.with(holder.itemView)
//            .load(item)
//            .centerCrop()
//            .into(holder.ivItemImagePoke)

        Log.e("TAG", "onBindViewHolder: $item")

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SecondActivity::class.java)

            intent.putExtra(SecondActivity.ID, position.plus(1).toString())
            holder.itemView.context.startActivity(intent)
            Log.e("TAG", "onBindViewHolder: $position", )
        }
    }

    override fun getItemCount(): Int = listPokemon.size
}