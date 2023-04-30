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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.svyd.videokilledtheradiostar.R.*
import com.svyd.videokilledtheradiostar.common.Destination
import com.svyd.videokilledtheradiostar.common.Destination.Browser
import com.svyd.videokilledtheradiostar.common.UiState
import com.svyd.videokilledtheradiostar.common.UiState.Error
import com.svyd.videokilledtheradiostar.common.UiState.Loading
import com.svyd.videokilledtheradiostar.common.UiState.Success
import com.svyd.videokilledtheradiostar.common.ui.theme.VideoKilledTheRadioStarTheme
import com.svyd.videokilledtheradiostar.feature.browser.data.BrowserViewModel
import com.svyd.videokilledtheradiostar.feature.browser.data.RadioBrowserViewModelFactory
import com.svyd.videokilledtheradiostar.feature.browser.data.sample.SampleDirectoryData
import com.svyd.videokilledtheradiostar.feature.browser.model.UiDirectory

@Composable
fun VideoKilledTheRadioStarApp() {

    val appName = stringResource(id = string.app_name)
    var title by remember { mutableStateOf(appName) }
    val navController = rememberNavController()
    var showBackButton by remember { mutableStateOf(false) }

    LaunchedEffect(navController) {
        navController
            .currentBackStackEntryFlow
            .collect { backStackEntry ->
                showBackButton = navController.previousBackStackEntry != null
                title = backStackEntry.arguments?.getString(Destination.TITLE) ?: appName
            }
    }

    RadioBrowserTopBar(
        showBackButton = showBackButton,
        title = title,
        onBackButtonClick = { navController.navigateUp() }) { padding ->

        NavHost(
            navController = navController,
            startDestination = Browser.route
        ) {

            composable(route = Browser.route) { backStackEntry ->
                RadioBrowserScreen(
                    backStackEntry.arguments?.getString(Destination.URL).orEmpty(),
                    padding
                ) { destinationUrl: String, destinationTitle: String ->
                    navController.navigate(
                        Browser.createRoute(
                            destinationUrl,
                            destinationTitle
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun RadioBrowserTopBar(
    showBackButton: Boolean,
    title: String,
    onBackButtonClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = if (showBackButton) {
                    {
                        IconButton(onClick = onBackButtonClick) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else null,
                title = { Text(title) },
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
    onLinkClick: (url: String, title: String) -> Unit
) {

    val viewModel: BrowserViewModel =
        viewModel(factory = RadioBrowserViewModelFactory(url))

    val state by viewModel.directoryState.collectAsState()

    Crossfade(targetState = state) { uiState ->
        RadioBrowserScreen(
            state = uiState,
            padding,
            onLinkClick = onLinkClick
        ) { viewModel.retry() }
    }
}

@Composable
fun RadioBrowserScreen(
    state: UiState<UiDirectory>,
    padding: PaddingValues = PaddingValues(),
    onLinkClick: (url: String, title: String) -> Unit = { _: String, _: String -> },
    onRetryClick: () -> Unit = {}
) {
    when (state) {
        Loading -> Loading()
        is Error -> ErrorRetry(error = state.error, onRetryClick)
        is Success -> {
            RadioBrowser(
                directory = state.data,
                padding = padding,
                onLinkClick
            )
        }
    }
}

@Composable
fun RadioBrowser(
    directory: UiDirectory,
    padding: PaddingValues,
    onLinkClick: (url: String, title: String) -> Unit
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RadioBrowserPreview() {
    VideoKilledTheRadioStarTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            RadioBrowserScreen(Success(SampleDirectoryData().directory()))
        }
    }
}
