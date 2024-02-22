package com.pokeapiretrofit.pokedexapp.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pokeapiretrofit.pokedexapp.model.PokeItem
import com.pokeapiretrofit.pokedexapp.model.PokeItemDetails

import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.CUSTOM_BLACK

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, showBackButton:Boolean=false, onClickBackButton:()->Unit){
        TopAppBar(
            title= {Text(text=title, color= Color.White, fontWeight = FontWeight.ExtraBold)},
            colors=TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color(CUSTOM_BLACK)
            ),
            navigationIcon = {
                if (showBackButton){
                    IconButton(onClick={onClickBackButton()}){
                        Icon(imageVector= Icons.Default.ArrowBack, contentDescription = null, tint=Color.White)

                    }
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

@Composable
fun MainImage(image:String){
    val image = rememberAsyncImagePainter(model = image)
    Image (painter=image,
        contentDescription=null,
        contentScale= ContentScale.Fit,
        modifier= Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
    )
}

