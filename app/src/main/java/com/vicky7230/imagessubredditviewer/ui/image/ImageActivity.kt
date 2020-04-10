package com.vicky7230.imagessubredditviewer.ui.image

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vicky7230.imageloader.ImageLoader
import com.vicky7230.imagessubredditviewer.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    companion object {

        val URL = "url"

        fun getStartIntent(context: Context, url: String): Intent {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(URL, url)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null && intent.getStringExtra(URL) != null) {
            val url: String = intent.getStringExtra(URL)!!
            ImageLoader.displayImage(url, image_big)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
