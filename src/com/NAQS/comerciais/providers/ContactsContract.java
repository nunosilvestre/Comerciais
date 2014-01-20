package com.NAQS.comerciais.providers;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContactsContract 
{
	// Table name.
		public static final String TABLEENTIDADES = "CONTACTS";
		
		// Content URI.
		public static final Uri CONTENT_URI = Uri.withAppendedPath(ContactsProvider.CONTENT_URI, TABLEENTIDADES);
		
		// Columns names.
		public static final String _ID 	 = BaseColumns._ID,
								   EMAIL = "email",
								   NOME = "nome",
								   MORADA = "morada",
								   CIDADE = "cidade",
								   CP = "cp",
								   TELEFONE = "telefone",
								   TELEMOVEL = "telemovel";
}
