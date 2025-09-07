package com.example.indianrailfanapp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.indianrailfanapp.MainViewModel
import com.example.indianrailfanapp.data.Locomotive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(viewState: MainViewModel.LocoState, navigateToDetail: (Locomotive) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val allItems = viewState.list
    val filteredItems = allItems.filter { it.locoName.contains(searchQuery, ignoreCase = true) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(filteredItems) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(top=8.dp,start=8.dp,end=8.dp)
                        .clickable{navigateToDetail(item)},
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Row(modifier=Modifier.padding(16.dp)){
                        AsyncImage(
                            model = item.locoImage,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(75.dp)
                        )
                        Text(text = item.locoName, modifier = Modifier.padding(16.dp, top =24.dp), style= TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                    }
                }
            }
        }
    }
}
