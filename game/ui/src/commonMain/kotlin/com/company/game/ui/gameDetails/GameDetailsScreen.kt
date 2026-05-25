package com.company.game.ui.gameDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmpsampleapp.game.ui.generated.resources.Res
import cmpsampleapp.game.ui.generated.resources.ic_arrow_back
import cmpsampleapp.game.ui.generated.resources.ic_delete
import cmpsampleapp.game.ui.generated.resources.ic_favorite_icon
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier, id: String, onNavigateUp: () -> Unit
) {

    val viewModel: GameDetailsViewModel = koinViewModel()

    LaunchedEffect(id) {
        viewModel.getGameDetails(id.toInt())
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GameDetailScreenContent(
        modifier = modifier,
        uiState = uiState,
        onNavigateUp = {
            onNavigateUp.invoke()
        }, onSaveClick = { id, name, image ->
            viewModel.save(id, name, image)
        }, onDeleteClick = { id ->
            viewModel.delete(id)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreenContent(
    modifier: Modifier,
    uiState: GameDetailsScreen.UiState,
    onNavigateUp: () -> Unit,
    onSaveClick: (id: Int, name: String, image: String) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Game Details")
        }, navigationIcon = {
            IconButton(onClick = {
                onNavigateUp.invoke()
            }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
        }, actions = {
            IconButton(onClick = {
                uiState.date?.let { data ->
                    onSaveClick.invoke(data.id, data.name, data.backgroundImage)
                }
            }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_favorite_icon),
                    contentDescription = null
                )
            }
            IconButton(onClick = {
                uiState.date?.let { data ->
                    onDeleteClick.invoke(data.id)
                }
            }) {
                Icon(painter = painterResource(Res.drawable.ic_delete), contentDescription = null)
            }
        })
    }, modifier = modifier.fillMaxSize()) { contentPadding ->
        Box(modifier = Modifier.fillMaxSize().background(Color.White).padding(contentPadding)) {

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

            uiState.date?.let { data ->
                LazyColumn {
                    item {
                        AsyncImage(
                            model = data.backgroundImage,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(350.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    item {
                        Text(
                            text = data.name,
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                    item {
                        Text(
                            text = data.description,
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    item {
                        Column {
                            Text(
                                text = "Platforms",
                                modifier = Modifier.fillMaxSize().padding(12.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(data.platforms) { item ->
                                    Card(
                                        modifier = Modifier.wrapContentSize().padding(12.dp),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)
                                    ) {
                                        Column(
                                            modifier = Modifier.width(150.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            AsyncImage(
                                                model = item.image,
                                                contentDescription = null,
                                                modifier = Modifier.size(150.dp).background(
                                                    Color.Transparent,
                                                    shape = CircleShape
                                                ).clip(CircleShape),
                                                contentScale = ContentScale.Crop
                                            )
                                            Text(
                                                text = item.name,
                                                modifier = Modifier.fillMaxWidth()
                                                    .padding(vertical = 8.dp),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.bodyMedium,
                                            )

                                        }
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Stores",
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    items(data.stores) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            AsyncImage(
                                model = item.image,
                                contentDescription = null,
                                modifier = Modifier.size(150.dp).background(
                                    Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                ).clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = item.name,
                                    modifier = Modifier,
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                                Text(
                                    text = item.domain,
                                    modifier = Modifier,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Text(
                                    text = "Game Count : ${item.gameCount}",
                                    modifier = Modifier,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }


                        }

                    }

                    item {
                        Text(
                            text = "Tags",
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        FlowRow(
                            modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            data.tags.forEach {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(
                                        4.dp
                                    ), verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.background(
                                        Color.White,
                                        shape = RoundedCornerShape(200.dp)
                                    ).clip(RoundedCornerShape(200.dp)).border(
                                        1.dp, Color.Black,
                                        shape = RoundedCornerShape(200.dp)
                                    ).padding(8.dp)
                                ) {
                                    AsyncImage(
                                        model = it.image,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                            .background(Color.Transparent, shape = CircleShape)
                                            .clip(CircleShape), contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = it.name,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }

                        }
                    }

                    item {
                        Text(
                            text = "Developers",
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(data.developers) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            AsyncImage(
                                model = item.image,
                                contentDescription = null,
                                modifier = Modifier.size(150.dp).background(
                                    Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                ).clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = item.name,
                                    modifier = Modifier,
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                                Text(
                                    text = "Game Count : ${item.gameCount}",
                                    modifier = Modifier,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }


                        }

                    }
                }

            }
        }

    }
}