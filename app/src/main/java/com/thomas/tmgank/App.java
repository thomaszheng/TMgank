package com.thomas.tmgank;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.thomas.tmgank.http.ApiHttpClient;

/**
 * Created by thomas on 2015/10/11.
 */
public class App extends Application {

    private static Context context;
    private static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        handler=new Handler();

        initHttpClient();
        initImageLoader(context);

    }

    private void initHttpClient() {
        AsyncHttpClient client = new AsyncHttpClient();
        ApiHttpClient.setHttpClient(client);
       PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
       client.setCookieStore(myCookieStore);


    }

    public static  Context getContext(){
        return context;
    }
    public static Handler getHandler(){
        return handler;
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();//不会在内存中缓存多个大小的图片
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());//为了保证图片名称唯一
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        //内存缓存大小默认是：app可用内存的1/8
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
//		ImageLoader.getInstance().init( ImageLoaderConfiguration.createDefault(this));
    }
}
