package com.example.toolxpress.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.components.PostCard
import com.example.toolxpress.ui.theme.data.model.PostModel

@Preview (showBackground = true)
@Composable
private fun Bars() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Row (
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(10.dp, 50.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Icon(Icons.Filled.Menu, contentDescription = "", tint = Color.White)
            Text(
                text ="App Title",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Icon(Icons.Filled.Settings, contentDescription = "", tint = Color.White)
        }
        val post = arrayOf(
            PostModel(1,"Title 1","Text 1",painterResource(R.drawable.ejemploimagen)),
            PostModel(2,"Title 2","Text 2",painterResource(R.drawable.ejemploimagen)),
            PostModel(3,"Title 3","Text 3",painterResource(R.drawable.ejemploimagen)),
            PostModel(4,"Title 4","Text 4",painterResource(R.drawable.ejemploimagen)),
            PostModel(5,"Title 5","Text 5",painterResource(R.drawable.ejemploimagen)),
            PostModel(6,"Title 6","Text 6",painterResource(R.drawable.ejemploimagen)),
            PostModel(7,"Title 7","Text 7",painterResource(R.drawable.ejemploimagen)),
            PostModel(8,"Title 8","Text 8",painterResource(R.drawable.ejemploimagen)),
            PostModel(9,"Title 9","Text 9",painterResource(R.drawable.ejemploimagen)),
            PostModel(10,"Title 10","Text 10",painterResource(R.drawable.ejemploimagen)),
        )
        Column (
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(10.dp, 90.dp, 10.dp, 50.dp)
                .fillMaxSize()
        )
        {
            //Posts(post)
            //PostCard(1,"This is a card title", "This is a card text", painterResource(R.drawable.descargar))
            PostGrid(post)
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(65.dp)
                .background(Color.Black)
                .padding(2.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column {
                IconButton(onClick = {}, Modifier.size(30.dp)) {
                    Icon(Icons.Outlined.Home,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(text = "Home", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, Modifier.size(30.dp)) {
                    Icon(Icons.Outlined.Search,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(text = "Search", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, Modifier.size(30.dp)) {
                    Icon(Icons.Outlined.Person,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(text = "Profile", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, Modifier.size(30.dp)) {
                    Icon(Icons.Outlined.Notifications,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(text = "Alerts", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, Modifier.size(30.dp)) {
                    Icon(Icons.Outlined.Call,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(text = "Contact", color = Color.White)
            }
        }
    }
}

@Composable
fun PostGrid(arrayPosts:Array<PostModel>) {
    LazyVerticalGrid (
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(arrayPosts){ post->
            PostCard(post.id, post.title, post.title, post.image)
        }
    }
}