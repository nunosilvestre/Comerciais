package com.NAQS.comerciais.services;

import android.app.IntentService;
import android.content.Intent;

import com.NAQS.comerciais.providers.ContactsContract;

public class RemoveContactService extends IntentService
{
	public final static String POSITION = "pos";
	
	public RemoveContactService() 
	{
		super("RemoveContact");
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		getContentResolver().delete(ContactsContract.CONTENT_URI, ContactsContract._ID + "=?", new String[] {intent.getIntExtra(POSITION, 0) + ""});
		// notify the changes.
		getContentResolver().notifyChange(ContactsContract.CONTENT_URI, null);
	}
}
