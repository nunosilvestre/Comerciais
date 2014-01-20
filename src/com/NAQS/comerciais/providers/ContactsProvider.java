package com.NAQS.comerciais.providers;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ContactsProvider extends ContentProvider
{

	public static final String AUTHORITY = "com.NAQS.comerciais.providers";
	public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);	
	
	private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int EMAIL_ID  = 1;
	private static final int EMAIL_ALL = 2;
	
	private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.com.NAQS.comerciais.providers." + ContactsContract.TABLEENTIDADES;
	private static final String MIME_ONE = "vnd.android.cursor.item/vnd.com.NAQS.comerciais.providers." + ContactsContract.TABLEENTIDADES;
	
	private SQLiteOpenHelper _helper;
	
	static 
	{
		URIMATCHER.addURI(AUTHORITY, "EMAIL/#", EMAIL_ID);
		URIMATCHER.addURI(AUTHORITY, "EMAIL", EMAIL_ALL);
	}
	
	@Override
	public boolean onCreate() 
	{
		_helper = new ContactsHelper(getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) 
	{
		return URIMATCHER.match(uri) == EMAIL_ALL ? MIME_ALL : MIME_ONE;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) 
	{
		SQLiteDatabase db = _helper.getWritableDatabase();
		try
		{
			long row = db.insert(ContactsContract.TABLEENTIDADES, null, values);
			return row == -1 ? null : ContentUris.withAppendedId(uri, row);
		} finally 
		{
			db.close();
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) 
	{
		SQLiteDatabase db = _helper.getReadableDatabase();	
		Cursor cursor = db.query(ContactsContract.TABLEENTIDADES, projection, selection, selectionArgs, null, null, sortOrder);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) 
	{
		SQLiteDatabase db = _helper.getWritableDatabase();
		try 
		{
			return db.update(ContactsContract.TABLEENTIDADES, values, selection, selectionArgs);
		} finally 
		{
			db.close();
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) 
	{
		SQLiteDatabase db = _helper.getWritableDatabase();
		try 
		{
			return db.delete(ContactsContract.TABLEENTIDADES, selection, selectionArgs);
		} finally
		{
			db.close();
		}
	}
	
	private static class ContactsHelper extends SQLiteOpenHelper
	{
		private static final String DB_NAME = "comerciais1.db";
		private static final int DB_VERSION = 1;
		
		public ContactsHelper(Context context) 
		{
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			String columns = ContactsContract._ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "
							 + ContactsContract.EMAIL + " TEXT NOT NULL, "
							 + ContactsContract.NOME + " TEXT NOT NULL, "
							 + ContactsContract.MORADA + " TEXT NOT NULL, "
							 + ContactsContract.CIDADE + " TEXT NOT NULL, "
							 + ContactsContract.CP + " TEXT NOT NULL, "
							 + ContactsContract.TELEFONE + " TEXT NOT NULL, "
							 + ContactsContract.TELEMOVEL + " TEXT NOT NULL";
			
			String sql = "CREATE TABLE IF NOT EXISTS " + ContactsContract.TABLEENTIDADES + " (" + columns + ")";
			db.execSQL(sql);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			db.execSQL("DROP TABLEENTIDADES IF EXISTS " + ContactsContract.TABLEENTIDADES);
			onCreate(db);
		}
	}
}
