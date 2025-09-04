package com.example.newsapp

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeNews(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeNews(modifier: Modifier = Modifier){
    var text = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Top Bar
        OutlinedTextField(
            value = "Buscar",
            onValueChange = { text.value = it },
            placeholder = {
                Text(text = "Buscar")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        )
        // Tabs Row
        TabsRow()
        // Ultimas Noticias
        LastNews()
        // Al rededor del mundo
        World()
    }
}

@Composable
fun TabsRow(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
                ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = "Noticias",
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = "Eventos",
            color = Color.Gray,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = "Clima",
            color = Color.Gray,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun LastNews(){
    val news = listOf(
        New(
            titulo = "El presidente de EE.UU. no muestra signos de arrepentimiento...",
            fecha = "febrero 08 - 2024"
        ),
        New(
            titulo = "Bañistas llenan las piscinas de Cancún...",
            fecha = "febrero 10 – 2024"
        ),
        New(
            titulo = "Nuevas protestas en el centro de la ciudad...",
            fecha = "febrero 12 – 2024"
        )
    )
    Text(
        text = "Ultimas Noticias",
        fontSize = 23.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 10.dp)
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(news) { new ->
            NewsCard(new)
        }
    }
}

data class New(
    val titulo: String,
    val fecha: String
)

@Composable
fun NewsCard(new: New){
    Box(
        modifier = Modifier
            .width(250.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.DarkGray)
            .padding(15.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = new.titulo,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp
            )
            Text(
                text = new.fecha,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
        }
    }
}

@Composable
fun World() {
    Text(
        text = "Alrededor del mundo", // ojo con la ortografía ; )
        fontSize = 23.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp), // o .weight(1f) si vas a repartir con weights
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(newsList) { noticia ->
            GridNewsCard(noticia)
        }
    }
}

@Composable
fun GridNewsCard(noticia: Noticia) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.LightGray)
            .padding(bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.DarkGray)
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray)
                .padding(12.dp)
        ) {
            Text(
                text = noticia.titulo,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


data class Noticia(val titulo: String)

val newsList = listOf(
    Noticia("El presidente de EE.UU. no muestra signos de arrepentimiento..."),
    Noticia("Bañarse en la piscina del desierto de Cleopatra"),
    Noticia("Gigantes tecnológicos"),
    Noticia("El rover de Marte envía"),
    Noticia("Descubren nueva especie marina"),
    Noticia("La ciudad implementa zonas verdes")
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppTheme {
        HomeNews()
    }
}