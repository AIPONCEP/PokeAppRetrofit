package com.pokeapiretrofit.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.anluisa.gamesretrofit.navigation.NavManager
import com.pokeapiretrofit.pokedexapp.dataStore.StoreDarkMode
import com.pokeapiretrofit.pokedexapp.ui.theme.PokedexAppTheme
import com.pokeapiretrofit.pokedexapp.viewmodel.PokedexViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : PokedexViewModel by viewModels()

        setContent {
            val darkModeStore= StoreDarkMode(this)
            val darkMode= darkModeStore.getDarkMode.collectAsState(initial = false)
            PokedexAppTheme(
                darkTheme=darkMode.value
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(viewModel)
                }
            }
        }
    }
}

