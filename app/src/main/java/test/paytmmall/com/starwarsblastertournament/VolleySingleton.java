package test.paytmmall.com.starwarsblastertournament;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.*;
import com.android.volley.toolbox.*;

public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleySingleton(Context pContext) {
        mRequestQueue = Volley.newRequestQueue(pContext);
        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    public static VolleySingleton getInstance(Context pContext) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(pContext);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return this.mImageLoader;
    }

}


