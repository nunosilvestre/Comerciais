package com.NAQS.comerciais;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.android.gms.internal.bm;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class GoogleBMP extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlebmp);

        // Get a handle to the Map Fragment
        new DownloadFilesTask().execute();
        
    }
	
	public static Bitmap getGoogleMapThumbnail(String Chegada, String Partida){
		
		String MIni = "&markers=color:blue|label:I|" + Partida;
		String MFim = "&markers=color:green|label:F|" + Chegada;
		
		
        //URL = URL.replaceAll(" ", "%20");
		Bitmap bmp = null;
		InputStream in = null;
        try {

        	//URI url = new URI("http://maps.google.com/maps/api/staticmap?center="+ Chegada + MIni + MFim + "&zoom=15&size=640x640&sensor=false");
        	//URI url = new URI("http", "maps.google.com", "/maps/api/staticmap", "center="+ Chegada + MIni + MFim + "&zoom=15&size=640x640&sensor=false", null);
        	//URL url = new URL("http://maps.google.com/maps/api/staticmap?center=" + Chegada + MIni + MFim + "&zoom=15&size=640x640&sensor=false");
        	String url = "http://maps.googleapis.com/maps/api/staticmap?center=Berkeley,CA&zoom=14&size=400x400&sensor=false";
        	HttpClient httpclient = new DefaultHttpClient();  
        	HttpGet request = new HttpGet(url);
        	Log.i("Testes", url.toString());	
        	HttpResponse response = httpclient.execute(request);
        	BufferedHttpEntity entity = new BufferedHttpEntity(response.getEntity());
        	
        	in = entity.getContent();
            bmp = BitmapFactory.decodeStream(in);
            
            in.close();
//        	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//        	connection.setDoInput(true);
//        	connection.connect();
//        	
//        	InputStream in = connection.getInputStream();
//        	BitmapFactory.Options options = new BitmapFactory.Options();
//        	bmp = BitmapFactory.decodeStream(in, null, options);
//        	
        } catch (Exception e) {
        	e.printStackTrace();
        } 

        return bmp;
    }
	private class DownloadFilesTask extends AsyncTask<Void, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Void... arg0) {
			
			String partida = GoogleBMP.this.getIntent().getStringExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerIni);
	        String chegada = GoogleBMP.this.getIntent().getStringExtra(com.NAQS.comerciais.Inicio.GOOGLEMarkerFim);
	        			
			return getGoogleMapThumbnail(chegada, partida);
		}
	    @Override
	    protected void onPostExecute(Bitmap result) {
	    	
	    	
	    	ImageView imagem = (ImageView) findViewById(R.id.bitmapGoogle);
	    	 imagem.setImageBitmap(result);
	    	
	    } 
	 }
	 
}
