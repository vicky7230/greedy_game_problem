# ImagesSubRedditViewer

Project Structure
-----------------
```
+-- imageloader (ImageLoading library)
+-- app (App to display the list of images and handle the networking part )
```

Include this line in your settings.gradle
----------------------------------------
```groovy
include ':imageloader'
```

Add this in your app module's build.gradle file :
--------------------------------------------------
```groovy
dependencies {
        implementation project(":imageloader")
    }
```

How does ImageLoading library works?
------------------------------------
1. ImageLoader receives a request which contains an ImageView and Image URL
2. Check whether Image exist in cache(first memory cache and then disk cache if using ```DoubleCache```) or not
3. If Image exists in the cache, then load it onto ImageView.
4. If Image doesn't exist in the cache, then Download the Image, write it to the cache and execute Step 3

How do I use ImageLoader?
-------------------------
```kotlin
//Initialize and Set Cache Type (DiskCache, MemoryCache, DoubleCache) in
//Application class
ImageLoader.setCache(DoubleCache(applicationContext))

//Asynchronously load the image onto the ImageView.
val future = ImageLoader.displayImage(url, image_view)

//ImageLoader.displayImage() returns a future,
//which can be used to cancel the specific operation in case the loading is not needed anymore.
future?.cancel(true) 

//Cancel all existing pending tasks
ImageLoader.stopAllImageLoading()
```
