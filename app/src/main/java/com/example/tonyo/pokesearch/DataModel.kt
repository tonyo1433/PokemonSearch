package com.example.tonyo.pokesearch

/**
 * Created by Tonyo on 3/17/2018.
 */
class PokemonData(val id: Int,
                  val name: String,
                  val sprite: Sprite,
                  val height: String,
                  val weight: String,
                  val types: String,
                  val skill: String)

class Sprite(val pokeString: String)