package com.vicky7230.imagessubredditviewer.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicky7230.imageloader.ImageLoader
import com.vicky7230.imagessubredditviewer.R
import com.vicky7230.imagessubredditviewer.data.network.ImageUrl
import com.vicky7230.imagessubredditviewer.data.network.Resource
import com.vicky7230.imagessubredditviewer.ui.base.BaseActivity
import com.vicky7230.imagessubredditviewer.ui.image.ImageActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity(), HasAndroidInjector, ImagesAdapter.Callback {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        imagesAdapter.setCallback(this)

        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        init()
    }

    private fun init() {

        setSupportActionBar(toolbar)

        image_list.layoutManager = LinearLayoutManager(this)
        image_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        image_list.adapter = imagesAdapter

        homeViewModel.resource.observe(this, Observer {
            when (it) {
                is Resource.Loading -> showLoading()
                is Resource.Error -> {
                    hideLoading()
                    showError(it.exception.localizedMessage)
                }
                is Resource.Success -> {
                    Timber.e(it.data.toString())
                    imagesAdapter.updateItems(it.data)
                    hideLoading()
                }
            }
        })

        homeViewModel.getSources()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onItemClick(imageUrl: ImageUrl) {
        startActivity(ImageActivity.getStartIntent(this, imageUrl.url))
    }

    override fun onDestroy() {
        ImageLoader.stopAllImageLoading()
        super.onDestroy()
    }
}
