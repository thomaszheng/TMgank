package com.thomas.tmgank.ui.fragment;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.thomas.tmgank.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        ListPreference themes = (ListPreference) findPreference(getString(R.string.key_theme));
        themes.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Toast.makeText(getActivity(), "设置主题", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        switch (preference.getKey()) {
            case "key_clear":
                Toast.makeText(getActivity(), "清楚缓存", Toast.LENGTH_SHORT).show();
              break;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().finish();
                return true;
        }
        return false;
    }

}
