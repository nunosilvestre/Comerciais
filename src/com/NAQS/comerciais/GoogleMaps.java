package com.NAQS.comerciais;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.SupportMapFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.Toast;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;

public class GoogleMaps extends FragmentActivity{
	
		    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.maps);

	        // Get a handle to the Map Fragment
	        
//	        GoogleMap map = ((MapFragment) getFragmentManager()
//	                .findFragmentById(R.id.map)).getMap();
//
//	        LatLng sydney = new LatLng(-33.867, 151.206);
//
//	        map.setMyLocationEnabled(true);
//	        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//
//	        map.addMarker(new MarkerOptions()
//	                .title("Sydney")
//	                .snippet("The most populous city in Australia.")
//	                .position(sydney));

	        FragmentManager janelaFragmento = getSupportFragmentManager(); // Cria o objeto janelaFragmento do tipo FragmentManager e d‡-nos o gestor de fragmentos da nossa janela que Ž s— um
	        SupportMapFragment mapa=(SupportMapFragment) janelaFragmento.findFragmentById(R.id.map); // inicializa o nosso fragmento, com cast
	        
	        GoogleMap map = mapa.getMap();
	        //double latitudeAqui = getIntent().getDoubleExtra("latitudemapa", 0); //traz da activitymain o conteudo da variavel latitudemapa, com as coordenadas GPS
	        //double longitudeAqui = getIntent().getDoubleExtra("longitudemapa",0);
	        double latitudeAqui = -33.867; //traz da activitymain o conteudo da variavel latitudemapa, com as coordenadas GPS
	        double longitudeAqui = 151.206;
	        		

	        LatLng saldanha = new LatLng(latitudeAqui,longitudeAqui);
	        Toast.makeText (this,latitudeAqui+"",Toast.LENGTH_SHORT).show();
	        Toast.makeText (this,longitudeAqui+"",Toast.LENGTH_SHORT).show();

	        map.setMyLocationEnabled(true);
	       map.moveCamera(CameraUpdateFactory.newLatLngZoom(saldanha, 18)); //faz zoom ao ponto de 13 vezes

//	     lerTrilho (R.xml.saldanha);
//	     map.addPolyline(trilho);
	    
	     
		    
		    
		    
		    
		    
		    }




//private class DownloadFilesTask extends AsyncTask<Void, Void, Bitmap> {
//
//	@Override
//	protected Bitmap doInBackground(Void... arg0) {
//		
//		String partida = GoogleMaps.this.getIntent().getStringExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerIni);
//        String chegada = GoogleMaps.this.getIntent().getStringExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerFim);
//        			
//		return getGoogleMapThumbnail(chegada, partida);
//	}
//    @Override
//    protected void onPostExecute(Bitmap result) {
//    	
//    	
//    	ImageView imagem = (ImageView) findViewById(R.id.bitmapGoogle);
//    	 imagem.setImageBitmap(result);
//    	
//    } 
// }
}
