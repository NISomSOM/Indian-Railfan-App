package com.example.indianrailfanapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.indianrailfanapp.data.Locomotive


@Composable
fun LocoDetailScreen(loco: Locomotive) {
    Column(modifier=Modifier.fillMaxSize()){
        Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomEnd = 16.dp, bottomStart = 16.dp)), contentAlignment = Alignment.Center){
            Text(text=loco.locoName, textAlign = TextAlign.Center,style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp), modifier = Modifier.padding(top=16.dp, bottom = 16.dp))}
        Box(modifier = Modifier.height(300.dp)){
            AsyncImage(
                model=loco.locoImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )}
        Spacer(modifier = Modifier.height(16.dp))

        // Centered stats row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem(icon = Icons.Default.Speed, label = "Speed", value = loco.locoTopSpeed)
            StatItem(icon = Icons.Default.Bolt, label = "Power", value = loco.locoHorsePower)
            StatItem(icon = Icons.Default.Timeline, label = "Lifespan", value = loco.locoLifeSpan)
            StatItem(icon = Icons.Default.Build, label = "Units", value = loco.locoTotalProduced)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxHeight().fillMaxWidth().clip(RoundedCornerShape(16.dp))){
            Text(text=loco.locoDescription, textAlign = TextAlign.Left,style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp))
        }
    }
}
@Composable
fun StatItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = label, tint = Color.DarkGray, modifier = Modifier.size(28.dp))
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}