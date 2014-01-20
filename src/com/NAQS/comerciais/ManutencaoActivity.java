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
import com.NAQS.comerciais.providers.*;
import com.NAQS.comerciais.services.RemoveContactService;
import com.NAQS.comerciais.services.UpdateContactService;

/**
 * List Activity with e-mail contacts for select one to send the e-mail.
 * */
public class ManutencaoActivity extends ListActivity
{
	private String _emailText;
	private CursorAdapter _adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.d(Inicio.TAG, "ManutencaoActivity: onCreate.");
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
				return new CursorLoader(ManutencaoActivity.this, ContactsContract.CONTENT_URI, null, null, null, null);
			}

			@Override
			public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
			{
				// Register this cursor to "watch" the changes occurred in the specified URI.
				cursor.setNotificationUri(getContentResolver(), ContactsContract.CONTENT_URI);
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
		
		
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
			Intent intent = new Intent(ManutencaoActivity.this, ListaCliente.class);
			intent.setType(HTTP.PLAIN_TEXT_TYPE);
			
			intent.putExtra(Inicio.NOME, cursor.getString(cursor.getColumnIndex(ContactsContract.NOME)));
			intent.putExtra(Inicio.MORADA, cursor.getString(cursor.getColumnIndex(ContactsContract.MORADA))); // recipients.
			intent.putExtra(Inicio.CIDADE, cursor.getString(cursor.getColumnIndex(ContactsContract.CIDADE))); // recipients.
			intent.putExtra(Inicio.CP, cursor.getString(cursor.getColumnIndex(ContactsContract.CP))); // recipients.
			intent.putExtra(Inicio.TELEMOVEL, cursor.getString(cursor.getColumnIndex(ContactsContract.TELEMOVEL))); // recipients.
			intent.putExtra(Inicio.TELEFONE, cursor.getString(cursor.getColumnIndex(ContactsContract.TELEFONE))); // recipients.
			intent.putExtra(Inicio.EMAIL, cursor.getString(cursor.getColumnIndex(ContactsContract.EMAIL))); // recipients.

			// Start the new activity using the explicit intent created previously.
			startActivity(intent);
		
	}
	
	
	/**
	 * Custom Array Adapter for contacts.
	 * 
	 
	 * */
	private class ContactsAdapter extends CursorAdapter
	{
		private final Context _ctx;
		
		public ContactsAdapter() 
		{
			super(ManutencaoActivity.this, null, 0);
			_ctx = ManutencaoActivity.this;
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
			
			h.tv.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.NOME)));
			// Set the delete action in the btn.
			h.bt1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) 
				{
					int id = cursor.getInt(cursor.getColumnIndex(ContactsContract._ID));
					Intent intent = new Intent(ContactsAdapter.this._ctx, RemoveContactService.class);
					intent.putExtra(RemoveContactService.POSITION, id);
					startService(intent);
				}
			});
				
				
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2)
		{
			Holder h = new Holder(); 
			View v = getLayoutInflater().inflate(R.layout.manutencao_item, null); 
			h.tv = (TextView)v.findViewById(R.id.my_manutencao_item_id);
			h.bt1 = (Button)v.findViewById(R.id.my_manutencao_item_apagar_id);
			
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
			
		}
		
		
	}
	
}
