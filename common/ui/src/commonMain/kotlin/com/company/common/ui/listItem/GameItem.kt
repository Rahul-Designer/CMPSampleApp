package com.company.common.ui.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cmpsampleapp.common.ui.generated.resources.Res
import cmpsampleapp.common.ui.generated.resources.ic_delete
import coil3.compose.AsyncImage
import com.company.common.domain.model.Game
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameItem(
    game: Game,
    isDeleteShow: Boolean,
    onGameDetailsClick: (Int) -> Unit,
    onGameDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize().clickable {
            onGameDetailsClick.invoke(game.id)
        }.padding(12.dp),
        shape = RoundedCornerShape(12.dp)
    )
    {
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
                modifier = Modifier.padding(12.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    ).fillMaxWidth()
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
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
            if (isDeleteShow) {
                IconButton(
                    onClick = {
                        onGameDeleteClick(game.id)
                    },
                    modifier = Modifier.padding(12.dp).background(Color.White, CircleShape)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_delete),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
    }
}