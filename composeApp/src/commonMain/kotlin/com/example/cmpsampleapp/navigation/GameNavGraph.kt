package com.example.cmpsampleapp.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.company.game.ui.game.GameScreen
import com.company.game.ui.gameDetails.GameDetailsScreen
import kotlinx.serialization.Serializable

object GameNavGraph : BaseNavGraph {

    @Serializable
    data object Root

    @Serializable
    data object Game

    @Serializable
    data class GameDetails(
        val id: Int
    )

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Root>(startDestination = Game) {
            composable<Game> {
                GameScreen(modifier = Modifier, onFavoriteClick = {
                    navHostController.navigate(FavoriteNavGraph.Favorite)
                }, onSearchClick = {
                    navHostController.navigate(SearchNavGraph.Search)
                }, onGameDetailsClick = { id ->
                    navHostController.navigate( GameDetails(id))
                })
            }
            composable<GameDetails> {
                val id = it.toRoute<GameDetails>()
                GameDetailsScreen(
                    modifier = Modifier,
                    id = id.id.toString(),
                    onNavigateUp = {
                        navHostController.popBackStack()
                    }
                )
            }
        }

    }
}