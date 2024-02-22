package com.pokeapiretrofit.pokedexapp.repository

import com.pokeapiretrofit.pokedexapp.data.PokeApiClient
import com.pokeapiretrofit.pokedexapp.data.PokeApiService
import com.pokeapiretrofit.pokedexapp.model.PokeItem
import com.pokeapiretrofit.pokedexapp.model.PokeItemDetails
import com.pokeapiretrofit.pokedexapp.model.toDomain
import javax.inject.Inject

class PokeApiRepository @Inject constructor(private val pokeApi:PokeApiClient){
    private val api = PokeApiService()

    suspend fun getAllPokemons(): List<PokeItem> {
        val response = api.getPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun getPokeDetails(id: Int): PokeItemDetails? {
        val response = api.getDetailsPokemon(id)
        return response?.toDomain()
    }
}