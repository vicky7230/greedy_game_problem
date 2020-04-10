package com.vicky7230.imagessubredditviewer.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicky7230.imagessubredditviewer.di.ViewModelFactory
import com.vicky7230.imagessubredditviewer.di.ViewModelKey
import com.vicky7230.imagessubredditviewer.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun postHomeViewModel(mainViewModel: HomeViewModel): ViewModel

/*
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun postNewsViewModel(newsViewModel: NewsViewModel): ViewModel
*/

}