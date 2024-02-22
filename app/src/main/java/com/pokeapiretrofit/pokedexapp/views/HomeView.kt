package com.pokeapiretrofit.pokedexapp.views

import android.annotation.SuppressLint
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pokeapiretrofit.pokedexapp.components.CardPokemon
import com.pokeapiretrofit.pokedexapp.components.MainTopBar
import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.CUSTOM_BLACK
import com.pokeapiretrofit.pokedexapp.viewmodel.PokedexViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(viewModel: PokedexViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            MainTopBar(title = "Pokedex") {}
        }
    ) {
        ContentHomeView(viewModel = viewModel, navController = navController)
    }
}

@Composable
fun ContentHomeView(viewModel: PokedexViewModel, navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    val pokemon by viewModel.pokemon.collectAsState()

    Column {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search pokemon") },
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn(
            modifier = Modifier
                .background(Color(CUSTOM_BLACK))
        ) {
            val filteredPokemon = pokemon.filter {
                it.name.contains(searchText, ignoreCase = true)
            }
            items(filteredPokemon) { item ->
                CardPokemon(item) {
                    navController.navigate("DetailView/${item.id}")
                }
                Row {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = item.formatId,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
    }
}