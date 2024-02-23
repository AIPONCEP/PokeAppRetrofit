package com.pokeapiretrofit.pokedexapp.dataStore


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Clase StoreDarkMode gestiona el estado del modo oscuro
class StoreDarkMode (private val context: Context){
    // Companion object para definir propiedades y funciones estáticas
    companion object {
        // Propiedad estática que permite acceder al DataStore asociado con la clave "DarkMode"
        private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("DarkMode")
        // Clave para acceder al valor booleano que indica si el modo oscuro está activado
        val DARK_MODE= booleanPreferencesKey("dark_mode")
    }
    // Flujo de tipo Booleano que emite el estado actual del modo oscuro
    val getDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences->
            // Mapeo del valor almacenado en el DataStore a un booleano (valor predeterminado: false si no está presente)
            preferences[DARK_MODE]?:false
        }
    // Función suspendida para guardar el estado del modo oscuro en el DataStore
    suspend fun saveDarkMode(dark_mode:Boolean){
        // Edición del DataStore para actualizar el valor del modo oscuro
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE]=dark_mode }
    }
}