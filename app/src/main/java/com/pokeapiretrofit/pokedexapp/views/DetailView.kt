package com.pokeapiretrofit.pokedexapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pokeapiretrofit.pokedexapp.components.MainImage
import com.pokeapiretrofit.pokedexapp.components.MainTopBar
import com.pokeapiretrofit.pokedexapp.components.PokemonWebsite
import com.pokeapiretrofit.pokedexapp.model.PokeItemDetails
import com.pokeapiretrofit.pokedexapp.util.Constants.Companion.CUSTOM_BLACK
import com.pokeapiretrofit.pokedexapp.viewmodel.PokedexViewModel


@Composable
fun DetailView(viewModel: PokedexViewModel, navController: NavController, id:Int){
    LaunchedEffect(Unit){
        viewModel.getPokemonById(id)
    }
    Scaffold(
        topBar= {
            MainTopBar(title = viewModel.state.name, showBackButton = true) {
                navController.popBackStack()
            }
        }
    ){
        ContenDetailView(pad = it, viewModel = viewModel, navController = navController)
    }

}

@Composable
fun ContenDetailView(pad: PaddingValues, viewModel: PokedexViewModel, navController: NavController){
    val state= viewModel.state
    Column(modifier= Modifier
        .padding(pad)
        .background(Color(CUSTOM_BLACK))
        .fillMaxSize()
    ) {
        MainImage(image=state.img)
        Spacer(modifier=Modifier.height(10.dp))

        PokemonWebsite(state.name)

        Spacer(modifier=Modifier.height(10.dp))
        getPokeDetailsView(viewModel)
    }
}

@Composable
fun getPokeDetailsView(viewModel: PokedexViewModel){
    val state= viewModel.state
    val joinedTypes = state.types.joinToString()
    val typeColor = getColorForType(state.types.firstOrNull() ?: "") // Obtener el color del primer tipo (si existe)

    Row (
        modifier= Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(text="Hp: "+state.hp,
                color= Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier=Modifier
                    .padding(top=5.dp, bottom=5.dp)
            )
            Text(text="Attack: "+state.attack,
                color= Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier=Modifier
                    .padding(top=5.dp, bottom=5.dp)
            )
            Text(text="Defense: "+state.defense,
                color= Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier=Modifier
                    .padding(top=5.dp, bottom=5.dp)
            )
            Text(text="Special Attack: "+state.specialAttack,
                color= Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier=Modifier
                    .padding(top=5.dp, bottom=5.dp)
            )
            Text(text="Special Defense: "+state.specialDefense,
                color= Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier=Modifier
                    .padding(top=5.dp, bottom=5.dp)
            )
            Text(text="Speed: "+state.speed,
                color= Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier=Modifier
                    .padding(top=5.dp, bottom=5.dp)
            )
            Text(
                text = "Type: $joinedTypes",
                color = typeColor,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )
            Text(text="Weight: "+state.weight,
                color= Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier=Modifier
                    .padding(top=5.dp, bottom=5.dp)
            )
            Text(text="Height: "+state.height,
                color= Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier=Modifier
                    .padding(top=5.dp, bottom=5.dp)
            )
        }
    }
}

fun getColorForType(type: String): Color {
    return when (type) {
        "Fire" -> Color.Red
        "Water" -> Color.Blue
        "Grass" -> Color.Green
        "Fairy" -> Color.Magenta
        else -> Color.White // Color por defecto
    }
}
