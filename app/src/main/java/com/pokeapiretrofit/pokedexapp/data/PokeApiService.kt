package com.pokeapiretrofit.pokedexapp.data

import com.pokeapiretrofit.pokedexapp.di.ApiModule
import com.pokeapiretrofit.pokedexapp.model.PokeModel
import com.pokeapiretrofit.pokedexapp.model.PokeModelDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokeApiService {

    private val retrofit = ApiModule.providesRetrofit()

    suspend fun getPokemons(): List<PokeModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokeApiClient::class.java).getListPokemon()
            response.body()?.pokemons ?: emptyList()
        }
    }

    suspend fun getDetailsPokemon(id: Int): PokeModelDetails?{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokeApiClient::class.java).getDetailsPokemon(id)
            response.body()
        }
    }
}