package com.thomas.tmgank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thomas.tmgank.ui.fragment.GankFragment;
import com.thomas.tmgank.ui.fragment.ImageFragment;

/**
 * Created by thomas on 2015/10/19.
 */
public class ClassiftAdapter extends FragmentPagerAdapter {
    private String[] classify;
    public ClassiftAdapter(FragmentManager fm,String[] classify) {
        super(fm);
        this.classify=classify;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return GankFragment.newInstance("data/Android/10/1");
            case 1:
                return GankFragment.newInstance("data/iOS/10/1");
            case 2:
                return GankFragment.newInstance("data/瞎推荐/10/1");
            case 3:
                return GankFragment.newInstance("data/拓展资源/10/1");
            case 4:
                return ImageFragment.newInstance("data/福利/10/1");
            case 5:
                return GankFragment.newInstance("data/休息视频/10/1");
        }
        return null;
    }

    @Override
    public int getCount() {
        return classify.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return classify[position];
    }
}
