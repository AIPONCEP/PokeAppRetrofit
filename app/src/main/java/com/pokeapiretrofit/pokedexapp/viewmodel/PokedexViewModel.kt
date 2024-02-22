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

@HiltViewModel
class PokedexViewModel @Inject constructor(private val repo: PokeApiRepository): ViewModel() {

    private val _pokemon= MutableStateFlow<List<PokeItem>> (emptyList())
    val pokemon= _pokemon.asStateFlow()
    var state by mutableStateOf(PokemonState())
        private set

    init{
        fetchPokemon()
    }

    private fun fetchPokemon(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result=repo.getAllPokemons()
                _pokemon.value=result ?: emptyList()
            }
        }
    }

    fun getPokemonById(id: Int){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val result=repo.getPokeDetails(id)
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
                    height = result?.height ?: 0.0
                )
            }
        }
    }
}