package com.vicky7230.imagessubredditviewer.di.module


import com.vicky7230.imagessubredditviewer.ui.home.HomeActivity
import com.vicky7230.imagessubredditviewer.ui.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindHomeActivity(): HomeActivity

}