package com.svyd.videokilledtheradiostar.feature.browser.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.svyd.videokilledtheradiostar.R.*
import com.svyd.videokilledtheradiostar.common.Destination
import com.svyd.videokilledtheradiostar.common.Destination.Browser
import com.svyd.videokilledtheradiostar.common.UiState
import com.svyd.videokilledtheradiostar.feature.browser.data.BrowserViewModel
import com.svyd.videokilledtheradiostar.feature.browser.data.RadioBrowserViewModelFactory
import com.svyd.videokilledtheradiostar.feature.browser.model.UiDirectory

@Composable
fun VideoKilledTheRadioStarApp() {

    val appName = stringResource(id = string.app_name)
    val title = remember { mutableStateOf(appName) }
    val navController = rememberNavController()
    var showBackButton by remember { mutableStateOf(false) }

    RadioBrowserTopBar(
        showBackButton,
        title,
        navController
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Browser.route
        ) {

            composable(route = Browser.route) { backStackEntry ->

                val url = backStackEntry.arguments?.getString(Destination.URL_ARGUMENT)
                showBackButton = navController.previousBackStackEntry != null

                RadioBrowserScreen(
                    url = url.orEmpty(),
                    padding = padding,
                    title = title
                ) { navController.navigate(Browser.createRoute(it)) }
            }
        }
    }
}

@Composable
fun RadioBrowserTopBar(
    showBackButton: Boolean,
    title: MutableState<String>,
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = if (showBackButton) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else null,
                title = { Text(title.value) },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = content
    )
}

@Composable
fun RadioBrowserScreen(
    url: String,
    padding: PaddingValues,
    title: MutableState<String>,
    onLinkClick: (url: String) -> Unit = {}
) {
    val viewModel: BrowserViewModel = viewModel(factory = RadioBrowserViewModelFactory(url))
    val state by viewModel.directoryState.collectAsState()

    Crossfade(targetState = state) {
        RadioBrowserScreen(it, padding, title, onLinkClick) { viewModel.retry() }
    }
}

@Composable
fun RadioBrowserScreen(
    state: UiState<UiDirectory>,
    padding: PaddingValues = PaddingValues(),
    title: MutableState<String>,
    onLinkClick: (url: String) -> Unit,
    onRetryClick: () -> Unit,
) {
    when (state) {
        UiState.Loading -> Loading()
        is UiState.Error -> ErrorRetry(error = state.error, onRetryClick)
        is UiState.Success -> {
            state.data.title?.let { title.value = it }
            RadioBrowserContent(
                directory = state.data,
                padding = padding,
                onLinkClick
            )
        }
    }
}

@Composable
fun RadioBrowserContent(
    directory: UiDirectory,
    padding: PaddingValues,
    onLinkClick: (url: String) -> Unit
) {
    LazyColumn(
        Modifier.padding(padding),
        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 16.dp)
    ) {
        items(directory.body) { element ->
            Element(element, onLinkClick)
        }
    }
}
