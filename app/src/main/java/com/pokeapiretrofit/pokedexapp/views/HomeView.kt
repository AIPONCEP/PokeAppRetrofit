package com.pokeapiretrofit.pokedexapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pokeapiretrofit.pokedexapp.components.CardPokemon
import com.pokeapiretrofit.pokedexapp.components.MainTopBar
import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.CUSTOM_BLACK
import com.pokeapiretrofit.pokedexapp.viewmodel.PokedexViewModel

@Composable
fun HomeView(viewModel: PokedexViewModel, navController:NavController){
    Scaffold(
        topBar= {
            MainTopBar(title = "Pokedex") {
            }
        }
    ) {
        ContentHomeView(viewModel = viewModel, pad = it, navController)
    }
}

@Composable
fun ContentHomeView (viewModel:PokedexViewModel, pad: PaddingValues, navController: NavController){
    val pokemon by viewModel.pokemon.collectAsState()
    LazyColumn( modifier= Modifier
        .padding(pad)
        .background(Color(CUSTOM_BLACK))

    ){
        items(pokemon) {item->
            CardPokemon(item) {
                navController.navigate("DetailView/${item.id}")
            }
            Row{
                Text( text= item.name,
                    fontWeight= FontWeight.ExtraBold,
                    color= Color.White,
                    modifier= Modifier.padding(start=10.dp)
                )
                Text( text= item.formatId,
                    fontWeight= FontWeight.ExtraBold,
                    color= Color.White,
                    modifier= Modifier.padding(start=10.dp)
                )
            }
        }
    }
}