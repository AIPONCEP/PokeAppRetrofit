package com.pokeapiretrofit.pokedexapp.model

import java.util.Locale

/**
 * Se recupera id, name e img
 */
data class PokeItem(
    val id: Int,
    val name: String,
    val img: String
){
    // formatId, formatea el ID como "N° XXX",
    // donde XXX es el ID del Pokémon con ceros a la izquierda si es necesario.
    val formatId = "N° ${id.toString().padStart(3,'0')}"
}

// Pag de donde se sacan las imagenes
// Nota: hay q añadir el nº y . png para sacar la imagen lo que se maneja con el método que está a continuación.
private const val URL_RAW = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

/**
 * Esta función es una extensión de la clase PokeModel.
 * Toma un objeto PokeModel y lo convierte en un PokeItem.
 * Extrae el ID del Pokémon del campo url del modelo.
 * Formatea el nombre del Pokémon capitalizando la primera letra de cada palabra.
 * Construye la URL de la imagen del Pokémon usando la constante URL_RAW y el ID extraído.
 * Retorna un nuevo PokeItem con los datos extraídos.
 */

fun PokeModel.toDomain(): PokeItem {
    val arrayUrl = url.split("/")
    val id = arrayUrl[arrayUrl.size - 2].toInt()
    val name = name.replaceFirstChar {
        // cada nombre con Mayuscula
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
    val img = "$URL_RAW$id.png"
    return PokeItem(id, name, img)
}