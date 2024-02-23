package com.pokeapiretrofit.pokedexapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokeapiretrofit.pokedexapp.PokeState.PokemonState
import com.pokeapiretrofit.pokedexapp.model.PokeItem
import com.pokeapiretrofit.pokedexapp.repository.PokeApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
// ViewModel para manejar la lógica de la pantalla principal de la Pokédex
@HiltViewModel
class PokedexViewModel @Inject constructor(private val repo: PokeApiRepository): ViewModel() {
    // Flujo mutable para almacenar la lista de Pokémon
    private val _pokemon= MutableStateFlow<List<PokeItem>> (emptyList())
    val pokemon= _pokemon.asStateFlow() // Flujo inmutable para exponer la lista de Pokémon al UI
    // Estado actual de la pantalla
    var state by mutableStateOf(PokemonState())
        private set
    // Inicialización del ViewModel, se llama automáticamente al crear una instancia del ViewModel
    init{
        fetchPokemon()// Obtener la lista de Pokémon al inicializar el ViewModel
    }
    // Función para obtener la lista de Pokémon
    private fun fetchPokemon(){
        viewModelScope.launch {
            // Ejecutar en el hilo IO para realizar la solicitud a la API
            withContext(Dispatchers.IO){
                // Obtener la lista de Pokémon del repositorio
                val result=repo.getAllPokemons()
                // Actualizar el flujo de Pokémon con los resultados obtenidos
                _pokemon.value=result ?: emptyList()
            }
        }
    }
    // Función para obtener los detalles de un Pokémon por su ID
    fun getPokemonById(id: Int){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                // Obtener los detalles del Pokémon del repositorio
                val result=repo.getPokeDetails(id)
                // Actualizar el estado de la pantalla con los detalles del Pokémon obtenidos
                state=state.copy(
                    name=result?.name ?: "",
                    img = result?.img ?: "",
                    hp = result?.hp ?: 0,
                    attack = result?.attack ?: 0,
                    defense = result?.defense ?: 0,
                    specialAttack = result?.specialAttack ?: 0,
                    specialDefense = result?.specialDefense ?: 0,
                    speed = result?.speed ?: 0,
                    types = result?.types ?: emptyList(),
                    weight = result?.weight ?: 0.0,
                    height = result?.height ?: 0.0,
                )
            }
        }
    }
}