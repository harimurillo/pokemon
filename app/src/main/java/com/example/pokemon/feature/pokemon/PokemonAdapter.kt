package com.example.pokemon.feature.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.model.PokemonResult
import com.example.pokemon.util.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*


class PokemonAdapter(val context: Context, val pokemons: ArrayList<PokemonResult>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pokemons.count()

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        val item = holder.itemView
        val pokemon = pokemons[position]

        item.tvName.text = pokemon.name


        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${position+1}.png")
            .transform(CircleTransform())
            .into(item.ivProfile)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}



