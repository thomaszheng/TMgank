package com.thomas.tmgank.model;

import java.util.Date;

/**
 * Created by thomas on 2015/10/13.
 */
public class Collection {

    public String url;
    public String title;
    public Date time;

    public Collection(String url, String title, Date time) {
        this.url = url;
        this.title = title;
        this.time = time;
    }
}
