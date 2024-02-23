package com.pokeapiretrofit.pokedexapp.data

import com.pokeapiretrofit.pokedexapp.di.ApiModule
import com.pokeapiretrofit.pokedexapp.model.PokeModel
import com.pokeapiretrofit.pokedexapp.model.PokeModelDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
// Clase que proporciona métodos para interactuar con la API de Poke
class PokeApiService {
    // Instancia de Retrofit para realizar solicitudes a la API
    private val retrofit = ApiModule.providesRetrofit()
    // Método suspendido para obtener la lista de Pokémon
    suspend fun getPokemons(): List<PokeModel> {
        return withContext(Dispatchers.IO) {
            // Realiza la solicitud a la API para obtener la lista de Pokémon
            val response = retrofit.create(PokeApiClient::class.java).getListPokemon()
            // Devuelve la lista de Pokémon si la respuesta no es nula, de lo contrario devuelve una lista vacía
            response.body()?.pokemons ?: emptyList()
        }
    }
    // Método suspendido para obtener los detalles de un Pokémon específico por su ID
    suspend fun getDetailsPokemon(id: Int): PokeModelDetails?{
        return withContext(Dispatchers.IO) {
            // Realiza la solicitud a la API para obtener los detalles de un Pokémon por su ID
            val response = retrofit.create(PokeApiClient::class.java).getDetailsPokemon(id)
            // Devuelve los detalles del Pokémon si la respuesta no es nula
            response.body()
        }
    }
}