package com.NAQS.comerciais.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

import com.NAQS.comerciais.providers.ContactsContract;

public class AddContactService extends IntentService
{
	public final static String EMAIL = "email";
	public final static String NOME = "nome";
	public final static String MORADA = "morada";
	public final static String CIDADE = "cidade";
	public final static String CP = "cp";
	public final static String TELEFONE = "telefone";
	public final static String TELEMOVEL = "telemovel";
	
	public AddContactService() 
	{
		super("AddContact");
	}

	@Override
	protected void onHandleIntent(Intent intent) 
	{
		ContentValues values = new ContentValues();
		values.put(ContactsContract.EMAIL, intent.getStringExtra(EMAIL));
		values.put(ContactsContract.NOME, intent.getStringExtra(NOME));
		values.put(ContactsContract.MORADA, intent.getStringExtra(MORADA));
		values.put(ContactsContract.CIDADE, intent.getStringExtra(CIDADE));
		values.put(ContactsContract.CP, intent.getStringExtra(CP));
		values.put(ContactsContract.TELEFONE, intent.getStringExtra(TELEFONE));
		values.put(ContactsContract.TELEMOVEL, intent.getStringExtra(TELEMOVEL));
		getContentResolver().insert(ContactsContract.CONTENT_URI, values);
		
		// notify the changes.
		getContentResolver().notifyChange(ContactsContract.CONTENT_URI, null);
		//todo
		
	}
}