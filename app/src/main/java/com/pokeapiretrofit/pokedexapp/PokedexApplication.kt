package com.pokeapiretrofit.pokedexapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
// Anotación HiltAndroidApp para habilitar la inyección de dependencias con Hilt
@HiltAndroidApp
class PokedexApplication: Application() {
}