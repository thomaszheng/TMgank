package com.thomas.tmgank.ui.widget;

import android.content.Intent;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;

import com.thomas.tmgank.ui.activity.DailyActivity;

/**
 * Created by thomas on 2015/10/12.
 */
public class URLSpanNoUnderline extends URLSpan {

    public URLSpanNoUnderline(String url) {
        super(url);
    }

    URLSpanNoUnderline(Parcel src) {
        super(src);
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {

            Intent intent = new Intent(widget.getContext(), DailyActivity.class);
            intent.putExtra("url", getURL());
            widget.getContext().startActivity(intent);
           //打开浏览器
            //super.onClick(widget);

    }
}
