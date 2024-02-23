package com.pokeapiretrofit.pokedexapp.dataStore


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDarkMode (private val context: Context){

    companion object {
        private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("DarkMode")
        val DARK_MODE= booleanPreferencesKey("dark_mode")
    }

    val getDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences->
            preferences[DARK_MODE]?:false
        }

    suspend fun saveDarkMode(dark_mode:Boolean){
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE]=dark_mode }
    }
}