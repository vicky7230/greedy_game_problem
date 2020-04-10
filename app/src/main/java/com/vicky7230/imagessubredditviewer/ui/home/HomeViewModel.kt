package com.vicky7230.imagessubredditviewer.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vicky7230.imagessubredditviewer.data.DataManager
import com.vicky7230.imagessubredditviewer.data.network.ImageUrl
import com.vicky7230.imagessubredditviewer.data.network.Resource
import com.vicky7230.imagessubredditviewer.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val dataManager: DataManager
) : BaseViewModel() {

    val resource = MutableLiveData<Resource<ArrayList<ImageUrl>>>()

    fun getSources() {
        viewModelScope.launch {
            resource.value = Resource.Loading

            val response = safeApiCall { dataManager.getImages() }

            when (response) {
                is Resource.Success -> {
                    try {
                        val jsonObject = response.data.asJsonObject
                        val childrenJsonArray =
                            jsonObject["data"].asJsonObject["children"].asJsonArray
                        val images = arrayListOf<ImageUrl>()
                        childrenJsonArray.forEach {
                            images.add(
                                ImageUrl(
                                    it.asJsonObject["data"].asJsonObject["thumbnail"].asString,
                                    it.asJsonObject["data"].asJsonObject["url"].asString
                                )
                            )
                        }
                        resource.value = Resource.Success(images)
                    } catch (e: Exception) {
                        resource.value = Resource.Error(IOException("Something went wrong."))
                    }
                }
                is Resource.Error -> {
                    resource.value = response
                }
            }
        }
    }

}