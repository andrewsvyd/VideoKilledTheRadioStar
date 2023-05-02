package com.svyd.videokilledtheradiostar.feature.browser.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.svyd.videokilledtheradiostar.feature.browser.data.BrowserViewModel

class RadioBrowserViewModelFactory(private val url: String?) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = RadioBrowserViewModelProvider()
        return BrowserViewModel(
            url,
            provider.provideDirectoryInteractor(),
            provider.provideRootDirectoryInteractor(),
            provider.provideMapper(),
            provider.provideModifier()
        ) as T
    }
}
