package com.example.indianrailfanapp.views

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.indianrailfanapp.MainViewModel
import com.example.indianrailfanapp.data.Locomotive


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navigateToDetail: (Locomotive) -> Unit, viewState: MainViewModel.LocoState) {
    var selectedFilter by remember {mutableStateOf("All")}
    val filteredList = when (selectedFilter) {
        "Electric" -> viewState.list.filter { it.locoType == "electric" }
        "Diesel" -> viewState.list.filter { it.locoType == "diesel" }
        else -> viewState.list
    }
    var isToggled by rememberSaveable { mutableStateOf(false) }


    Column() {
        if (viewState.list.isNotEmpty()) { //Because carousel only checks the list once at the start, and our list will be empty at the start due to loading from api
            HorizontalUncontainedCarousel(
                state = rememberCarouselState { viewState.list.count() },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, bottom = 8.dp)
                    .height(250.dp),
                itemWidth = 300.dp,
                itemSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) { i ->
                val item = viewState.list[i] //item is a loco right now
                Log.d("CarouselItemDebug", "Item image URL: ${item.locoImage}")
                AsyncImage(
                    model = item.locoImage,
                    modifier = Modifier
                        .height(300.dp),
                    contentDescription = item.locoName,
                    contentScale = ContentScale.Crop,
                    onSuccess = { result ->
                        Log.d("CoilCSuccess", "Successfully loaded image: ${item.locoImage}")
                    },
                    onError = { error ->
                        Log.e("CoilCError", "Error loading image: ${item.locoImage}", error.result.throwable)
                    }
                )
            }
        }
        Row() {
            IconButton(onClick = {
                selectedFilter = "All"
            }) {
                Icon(imageVector = Icons.Filled.AllInclusive, contentDescription = "All",tint= if (selectedFilter=="All") Color(3, 152, 252) else MaterialTheme.colorScheme.onBackground)
            }
            IconButton(onClick = {
                selectedFilter = "Electric"
            }) {
                Icon(imageVector = Icons.Filled.ElectricBolt, contentDescription = "Electric",tint= if (selectedFilter=="Electric") Color(3, 152, 252) else MaterialTheme.colorScheme.onBackground)
            }
            IconButton(onClick = {
                selectedFilter = "Diesel"
            }) {
                Icon(imageVector = Icons.Filled.LocalGasStation, contentDescription = "Diesel",tint= if (selectedFilter=="Diesel") Color(3, 152, 252) else MaterialTheme.colorScheme.onBackground)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when {
                viewState.loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                viewState.error != null -> {
                    Text(text = "Error Occurred", modifier = Modifier.align(Alignment.Center))
                }

                else -> {
                    LocoList(locomotives = filteredList, navigateToDetail)
                }
            }
        }
    }
}

@Composable
fun LocoList(locomotives: List<Locomotive>, navigateToDetail: (Locomotive) -> Unit) {
    LazyVerticalGrid(GridCells.Fixed(2),modifier=Modifier.fillMaxSize().animateContentSize()){
        items(locomotives, key={it.locoId}){
            LocoItem(it, navigateToDetail, modifier = Modifier
                .animateItem())
        }
    }
}

@Composable
fun LocoItem(locomotive: Locomotive,navigateToDetail:(Locomotive)->Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                navigateToDetail(locomotive)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = locomotive.locoImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onSuccess = { result ->
                Log.d("CoilLSuccess", "Successfully loaded image: ${locomotive.locoImage}")
            },
            onError = { error ->
                Log.e("CoilLError", "Error loading image: ${locomotive.locoImage}", error.result.throwable)
            },
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = locomotive.locoName,
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
