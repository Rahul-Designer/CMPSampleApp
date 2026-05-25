package com.company.favorite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmpsampleapp.favorite.ui.generated.resources.Res
import cmpsampleapp.favorite.ui.generated.resources.ic_arrow_back
import com.company.common.domain.model.Game
import com.company.common.ui.listItem.GameItem
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    onGameDetailsClick: (Int) -> Unit,
) {

    val viewModel: FavoriteViewModel = koinViewModel()

    val games by viewModel.games.collectAsStateWithLifecycle()
    FavoriteScreenContent(
        modifier,
        games = games,
        onNavigateUp = {
            onNavigateUp.invoke()
        }, onGameDetailsClick = {
            onGameDetailsClick.invoke(it)
        }, onGameDeleteClick = {
            viewModel.delete(it)
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreenContent(
    modifier: Modifier,
    games: List<Game>,
    onNavigateUp: () -> Unit,
    onGameDetailsClick: (Int) -> Unit,
    onGameDeleteClick: (Int) -> Unit
) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Favorites")
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        onNavigateUp.invoke()
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = null, modifier
                    )
                }

            }
        )
    }, modifier = modifier.fillMaxSize()) { contentPadding ->
        Box(modifier = Modifier.fillMaxSize().background(Color.White).padding(contentPadding)) {

            if (games.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Nothing Found")
                }
            } else {
                LazyColumn {
                    items(games) { game ->
                        GameItem(game, isDeleteShow = true, onGameDetailsClick = {
                            onGameDetailsClick.invoke(it)
                        }, onGameDeleteClick = {
                            onGameDeleteClick(it)
                        })
                    }
                }
            }
        }

    }
}