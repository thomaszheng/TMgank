package com.thomas.tmgank.http;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by thomas on 2015/10/21.
 */
public class DataManager {

    // gank 列表信息
    public static void getGankList(String daystring, AsyncHttpResponseHandler handler) {
        ApiHttpClient.get(daystring, null, handler);
    }
}
