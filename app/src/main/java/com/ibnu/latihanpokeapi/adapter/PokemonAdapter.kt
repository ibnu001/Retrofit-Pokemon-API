package com.ibnu.latihanpokeapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.latihanpokeapi.R

class PokemonAdapter(
    private val listPokemon: List<String>,
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvItemPoke : TextView = v.findViewById(R.id.tvItemPokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemPoke.text = listPokemon[position]
    }

    override fun getItemCount(): Int = listPokemon.size
}