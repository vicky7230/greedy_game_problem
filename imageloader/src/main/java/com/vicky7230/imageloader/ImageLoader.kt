package com.vicky7230.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

object ImageLoader {
    private lateinit var cache: ImageCache
    private var executorService: ExecutorService =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val uiHandler: Handler = Handler(Looper.getMainLooper())
    private val futures = mutableListOf<Future<*>>()

    fun setCache(cache: ImageCache) {
        this.cache = cache
    }

    fun displayImage(url: String, imageView: ImageView): Future<*>? {
        val cached = cache.get(url)
        if (cached != null) {
            updateImageView(imageView, cached)
            return null
        }
        imageView.tag = url

        val future = executorService.submit {
            val bitmap: Bitmap? = downloadImage(url)
            if (bitmap != null) {
                if (imageView.tag == url) {
                    updateImageView(imageView, bitmap)
                }
                cache.put(url, bitmap)
            }
        }

        futures.add(future)
        return future
    }

    fun clearCache() {
        this.cache.clear()
    }

    fun stopAllImageLoading() {
        for (future in futures)
            future.cancel(true)
    }

    private fun updateImageView(imageView: ImageView, bitmap: Bitmap) {
        uiHandler.post {
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun downloadImage(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url1 = URL(url)
            val conn: HttpURLConnection = url1.openConnection() as HttpURLConnection
            bitmap = BitmapFactory.decodeStream(conn.inputStream)
            conn.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }
}