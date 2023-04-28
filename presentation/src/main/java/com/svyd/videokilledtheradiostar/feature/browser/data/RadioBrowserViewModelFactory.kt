package com.svyd.videokilledtheradiostar.feature.browser.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RadioBrowserViewModelFactory(private val url: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = PlainViewModelProvider()
        return BrowserViewModel(url, provider.provideInteractor(), provider.provideMapper()) as T
    }
}
