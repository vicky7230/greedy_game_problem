# greedy_game_problem
```
Project Root
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
