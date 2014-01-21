package com.NAQS.comerciais;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.Toast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

public class GoogleMaps extends FragmentActivity 
{
	
	private double longitute;
	private double latitude;
	
		    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.maps);
	        //mLocationClient = new LocationClient(this, this, this);
	        //Location mCurrentLocation = mLocationClient.getLastLocation();
	        
//	        mLocationClient = new LocationClient(this, this, this);
//	        Location mCurrentLocation = mLocationClient.getLastLocation();
//	        
//	        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
//	        map = mapFragment.getMap();
//
//	        map.setMyLocationEnabled(true);
	        
	        
//	        FragmentManager janelaFragmento = getSupportFragmentManager(); // Cria o objeto janelaFragmento do tipo FragmentManager e d‡-nos o gestor de fragmentos da nossa janela que Ž s— um
//	        SupportMapFragment mapa=(SupportMapFragment) janelaFragmento.findFragmentById(R.id.map); // inicializa o nosso fragmento, com cast
	        
	       // GoogleMap map = mapa.getMap();
	        
	        //double latitudeAqui = getIntent().getDoubleExtra("latitudemapa", 0); //traz da activitymain o conteudo da variavel latitudemapa, com as coordenadas GPS
	        //double longitudeAqui = getIntent().getDoubleExtra("longitudemapa",0);
	        //JSONObject jsonObject = getLocationInfo("calcada galvao 139 porta b, 1400-167 lisboa");
	        String partida = GoogleMaps.this.getIntent().getStringExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerIni);
	        String chegada = GoogleMaps.this.getIntent().getStringExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerFim);
	        new DownloadFilesTask().execute(chegada);
	        

	        double latitudeAqui = -33.867; //traz da activitymain o conteudo da variavel latitudemapa, com as coordenadas GPS
	        double longitudeAqui = 151.206;
//	        		double lat = mCurrentLocation.getAltitude();
//	        		double lon = mCurrentLocation.getLongitude();
	        		double lat = 38.6775365; //traz da activitymain o conteudo da variavel latitudemapa, com as coordenadas GPS
	    	        double lon = -9.17538643;
//	        LatLng saldanha = new LatLng(latitude, longitute);
//	        Toast.makeText (this,latitude+"",Toast.LENGTH_SHORT).show();
//	        Toast.makeText (this,longitute+"",Toast.LENGTH_SHORT).show();
//
//	        
//			map.addMarker(new MarkerOptions()
//	        .position(saldanha)
//	        .title("Hello world"));
//	        map.setMyLocationEnabled(true);
//	        
//	        
//	        
//	       map.moveCamera(CameraUpdateFactory.newLatLngZoom(saldanha, 18)); //faz zoom ao ponto de 13 vezes


		    }

		    public static JSONObject getLocationInfo(String address) {
		        StringBuilder stringBuilder = new StringBuilder();
		        try {
		        //address = "calcada galvao 139 porta b, 1400-167 lisboa";
		        address = address.replaceAll(" ","%20");    

		        HttpPost httppost = new HttpPost("http://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false");
		        HttpClient client = new DefaultHttpClient();
		        HttpResponse response;
		        stringBuilder = new StringBuilder();


		            response = client.execute(httppost);
		            HttpEntity entity = response.getEntity();
		            InputStream stream = entity.getContent();
		            int b;
		            while ((b = stream.read()) != -1) {
		                stringBuilder.append((char) b);
		            }
		        } catch (ClientProtocolException e) {
		        } catch (IOException e) {
		        }

		        JSONObject jsonObject = new JSONObject();
		        try {
		            jsonObject = new JSONObject(stringBuilder.toString());
		        } catch (JSONException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }

		        return jsonObject;
		    } 
			

		    private class DownloadFilesTask extends AsyncTask<String, Void, JSONObject> {

				@Override
				protected JSONObject doInBackground(String... morada) {
					
					//String partida = GoogleBMP.this.getIntent().getStringExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerIni);
			        //String chegada = GoogleBMP.this.getIntent().getStringExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerFim);
			        			
					return getLocationInfo(morada[0]);
							
				}
			    @Override
			    protected void onPostExecute(JSONObject result) {
			    	
			    	try {

			            double longitute = ((JSONArray)result.get("results")).getJSONObject(0)
			                .getJSONObject("geometry").getJSONObject("location")
			                .getDouble("lng");

			            double latitude = ((JSONArray)result.get("results")).getJSONObject(0)
			                .getJSONObject("geometry").getJSONObject("location")
			                .getDouble("lat");

			            LatLng saldanha = new LatLng(latitude, longitute);
//				        Toast.makeText (this,latitude+"",Toast.LENGTH_SHORT).show();
//				        Toast.makeText (this,longitute+"",Toast.LENGTH_SHORT).show();
			            FragmentManager janelaFragmento = getSupportFragmentManager(); // Cria o objeto janelaFragmento do tipo FragmentManager e d‡-nos o gestor de fragmentos da nossa janela que Ž s— um
				        SupportMapFragment mapa=(SupportMapFragment) janelaFragmento.findFragmentById(R.id.map); // inicializa o nosso fragmento, com cast
			            GoogleMap map = mapa.getMap();
						map.addMarker(new MarkerOptions()
				        .position(saldanha)
				        .title("Hello world"));
				        map.setMyLocationEnabled(true);
				        
				        
				        
				       map.moveCamera(CameraUpdateFactory.newLatLngZoom(saldanha, 18)); //faz zoom ao ponto de 13 vezes
			        } catch (JSONException e) {
			           

			        }
			    	
			    } 
			 }    
		 

}
