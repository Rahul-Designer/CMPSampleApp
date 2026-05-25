package com.company.game.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmpsampleapp.game.ui.generated.resources.Res
import cmpsampleapp.game.ui.generated.resources.ic_favorite_icon
import cmpsampleapp.game.ui.generated.resources.ic_search
import com.company.common.ui.listItem.GameItem
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit,
    onGameDetailsClick: (Int) -> Unit
) {

    val viewModel: GameViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GameScreenContent(
        modifier = modifier,
        uiState = uiState,
        onFavoriteClick = {
            onFavoriteClick.invoke()
        }, onSearchClick = {
            onSearchClick.invoke()
        }, onGameDetailsClick = {
            onGameDetailsClick(it)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScreen.UiState,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit,
    onGameDetailsClick: (Int) -> Unit
) {

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = {
                Text(text = "Gamopedia")
            }, actions = {
                IconButton(onClick = {
                    onSearchClick.invoke()
                }) {
                    Image(
                        painterResource(Res.drawable.ic_search),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                }
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
        Box(modifier = Modifier.fillMaxSize().padding(contentPadding).background(Color.White)) {
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            if (uiState.error.isNotBlank()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = uiState.error)
                    print(uiState.error)
                }
            }

            uiState.data?.let { data ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(data) { game ->
                        GameItem(game, isDeleteShow = false, onGameDetailsClick = {
                            onGameDetailsClick.invoke(it)
                        }, onGameDeleteClick = {}
                        )
                    }
                }

            }
        }
    }
}