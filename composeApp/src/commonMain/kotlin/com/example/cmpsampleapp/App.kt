package com.example.cmpsampleapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cmpsampleapp.navigation.FavoriteNavGraph
import com.example.cmpsampleapp.navigation.GameNavGraph
import com.example.cmpsampleapp.navigation.SearchNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()
        NavHost(
            navController = navHostController,
            startDestination = GameNavGraph.Root
        ) {
            listOf(
                GameNavGraph,
                SearchNavGraph,
                FavoriteNavGraph
            ).forEach {
                it.build(
                    modifier = Modifier.fillMaxSize(),
                    navHostController = navHostController,
                    navGraphBuilder = this
                )
            }
        }
    }
}