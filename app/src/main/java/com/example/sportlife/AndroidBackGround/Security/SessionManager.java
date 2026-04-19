package com.example.sportlife.AndroidBackGround.Security;

import android.content.SharedPreferences;
import android.preference.PreferenceDataStore;

import java.util.prefs.Preferences;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionManager {
    private final DataStore<Preferences> data;
    private final SharedPreferences preferences;
}
