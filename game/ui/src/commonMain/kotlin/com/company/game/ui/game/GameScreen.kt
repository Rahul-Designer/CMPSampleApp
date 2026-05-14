package com.company.game.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmpsampleapp.game.ui.generated.resources.Res
import cmpsampleapp.game.ui.generated.resources.ic_favorite_icon
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(modifier: Modifier = Modifier, onFavoriteClick: () -> Unit) {

    val viewModel: GameViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GameScreenContent(
        modifier = modifier,
        uiState = uiState,
        onFavoriteClick = {
            onFavoriteClick.invoke()
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScreen.UiState,
    onFavoriteClick: () -> Unit
) {

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = {
                Text(text = "Gamopedia")
            }, actions = {
                IconButton(onClick = {
                    onFavoriteClick.invoke()
                }) {
                    Image(
                        painterResource(Res.drawable.ic_favorite_icon),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                }

            }
        )
    }) { contentPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(contentPadding)) {
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            if (uiState.error.isNotBlank()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = uiState.error)
                }
            }

            uiState.data?.let { data ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(data) { game ->

                        Card(
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                AsyncImage(
                                    game.imageUrl,
                                    contentDescription = "game_image",
                                    modifier = Modifier.fillMaxWidth().height(350.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier.padding(horizontal = 12.dp)
                                        .background(
                                            color = Color.White,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                ) {
                                    Text(
                                        text = game.name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(
                                            horizontal = 8.dp,
                                            vertical = 4.dp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}