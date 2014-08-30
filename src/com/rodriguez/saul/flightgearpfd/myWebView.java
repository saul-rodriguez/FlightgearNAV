package com.rodriguez.saul.flightgearpfd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.webkit.WebView;


public class myWebView  extends WebView {
	Context mContext;
	
	int width;
	int height;
	
	public int x,y; //offset values from center
	
	//Encoder Modes
	public int mode;
	public int range;
	
	public int rangeref;
	float rangescale;
	
	float dpi;
	
	//position
	float lat;
	float lon;
	float reflat;
	float reflon;
	
	
	//Helper distance	
	double distx;
	double disty;
	
	//Bitmaps
	Bitmap planebmp;
	Bitmap symbols;
	//Matrices
	Matrix planeMatrix;
	
	float scaleFactor;
	float realheading;
	
	public myWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		mContext = context;
		
		x = 0;
		y = -20;
		
		mode = 0;
		range = 10;
		rangeref = 0;
		rangescale = 1;
		
		dpi = 160;
		
		scaleFactor = 1;
	    realheading = 0;
		
		planeMatrix = new Matrix();
		planebmp = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.planebmp);
		//planebmp = Bitmap.createBitmap(symbols, 0, 0, 67, 85);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		width = this.getWidth();
		height = this.getHeight();
		
		
		//Log.d("Saul",String.format("Width WebView %d", width));
		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        
		//paint.setColor(Color.BLUE);
		//paint.setStyle(Style.STROKE);
		//paint.setStrokeWidth(2);
		
		drawPlane(canvas,paint);
		//canvas.drawCircle((float)(width/2. + distx),(float)(height/2. - (disty-20)), 10, paint);
		
	}
	
	void drawPlane(Canvas canvas, Paint paint)
	{
		
		float aux;
		
		aux = (float)(1/2.54*dpi); //real height of plane = 1cm
		scaleFactor = aux/planebmp.getHeight();
		
		planeMatrix.reset();
		planeMatrix.postTranslate(-planebmp.getWidth()/2, -planebmp.getHeight()/2);
		planeMatrix.postScale(scaleFactor, scaleFactor);
		planeMatrix.postRotate(realheading);
				
		planeMatrix.postTranslate((float)(width/2 + distx),(float)(height/2 + 20 - disty));
								
		canvas.drawBitmap(planebmp, planeMatrix, paint);
	}

	void updateRange() {
		
		if (rangeref == range) {
			//Calculate plane distances 
			calcDistance(reflat,reflon,lat,lon);
			dist2pixels();
			
			invalidate();
			
			return;
		}
		
		//Reference coord. changed
		updateRefPos();
		
		rangeref = range;
		
		int range_aux;
		
		switch (range) {
			case 10:
						range_aux = 1;
						rangescale = 1;
						break;
			case 20:
						range_aux = 3;
						rangescale = 2;
						break;
			case 40:
						range_aux = 5;
						rangescale = 4;
						break;
			case 80:
						range_aux = 7;
						rangescale = 8;
						break;
			case 160:	
						range_aux = 9;
						rangescale = 16;
						break;
			case 320:
						range_aux = 11;
						rangescale = 32;
						break;
			case 640:
						range_aux = 13;
						rangescale = 64;
						break;
			default:	
						range_aux = 1;
						break;
		}
		
		String aux = "http://skyvector.com/?ll=" + String.format("%f", lat) + "," + String.format("%f", lon) + 
				"&chart=301&zoom=" + String.format("%d", range_aux);
		this.loadUrl(aux);
	}
	
	float calcDistance(float Lat1, float Lon1, float Lat2, float Lon2)
	{
		double latMid, m_per_deg_lat, m_per_deg_lon, deltaLat, deltaLon,dist_m;

		latMid = (Lat1+Lat2 )/2.0*(Math.PI/180);  // radians!  just use Lat1 for slightly less accurate estimate


		m_per_deg_lat = 111132.954 - 559.822 * Math.cos( 2.0 * latMid ) + 1.175 * Math.cos( 4.0 * latMid);
		m_per_deg_lon = (3.14159265359/180 ) * 6367449 * Math.cos ( latMid );

				
		deltaLat = (Lat2 - Lat1);
		deltaLon = (Lon2 - Lon1);

		disty = deltaLat * m_per_deg_lat;
		distx = deltaLon * m_per_deg_lon;
		
		//dist_m = Math.sqrt (  Math.pow( deltaLat * m_per_deg_lat,2) + Math.pow( deltaLon * m_per_deg_lon , 2) );
		dist_m = Math.sqrt (  Math.pow( disty,2) + Math.pow( distx , 2) );
		return (float) dist_m;
	}
	
	void dist2pixels()
	{
		distx = (distx/1852.0)*(12.3/30)*(1/2.54)*dpi/rangescale; //12.3cm = 30nm
		disty = (disty/1852.0)*(12.3/30)*(1/2.54)*dpi/rangescale;		
	}
	
	//updates center of the map coord.
	void updateRefPos() {
		reflat = lat;
		reflon = lon;
	}
	
	
	
	void setMode(int newmode)
	{
		mode = newmode;
	}
	
	void setRange(int newrange)
	{
		range = newrange;
	}
	
	void setLat(float newLat)
	{
		lat = newLat;
	}
	
	void setLon(float newLon)
	{
		lon = newLon;
	}
	
	void setRadhead(float head)
	{
		realheading = head;
	}
}
