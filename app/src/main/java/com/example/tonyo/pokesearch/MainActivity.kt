package com.example.tonyo.pokesearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val url = "https://pokeapi.co/api/v2/pokemon/"
    private val Key_ID = "id"
    private val Key_Name = "name"
    private val Key_Sprites = "sprites"
    private val Key_FrontDefault = "front_default"
    private val PokeList = ArrayList<PokemonData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.INVISIBLE

        btnSearch.setOnClickListener {
            PokeSearch()
            progressBar.visibility = View.VISIBLE
            PokeList.clear()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun PokeSearch() {

        doAsync {

            var tempPokeName = editText.text.toString()
            val resultJson = URL(url + tempPokeName).readText()
            val jsonObject = JSONObject(resultJson)
            val getID = jsonObject.getInt(Key_ID)
            val getName = jsonObject.getString(Key_Name)
            val getSprites = jsonObject.getJSONObject(Key_Sprites)
                    .getString(Key_FrontDefault)
            val getHgt = jsonObject.getString("height")
            val getWgt = jsonObject.getString("weight")
            val getType = jsonObject.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name")
            val getSkill = jsonObject.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("name")

            uiThread {
                recyclerView.adapter = MainAdapter(PokeList)
                PokeList.add(PokemonData(getID,
                        getName,
                        Sprite(getSprites),getHgt, getWgt, getType, getSkill))
                        txtPokeName.text = getName.substring(0, 1).toUpperCase() + getName.substring(1)
                        Picasso.with(this@MainActivity).load(getSprites).into(imgPoke)
                        progressBar.visibility = View.INVISIBLE
            }
        }
    }


}
