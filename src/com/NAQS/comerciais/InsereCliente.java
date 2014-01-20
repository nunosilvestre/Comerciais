package com.NAQS.comerciais;






import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.NAQS.comerciais.services.AddContactService;

public class InsereCliente extends Activity {

	public final static String TAG = "tag";
	public final static String EMAIL_TEXT = "com.NAQS.comerciais.MainActivity.EMAIL_TEXT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insereentidade);
		
		//grava Clientes
		findViewById(R.id.BTgravar).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(InsereCliente.this, AddContactService.class);
				intent.putExtra(AddContactService.NOME, ((EditText)InsereCliente.this.findViewById(R.id.inNome)).getText().toString());
				intent.putExtra(AddContactService.MORADA, ((EditText)InsereCliente.this.findViewById(R.id.inMorada)).getText().toString());
				intent.putExtra(AddContactService.CIDADE, ((EditText)InsereCliente.this.findViewById(R.id.inCidade)).getText().toString());
				intent.putExtra(AddContactService.CP, ((EditText)InsereCliente.this.findViewById(R.id.inCP)).getText().toString());
				intent.putExtra(AddContactService.TELEFONE, ((EditText)InsereCliente.this.findViewById(R.id.inTelefone)).getText().toString());
				intent.putExtra(AddContactService.TELEMOVEL, ((EditText)InsereCliente.this.findViewById(R.id.inTelemovel)).getText().toString());
				intent.putExtra(AddContactService.EMAIL, ((EditText)InsereCliente.this.findViewById(R.id.inEMail)).getText().toString());
						
				startService(intent);
				
			}
		});
		//insere de Clientes
//		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) 
//			{
//				Intent intent = new Intent(InsereCliente.this, AddContactService.class);
//				intent.putExtra(AddContactService.NOME, "nuno silvestre");
//				intent.putExtra(AddContactService.MORADA, "Calçada Galvao 139 porta b");
//				intent.putExtra(AddContactService.CIDADE, "Lisboa");
//				intent.putExtra(AddContactService.CP, "1400-167");
//				intent.putExtra(AddContactService.TELEFONE, "216164893");
//				intent.putExtra(AddContactService.TELEMOVEL, "916164893");
//				intent.putExtra(AddContactService.EMAIL, "nunosilvestre@gmail.com");
//						
//				startService(intent);
//				
//			}
//		});
		
	}
	
	

	
	
	

}
