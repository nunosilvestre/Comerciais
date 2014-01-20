package com.NAQS.comerciais;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ListaCliente extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaentidade);
		
		TextView text = (TextView) findViewById(R.id.outNome);
		text.setText(getIntent().getStringExtra(com.NAQS.comerciais.Inicio.NOME));
		TextView text1 = (TextView) findViewById(R.id.outMorada);
		text1.setText(getIntent().getStringExtra(com.NAQS.comerciais.Inicio.MORADA));
		TextView text2 = (TextView) findViewById(R.id.outCidade);
		text2.setText(getIntent().getStringExtra(com.NAQS.comerciais.Inicio.CIDADE));
		TextView text3 = (TextView) findViewById(R.id.outCP);
		text3.setText(getIntent().getStringExtra(com.NAQS.comerciais.Inicio.CP));
		TextView text4 = (TextView) findViewById(R.id.outTelefone);
		text4.setText(getIntent().getStringExtra(com.NAQS.comerciais.Inicio.TELEFONE));
		TextView text5 = (TextView) findViewById(R.id.outTelemovel);
		text5.setText(getIntent().getStringExtra(com.NAQS.comerciais.Inicio.TELEMOVEL));
		TextView text6 = (TextView) findViewById(R.id.outEMail);
		text6.setText(getIntent().getStringExtra(com.NAQS.comerciais.Inicio.EMAIL));
		
		
		
		
		
	}
	
	

	
	
	

}
