package com.rodriguez.saul.flightgearpfd;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class FIXdb {
Context mContext;
	
	//NAV database related
	static final int SIZE = 119000;
	//static final int SIZE = 1000;
	static final int SIZE_NEAR = 50;
	
	//int[] ID;
	float[] longitude;
	float[] latitude;
	String[] name;
	
	//Nearby objects
	//int[] mID;
	float[] mlongitude;
	float[] mlatitude;
	String[] mname;
	int mnear;
	
	//Helper distance	
	double distx;
	double disty;
		
	
	public FIXdb(Context context)
	{
		mContext = context;
		
		//ID = new int[SIZE];
		latitude = new float[SIZE];
		longitude = new float[SIZE];		
		name = new String[SIZE];
		
		//mID = new int[SIZE_NEAR];
		mlatitude = new float[SIZE_NEAR];
		mlongitude = new float[SIZE_NEAR];		
		mname = new String[SIZE_NEAR];
		mnear = 0;
				
	}
	
	public void readDB()
	{
		//ContextWrapper context;
				AssetManager am = mContext.getAssets();
				InputStream is = null;
								
				//Opening the nav file
				try {
					is = am.open("fix.csv");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//Save the data in a buffer
				byte[] bytesdata;
				String[] outData;
				bytesdata = new byte[3200000];
				
				try {
					is.read(bytesdata);
					//is.read
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String data = new String(bytesdata);
				
				//Split the data in lines
				outData = data.split("\n");
				
				//Log.d("Saul2",outData[26700]);
				Log.d("Saul2", String.format("lenght = %d",outData.length));
				
				
				//Fill class variables
				String aux[];
				//aux = new String[3];
				
				for (int i = 0; i < SIZE; i++) {
					aux = outData[i].split(",");
					//ID[i] = Integer.valueOf(aux[0]);
					latitude[i] = Float.valueOf(aux[0]);
					longitude[i] = Float.valueOf(aux[1]);
					name[i] = aux[2];
				}
				
				
				Log.d("Saul",String.format("LAT = %f, LON = %f, NAM = ",latitude[0],longitude[0])+name[0]);
				Log.d("Saul",String.format("LAT = %f, LON = %f, NAM = ",latitude[SIZE-1],longitude[SIZE-1])+name[SIZE-1]);
				//, LAT = %f, LON = %f",ID[0],latitude[0],longitude[0]);
				
	}
	
	void selectNear(float lat, float lon)
	{
		mnear = 0;
		float dist;
		
		for (int i = 0; i < SIZE; i++){
			
			dist = calcDistance(lat,lon,latitude[i],longitude[i]);
			
			if (dist < 200000) { //Save objects which are 200 km close
				//mID[mnear] = ID[i];
				mlatitude[mnear] = latitude[i];
				mlongitude[mnear] = longitude[i];
				mname[mnear] = name[i];
				Log.d("Saul",String.format("%d ",mnear)+mname[mnear]);
				mnear++;
								
				if (mnear == SIZE_NEAR)					
					break;
			}			
			
			
		}
		
		
	}
	
	float calcDistance(float Lat1, float Lon1, float Lat2, float Lon2)
	{
		double latMid, m_per_deg_lat, m_per_deg_lon, deltaLat, deltaLon,dist_m;

		latMid = (Lat1+Lat2 )/2.0*(Math.PI/180);  // radians!  just use Lat1 for slightly less accurate estimate


		m_per_deg_lat = 111132.954 - 559.822 * Math.cos( 2.0 * latMid ) + 1.175 * Math.cos( 4.0 * latMid);
		m_per_deg_lon = (3.14159265359/180 ) * 6367449 * Math.cos ( latMid );

		//deltaLat = Math.abs(Lat1 - Lat2);
		//deltaLon = Math.abs(Lon1 - Lon2);
		
		deltaLat = (Lat2 - Lat1);
		deltaLon = (Lon2 - Lon1);

		disty = deltaLat * m_per_deg_lat;
		distx = deltaLon * m_per_deg_lon;
		
		//dist_m = Math.sqrt (  Math.pow( deltaLat * m_per_deg_lat,2) + Math.pow( deltaLon * m_per_deg_lon , 2) );
		dist_m = Math.sqrt (  Math.pow( disty,2) + Math.pow( distx , 2) );
		return (float) dist_m;
	}
}
