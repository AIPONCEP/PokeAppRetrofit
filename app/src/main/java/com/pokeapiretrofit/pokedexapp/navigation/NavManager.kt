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
// Function que gestiona la navegación en la aplicación
@Composable
fun NavManager(viewModel:PokedexViewModel){
    // Instancia de NavController para controlar la navegación
    val navController = rememberNavController()
    // Definición del destino de la navegación y las pantallas asociadas
    NavHost(navController = navController, startDestination = "Home"){
        // Pantalla de inicio
        composable("Home"){
            HomeView(viewModel, navController)
        }
        // Pantalla de detalle que recibe un argumento 'id'
        composable("DetailView/{id}", arguments= listOf(
            navArgument("id"){
                type= NavType.IntType
            }
        )){
            // Obtiene el valor del argumento 'id' de la ruta y lo asigna a la variable 'id', si es nulo asigna 0
            val id = it.arguments?.getInt("id")?:0
            // Muestra la pantalla de detalle con el 'id' obtenido y proporciona el ViewModel y NavController
            DetailView(viewModel, navController, id)
        }
    }
}
