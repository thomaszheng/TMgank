package com.thomas.tmgank.util;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.thomas.tmgank.model.Gank;
import com.thomas.tmgank.ui.widget.URLSpanNoUnderline;

import java.util.List;

/**
 * Created by thomas on 2015/10/12.
 */
public class TextKit {
    public static SpannableStringBuilder generate(List<Gank> ganHuos, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        int start;
        for (Gank gh : ganHuos) {
            start = builder.length();
            builder.append(" • ");
            builder.setSpan(new StyleSpan(Typeface.BOLD), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = builder.length();
            builder.append(gh.desc);
            builder.setSpan(new URLSpanNoUnderline(gh.url), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(color), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append("（");
            builder.append(gh.who);
            builder.append("）\n");
        }
        return builder;
    }

    public static CharSequence getInfo() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        int start = 0;
        builder.append("TMgank .Android 客户端\n");
        builder.setSpan(new StyleSpan(Typeface.BOLD), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("项目地址：");
        start = builder.length();
        builder.append("github");
        builder.setSpan(new URLSpanNoUnderline("https://github.com/thomaszheng"), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("\n");
        start = builder.length();
        builder.append("by thomas");
        builder.setSpan(new StyleSpan(Typeface.ITALIC), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

}
