package com.vicky7230.imagessubredditviewer.ui.home

import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun provideImageAdapter(): ImagesAdapter {
        return ImagesAdapter(arrayListOf())
    }
}