package com.ice.quiz2;


import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class Preferences extends PreferenceActivity {
    public static final String SERVER = "server";
    public static final String EDITSERVER = "editservername";

    public static final String KEY_LIST_PREFERENCE = "server";
    public static ListPreference  mListPreference;
    public static EditTextPreference  mEditTextPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* addPreferencesFromResource(R.xml.flightoptions);

        PreferenceScreen prefSet = getPreferenceScreen();
        mListPreference = (ListPreference) prefSet.findPreference(SERVER);
        mEditTextPreference = (EditTextPreference) prefSet.findPreference(EDITSERVER);
        mEditTextPreference.setText(""); */
    }
    @Override
    protected void onResume()  {
        super.onResume();
        PreferenceScreen prefSet = getPreferenceScreen();

        mListPreference = (ListPreference) prefSet.findPreference(SERVER);
        mEditTextPreference = (EditTextPreference) prefSet.findPreference(EDITSERVER);
        mEditTextPreference.setText("");
    }

    public boolean onPreferenceChange(Preference preference, Object newValue){
        if (preference == mListPreference) {
            mListPreference.setSummary(newValue.toString());
            return true;
        }
        if (preference == mEditTextPreference) {
            mListPreference.setSummary(newValue.toString());
            return true;
        }
        mListPreference.setSummary("server name");

        return false;
    }

}