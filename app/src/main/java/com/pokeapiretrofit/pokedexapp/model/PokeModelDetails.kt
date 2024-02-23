package com.pokeapiretrofit.pokedexapp.model

import com.google.gson.annotations.SerializedName


/*
 * Esta clase se usa para mapear los datos de la respuesta de la API de
 * Pokémon a objetos Kotlin para facilitar su manipulación y uso en la aplicación.
 * https://pokeapi.co/api/v2/pokemon/25/
 */
/**
 * Esta clase representa los detalles de un Pokémon específico. Contiene propiedades como el ID, la altura,
 * el nombre, las imágenes, las estadísticas, los tipos y el peso del Pokémon.
 */
data class PokeModelDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("stats") val pokemonDetails: List<Stats>,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("weight") val weight: Int,
    // falta: debilidades -> Array
)
data class Sprites(
    @SerializedName("other") val other: Other
)

data class Other(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default") val img: String,
)

data class Stats(
    @SerializedName("base_stat") val statValue: Int,
    @SerializedName("stat") val stat: Stat
)

data class Stat(
    @SerializedName("name") val statName: String
)

data class Types(
    @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val name: String
)
