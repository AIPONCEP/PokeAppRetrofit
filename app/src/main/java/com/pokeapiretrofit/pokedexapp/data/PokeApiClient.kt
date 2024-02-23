package com.pokeapiretrofit.pokedexapp.data

import com.pokeapiretrofit.pokedexapp.model.PokeModelDetails
import com.pokeapiretrofit.pokedexapp.model.ResultApi
import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.ENDPOINT
import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.ENDPOINT2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz que define las funciones para realizar solicitudes a la API de Poke
 */
interface PokeApiClient {
    // Función suspendida para obtener la lista de Pokémon
    @GET(ENDPOINT)
    suspend fun getListPokemon(): Response<ResultApi>
    // Función suspendida para obtener los detalles de un Pokémon específico por su ID
    @GET(ENDPOINT2)
    suspend fun getDetailsPokemon(@Path("id") id: Int): Response<PokeModelDetails>

}