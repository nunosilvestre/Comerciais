package com.NAQS.comerciais;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.NAQS.comerciais.ShowLocationActivity;


public class Inicio extends Activity {

	public final static String TAG = "tag";
	public final static String EMAIL_TEXT = "com.NAQS.comerciais.Inicio.EMAIL_TEXT";
	public static String NOME="nome";
	public static String MORADA="morada";
	public static String CIDADE="cidade";
	public static String CP="cp";
	public static String TELEFONE="telefone";
	public static String TELEMOVEL="telemovel";
	public static String EMAIL="email";
	public static String GOOGLEMarkerIni="googlemarkerini";
	public static String GOOGLEMarkerFim="googlemarkerfim";
	public static double GPSLatitude = 0;
	public static double GPSLongitude = 0;
	private LocationManager locationManager;
	private String provider;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the locatioin provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);

	    // Initialize the location fields
	    if (location != null) {
	      System.out.println("Provider " + provider + " has been selected.");
	      onLocationChanged(location);
	    } else {
	    	
	    	Inicio.GPSLongitude = 0;
	    	Inicio.GPSLatitude = 0;
	    }
		
		//Lista de Clientes
		findViewById(R.id.BClientes).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				
				Intent intent = new Intent(Inicio.this, ContactsActivity.class);
				// Start the new activity using the explicit intent created previously.
				startActivity(intent);
			}
		});
		//insere de Clientes
//		findViewById(R.id.BClientesAdd).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) 
//			{
//				
//				Intent intent = new Intent(Inicio.this, InsereCliente.class);
//				// Start the new activity using the explicit intent created previously.
//				startActivity(intent);
//			}
//		});
		//Lista de Fornecedores
		findViewById(R.id.BFornecedores).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				
				Intent intent = new Intent(Inicio.this, ContactsActivity.class);
				// Start the new activity using the explicit intent created previously.
				startActivity(intent);
			}
		});
		//Lista de Produtos
		findViewById(R.id.BProdutos).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				
				Intent intent = new Intent(Inicio.this, Produtos.class);
				// Start the new activity using the explicit intent created previously.
				startActivity(intent);
			}
		});
	
	}
	
	public void onLocationChanged(Location location) {
	    Inicio.GPSLatitude = location.getLatitude();
	    Inicio.GPSLongitude = location.getLongitude();
	    
	  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		Boolean superRes = super.onCreateOptionsMenu(menu);
		// Inflates the menu.
		getMenuInflater().inflate(R.menu.inicio, menu);
		return true && superRes;
	}
	
	/*
	 * Callback method that is called always an item is 
	 * selected in the options menu.
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			// For add one more contact.
			
			// For open the preferences.
			case R.id.action_settings:
				startActivity(new Intent(this, Preferencias.class));
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	

	
	
	

}
