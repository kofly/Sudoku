package com.kof.sudoku;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Kof on 2016/4/24.
 */
public class Prefs extends PreferenceActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
