package com.anluisa.gamesretrofit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pokeapiretrofit.pokedexapp.model.PokeItemDetails
import com.pokeapiretrofit.pokedexapp.viewmodel.PokedexViewModel
import com.pokeapiretrofit.pokedexapp.views.DetailView
import com.pokeapiretrofit.pokedexapp.views.HomeView

@Composable
fun NavManager(viewModel:PokedexViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(viewModel, navController)
        }

        composable("DetailView/{id}", arguments= listOf(
            navArgument("id"){
                type= NavType.IntType
            }
        )){
            val id = it.arguments?.getInt("id")?:0
            DetailView(viewModel, navController, id)
        }
    }
}
