package com.pokeapiretrofit.pokedexapp.di

import com.pokeapiretrofit.pokedexapp.data.PokeApiClient
import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Aqui añádimos las dependencias
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    //Se añade retrofit
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Función para proveer a PokeApi
    @Singleton
    @Provides
    fun providesPokeApi(retrofit:Retrofit): PokeApiClient {
        return  retrofit.create(PokeApiClient::class.java)
    }

}