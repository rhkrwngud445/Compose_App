package com.app.compose_study.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.compose_study.data.model.Post

@Composable
fun HomeScreen(navHostController: NavHostController, posts: List<Post>) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        HomeListScreen(posts)
    }
}

@Composable
fun HomeListScreen(data: List<Post>) {
    val scrollState = rememberLazyGridState()

    LazyVerticalGrid( //  lazy는 지연 목록으로, 데이터의 수를 모르는 경우 맞춰서 변할 수 있음
        state = scrollState,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        modifier = Modifier
            .padding(top = 10.dp, start = 30.dp, end = 30.dp),
        userScrollEnabled = true
    ) {
        items(data.size) {
            GridItemCard(data = data[it])
        }
//        data.value.forEachIndexed { index, post ->
//            items(index) { _ ->
//                GridItemCard(data = post)
//            }
//        }
    }
}

@Composable
fun GridItemCard(data: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        shape = MaterialTheme.shapes.small.copy(CornerSize(20.dp)),
    ) {
        Column() {
            val modifier = Modifier.fillMaxHeight(0.6f)
            ItemImage(data.poster)
            ItemText(data)
        }
    }
}

@Composable
fun ItemText(data: Post) {
    Column(Modifier.padding(10.dp)) {
        Text(
            text = data.name,
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(text = data.name.toString(), fontSize = 13.sp, color = Color.Black)
//        Text(text = data.gender, fontSize = 13.sp, color = Color.Black)
//        Text(
//            text = if (data.student) "student"
//            else "not student",
//            fontSize = 13.sp
//        )
    }
}

@Composable
fun ItemImage(image: String) {
    Surface(shape = MaterialTheme.shapes.small.copy(CornerSize(20.dp))) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )
    }
}
