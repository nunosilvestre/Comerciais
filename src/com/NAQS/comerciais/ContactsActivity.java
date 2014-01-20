package com.NAQS.comerciais;

import org.apache.http.protocol.HTTP;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * List Activity with e-mail contacts for select one to send the e-mail.
 * */
public class ContactsActivity extends ListActivity
{
	private String _emailText;
	private CursorAdapter _adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.d(Inicio.TAG, "ContactsActivity: onCreate.");
		super.onCreate(savedInstanceState);
		/*
		 *  In this example, none layout is defined but you can define one ;-).
		 *  So, is not necessary to inflate the view.
		 */
				
		// Get the e-mail text from intent.
		//_emailText = getIntent().getStringExtra(Inicio.EMAIL_TEXT);
		_emailText = "teste";
		_adapter = new ContactsAdapter();
		setListAdapter(_adapter);
		
		// Use LoaderManager.
		
		
		
		getLoaderManager().initLoader(0, null, new LoaderCallbacks<Cursor>() {
			@Override
			public Loader<Cursor> onCreateLoader(int id, Bundle args)
			{
				return new CursorLoader(ContactsActivity.this, com.NAQS.comerciais.providers.ContactsContract.CONTENT_URI, null, null, null, null);
			}

			@Override
			public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
			{
				// Register this cursor to "watch" the changes occurred in the specified URI.
				cursor.setNotificationUri(getContentResolver(), com.NAQS.comerciais.providers.ContactsContract.CONTENT_URI);
				_adapter.swapCursor(cursor);
			}

			@Override
			public void onLoaderReset(Loader<Cursor> loader) 
			{
				_adapter.swapCursor(null);
			}
		});
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		//super.onListItemClick(l, v, position, id);
		
		// Get the cursor.
		//Cursor cursor = (Cursor)l.getItemAtPosition(position);
		//TODO   ecran de detalhe de cliente
		// Verify the signature from preferences.
//		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//		String body = _emailText;
//		if (prefs.getBoolean("signatureOn", false))
//		{
//			// Add the signature to body.
//			body += "\n" + prefs.getString("signature", "");
//		}
//		
//		
		
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
			Intent intent = new Intent(ContactsActivity.this, ListaCliente.class);
			intent.setType(HTTP.PLAIN_TEXT_TYPE);
			
			intent.putExtra(com.NAQS.comerciais.Inicio.NOME, cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.NOME)));
			intent.putExtra(com.NAQS.comerciais.Inicio.MORADA, cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.MORADA))); // recipients.
			intent.putExtra(com.NAQS.comerciais.Inicio.CIDADE, cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.CIDADE))); // recipients.
			intent.putExtra(com.NAQS.comerciais.Inicio.CP, cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.CP))); // recipients.
			intent.putExtra(com.NAQS.comerciais.Inicio.TELEMOVEL, cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.TELEMOVEL))); // recipients.
			intent.putExtra(com.NAQS.comerciais.Inicio.TELEFONE, cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.TELEFONE))); // recipients.
			intent.putExtra(com.NAQS.comerciais.Inicio.EMAIL, cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.EMAIL))); // recipients.

			// Start the new activity using the explicit intent created previously.
			startActivity(intent);
		
	}
	
	
	/**
	 * Custom Array Adapter for contacts.
	 * 
	 
	 * */
	private class ContactsAdapter extends CursorAdapter
	{
		//private final Context _ctx;
		
		public ContactsAdapter() 
		{
			super(ContactsActivity.this, null, 0);
			//_ctx = ContactsActivity.this;
		}
		
		@Override
		public boolean areAllItemsEnabled() 
		{
			return true;
		}
		
		@Override
		public boolean isEnabled(int position) 
		{
			return true;
		}

		@Override
		public void bindView(View v, Context ctx, final Cursor cursor) 
		{
			Holder h = (Holder)v.getTag(); 
			
			h.tv.setText(cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.NOME)));
			// Set the delete action in the btn.
			h.bt1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					// Start activity to remove.
					//TODO telefonar
					//int id = cursor.getInt(cursor.getColumnIndex(ContactsContract._ID));
					//Intent intent = new Intent(ContactsAdapter.this._ctx, RemoveContactService.class);
					//intent.putExtra(RemoveContactService.POSITION, id);
					//startService(intent);
				}
			});
				h.bt2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) 
					{
						// Start activity to remove.
						//TODO email
						Intent intent = new Intent(Intent.ACTION_SEND);
						intent.setType(HTTP.PLAIN_TEXT_TYPE);
						intent.putExtra(Intent.EXTRA_EMAIL, new String[] {cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.EMAIL))}); // recipients.
						intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
						//intent.putExtra(Intent.EXTRA_TEXT, body);
						startActivity(intent);
					}
			});
				h.bt3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) 
					{
						// Start activity to remove.
						//TODO email
						Intent intent = new Intent(ContactsActivity.this, GoogleMaps.class);
						intent.putExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerIni, "Praca Saldanha,Lisboa");
						intent.putExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerFim, cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.MORADA))+ "," + cursor.getString(cursor.getColumnIndex(com.NAQS.comerciais.providers.ContactsContract.CIDADE)));
						// Start the new activity using the explicit intent created previously.
						startActivity(intent);
					}
			});
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2)
		{
			Holder h = new Holder(); 
			View v = getLayoutInflater().inflate(R.layout.contact_item, null); 
			h.tv = (TextView)v.findViewById(R.id.my_contact_item_id);
			h.bt1 = (Button)v.findViewById(R.id.my_contact_item_telefonar_id);
			h.bt2 = (Button)v.findViewById(R.id.my_contact_item_eMail_id);
			h.bt3 = (Button)v.findViewById(R.id.my_contact_item_MAP_id);
			v.setTag(h);
			return v;
		}
		
		/**
		 * Helper class for hold the views, so we gain 
		 * optimization because only inflates once the item view.
		 * */
		private class Holder
		{
			TextView tv;
			Button bt1;
			Button bt2;
			Button bt3;
		}
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		Boolean superRes = super.onCreateOptionsMenu(menu);
		// Inflates the menu.
		getMenuInflater().inflate(R.menu.contactos, menu);
		return true && superRes;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			// For add one more contact.
			case R.id.adiciona_contacto_id:
				
				// Creation of one AlertDialog for the user writes the new e-mail to add to list.
				startActivity(new Intent(this, InsereCliente.class));
				
				return true;
				
			// For open the preferences.
			case R.id.manter_contacto_id:
				startActivity(new Intent(this, ManutencaoActivity.class));
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
