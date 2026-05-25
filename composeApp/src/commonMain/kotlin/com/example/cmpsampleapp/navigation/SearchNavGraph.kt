package com.example.cmpsampleapp.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.company.search.ui.SearchScreen
import kotlinx.serialization.Serializable

object SearchNavGraph : BaseNavGraph {

    @Serializable
    data object Root

    @Serializable
    data object Search

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Root>(
            startDestination = Search
        ) {
            composable<Search> {
                SearchScreen(
                    modifier = Modifier, onClickDetail = {
                        navHostController.navigate(GameNavGraph.GameDetails(it))
                    }
                )
            }
        }
    }
}