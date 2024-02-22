package com.pokeapiretrofit.pokedexapp.state

data class PokemonState(
    val id: String = "",
    val name: String = "",
    val img: String = "",
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val specialAttack: Int = 0,
    val specialDefense: Int = 0,
    val speed: Int = 0,
    val types: List<String> = emptyList(),
    val weight: Double = 0.0,
    val height: Double = 0.0
)
