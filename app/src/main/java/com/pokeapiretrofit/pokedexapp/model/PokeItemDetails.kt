package com.pokeapiretrofit.pokedexapp.model

import java.util.Locale

data class PokeItemDetails(
    val id: String,
    val name: String,
    val img: String,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense:Int,
    val speed: Int,
    val types: List<String>,
    val weight: Double,
    val height: Double,
)
// Extensión de la clase PokeModelDetails que convierte un objeto PokeModelDetails en un objeto PokeItemDetails
fun PokeModelDetails.toDomain(): PokeItemDetails {
    // Formatea el ID agregando ceros a la izquierda si es necesario y lo convierte en una cadena con el prefijo "N°"
    val id = "N° ${id.toString().padStart(3,'0')}"
    // Formatea el nombre para que la primera letra esté en mayúscula
    val name = replaceFirstChar(name)
    // Obtiene la URL de la imagen oficial del Pokémon
    val img = sprites.other.officialArtwork.img
    // Obtiene los valores de las estadísticas del Pokémon
    val hp = pokemonDetails[0].statValue
    val attack = pokemonDetails[1].statValue
    val defense = pokemonDetails[2].statValue
    val specialAttack = pokemonDetails[3].statValue
    val specialDefense = pokemonDetails[4].statValue
    val speed = pokemonDetails[5].statValue
    // Obtiene los tipos del Pokémon
    val types = getTypes(types)
    // Convierte el peso y la altura de decímetros a metros
    val weight = weight / 10.0
    val height = height / 10.0
    // Retorna un nuevo objeto PokeItemDetails con los datos formateados
    return PokeItemDetails(id, name, img, hp, attack, defense,
        specialAttack, specialDefense, speed, types, weight, height)
}

private fun getTypes(types: List<Types>): List<String> {
    return if (types.size > 1) {
        listOf(replaceFirstChar(types[0].type.name), replaceFirstChar(types[1].type.name))
    } else {
        listOf(replaceFirstChar(types[0].type.name))
    }
}

private fun replaceFirstChar(t: String): String {
    return t.replaceFirstChar {
        // cada nombre con Mayuscula
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}