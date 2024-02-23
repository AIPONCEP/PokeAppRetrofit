package com.pokeapiretrofit.pokedexapp.repository

import com.pokeapiretrofit.pokedexapp.data.PokeApiClient
import com.pokeapiretrofit.pokedexapp.data.PokeApiService
import com.pokeapiretrofit.pokedexapp.model.PokeItem
import com.pokeapiretrofit.pokedexapp.model.PokeItemDetails
import com.pokeapiretrofit.pokedexapp.model.toDomain
import javax.inject.Inject

/**
 * Repositorio para manejar las operaciones relacionadas con la API de Pokémon
 */
class PokeApiRepository @Inject constructor(private val pokeApi:PokeApiClient){
    // Instancia de PokeApiService para interactuar con la API de Pokémon
   private val api = PokeApiService()
    // Función suspendida para obtener todos los Pokémon
    suspend fun getAllPokemons(): List<PokeItem> {
        // Realiza la solicitud para obtener la lista de Pokémon
        val response = api.getPokemons()
        // Mapea la respuesta a objetos PokeItem utilizando la función de extensión toDomain()
        return response.map { it.toDomain() }
    }
    // Función suspendida para obtener los detalles de un Pokémon específico por su ID
    suspend fun getPokeDetails(id: Int): PokeItemDetails? {
        // Realiza la solicitud para obtener los detalles de un Pokémon por su ID
        val response = api.getDetailsPokemon(id)
        // Mapea la respuesta a un objeto PokeItemDetails utilizando la función de extensión toDomain()
        return response?.toDomain()
    }
}