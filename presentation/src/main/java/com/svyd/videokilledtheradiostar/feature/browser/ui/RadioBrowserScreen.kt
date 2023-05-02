package com.svyd.videokilledtheradiostar.feature.browser.ui

import android.content.Intent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.svyd.videokilledtheradiostar.R.*
import com.svyd.videokilledtheradiostar.common.ContentState
import com.svyd.videokilledtheradiostar.common.Destination
import com.svyd.videokilledtheradiostar.common.Destination.Browser
import com.svyd.videokilledtheradiostar.common.Event
import com.svyd.videokilledtheradiostar.common.UiState
import com.svyd.videokilledtheradiostar.common.UiState.Error
import com.svyd.videokilledtheradiostar.common.UiState.Success
import com.svyd.videokilledtheradiostar.common.ui.theme.VideoKilledTheRadioStarTheme
import com.svyd.videokilledtheradiostar.feature.browser.data.BrowserViewModel
import com.svyd.videokilledtheradiostar.feature.browser.data.di.RadioBrowserViewModelFactory
import com.svyd.videokilledtheradiostar.feature.browser.data.sample.SampleDirectoryData
import com.svyd.videokilledtheradiostar.feature.browser.model.UiElement
import com.svyd.videokilledtheradiostar.feature.browser.player.PlayerService
import com.svyd.videokilledtheradiostar.feature.browser.player.ui.PlayerReceiver
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoKilledTheRadioStarApp() {

    val appName = stringResource(id = string.app_name)
    var title by remember { mutableStateOf(appName) }
    val navController = rememberNavController()
    var showBackButton by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val onAudioClick: (url: String) -> Unit = {
        val intent = Intent(context, PlayerService::class.java)
        intent.putExtra(PlayerService.KEY_AUDIO_URL, it)
        context.startService(intent)
    }

    val onContentLoaded: () -> Unit = {
        val intent = Intent(context, PlayerService::class.java)
        context.startService(intent)
    }

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
                    backStackEntry.arguments?.getString(Destination.URL),
                    padding,
                    onContentLoaded,
                    onAudioClick,
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
    url: String?,
    padding: PaddingValues,
    onContentLoaded: () -> Unit = {},
    onAudioClick: (url: String) -> Unit,
    onLinkClick: (url: String, title: String) -> Unit
) {
    val viewModel: BrowserViewModel =
        viewModel(factory = RadioBrowserViewModelFactory(url))

    PlayerReceiver { viewModel.playerStateChanged(it) }

    val state by viewModel.directoryState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                Event.ContentLoaded -> onContentLoaded()
            }
        }
    }

    RadioBrowserScreen(
        state = state,
        padding,
        onAudioClick,
        onLinkClick = onLinkClick
    ) { viewModel.retry() }
}

@Composable
fun RadioBrowserScreen(
    state: UiState<List<UiElement>>,
    padding: PaddingValues = PaddingValues(),
    onAudioClick: (url: String) -> Unit = {},
    onLinkClick: (url: String, title: String) -> Unit = { _: String, _: String -> },
    onRetryClick: () -> Unit = {}
) {
    Crossfade(targetState = state.contentState) {
        when (it) {
            ContentState.Loading -> Loading()
            is ContentState.Error -> ErrorRetry(error = (state as Error).error, onRetryClick)
            is ContentState.Success -> {
                RadioBrowser(
                    elements = (state as Success).data,
                    padding = padding,
                    onAudioClick,
                    onLinkClick
                )
            }
        }
    }
}

@Composable
fun RadioBrowser(
    elements: List<UiElement>,
    padding: PaddingValues,
    onAudioClick: (url: String) -> Unit,
    onLinkClick: (url: String, title: String) -> Unit
) {
    LazyColumn(
        Modifier.padding(padding),
        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 16.dp)
    ) {
        items(elements) { element ->
            Element(element, onAudioClick, onLinkClick)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RadioBrowserPreview() {
    VideoKilledTheRadioStarTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            RadioBrowserScreen(Success(SampleDirectoryData().directory().body))
        }
    }
}
