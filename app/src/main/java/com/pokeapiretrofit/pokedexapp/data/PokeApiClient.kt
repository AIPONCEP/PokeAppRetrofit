package com.pokeapiretrofit.pokedexapp.data

import com.pokeapiretrofit.pokedexapp.model.PokeModelDetails
import com.pokeapiretrofit.pokedexapp.model.ResultApi
import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.ENDPOINT
import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.ENDPOINT2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//Aquí se pone lo relativo a las consultas a la API
interface PokeApiClient {

    @GET(ENDPOINT)
    suspend fun getListPokemon(): Response<ResultApi>

    @GET(ENDPOINT2)
    suspend fun getDetailsPokemon(@Path("id") id: Int): Response<PokeModelDetails>

}