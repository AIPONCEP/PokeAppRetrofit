package com.pokeapiretrofit.pokedexapp.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pokeapiretrofit.pokedexapp.dataStore.StoreDarkMode
import com.pokeapiretrofit.pokedexapp.model.PokeItem
import com.pokeapiretrofit.pokedexapp.model.PokeItemDetails

import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.CUSTOM_BLACK
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, showBackButton:Boolean=false, onClickBackButton:()->Unit){
    val context = LocalContext.current // Obtenemos el contexto actual
    val scope = rememberCoroutineScope() // Obtenemos el CoroutineScope

    val darkModeStore = StoreDarkMode(context)
    val darkMode = darkModeStore.getDarkMode.collectAsState(initial = false)
    TopAppBar(
        title= { Text(text=title, color= Color.White, fontWeight = FontWeight.ExtraBold) },
        colors=TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(CUSTOM_BLACK)
        ),
        navigationIcon = {
            if (showBackButton){
                IconButton(onClick={onClickBackButton()}){
                    Icon(imageVector= Icons.Default.ArrowBack, contentDescription = null, tint=Color.White)
                }
            }
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically, // Alineación vertical centrada
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "PokedexApp",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Switch(
                    checked = darkMode.value,
                    onCheckedChange = { isChecked ->
                        scope.launch {
                            darkModeStore.saveDarkMode(isChecked)
                        }
                    }
                )
            }
        }
    )
}


@Composable
fun CardPokemon(pokemon: PokeItem, onClick: ()->Unit){
    Card (
        shape= RoundedCornerShape(5.dp),
        modifier= Modifier
            .padding(10.dp)
            .shadow(40.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column {
            MainImage (image=pokemon.img)
        }

    }
}
/**
 * Composable que muestra una imagen principal
 */
@Composable
fun MainImage(image:String){
    // Utiliza rememberCoilPainter para cargar la imagen de manera asíncrona
    val image = rememberAsyncImagePainter(model = image)
    // Muestra la imagen utilizando la biblioteca de Coil para la carga asíncrona de imágenes
    Image (painter=image,
        contentDescription=null,
        contentScale= ContentScale.Fit,
        modifier= Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
    )
}

@Composable
fun PokemonWebsite(pokemonName: String){
    val context= LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikidex.net/wiki/$pokemonName"))
    Button(
        onClick = {context.startActivity(intent)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 5.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text("Ir a la página web",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            )
    }
}