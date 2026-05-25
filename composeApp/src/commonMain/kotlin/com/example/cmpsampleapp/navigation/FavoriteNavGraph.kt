package com.example.cmpsampleapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.company.favorite.ui.FavoriteScreen
import kotlinx.serialization.Serializable

object FavoriteNavGraph : BaseNavGraph {

    @Serializable
    data object Root

    @Serializable
    data object Favorite

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Root>(
            startDestination = Favorite
        ) {
            composable<Favorite> {
                FavoriteScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigateUp = {
                        navHostController.popBackStack()
                    }, onGameDetailsClick = {
                        navHostController.navigate(GameNavGraph.GameDetails(id = it))
                    }
                )
            }
        }
    }
}