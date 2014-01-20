package com.NAQS.comerciais;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Application preferences activity for show and 
 * handle the user preferences.
 * 
 * @author ricardosousa
 * */
public class Preferencias extends PreferenceActivity
{
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.user_prefs);
	}
}