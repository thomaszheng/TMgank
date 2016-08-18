package com.thomas.tmgank.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.client.params.ClientPNames;

import java.util.Locale;

/**
 * Created by thomas on 2015/10/21.
 */
public class ApiHttpClient {

    public final static String HOST = "http://gank.avosapps.com";
    private static String API_URL = "http://gank.avosapps.com/api/%s";
    public static AsyncHttpClient client;


    public static void setHttpClient(AsyncHttpClient c) {
        client = c;
        client.addHeader("Accept-Language", Locale.getDefault().toString());
        client.getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

    }

    public static void get(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        System.out.println(String.format(API_URL, partUrl));
        client.get(String.format(API_URL, partUrl), params, handler);
    }


}
