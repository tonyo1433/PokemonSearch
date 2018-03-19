package com.example.tonyo.pokesearch
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.poke_ability.view.*
/**
 * Created by Tonyo on 3/17/2018.
 */
class MainAdapter (val pokemon: ArrayList<PokemonData>): RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        holder?.view?.txtHgt?.text = pokemon[position].height
        holder?.view?.txtWgt?.text = pokemon[position].weight
        holder?.view?.txtType?.text = pokemon[position].types
        holder?.view?.txtSkill?.text = pokemon[position].skill
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.poke_ability, parent, false)
        return CustomViewHolder(view)    }
}

class CustomViewHolder(var view: View): RecyclerView.ViewHolder(view) {

}