package com.rodriguez.saul.flightgearpfd;


//import com.example.com.readnav.test.NAVdb;
import com.rodriguez.saul.flightgearpfd.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;

public class Plane777 extends Plane {
	
	

	//Bitmap mask; defined in the base class!
	Bitmap maskfull;
	Bitmap hsi;
	Bitmap hsiarc;
	Bitmap symbols;
	
	Bitmap triangle;
	Bitmap aphead;
	Bitmap vorlup;
	Bitmap vorlback;
	Bitmap vorrup;
	Bitmap vorrback;
	Bitmap adflup;
	Bitmap adflback;
	Bitmap adfrup;
	Bitmap adfrback;
	Bitmap radialup;
	Bitmap radialback;
	Bitmap defhor;
	Bitmap defver;
	Bitmap defnid;
	Bitmap bug;
	Bitmap wind;
	
	Bitmap circenter;
	Bitmap cirup;
	Bitmap cirback;
	
	Bitmap ndsymbols;
	Bitmap ndvor;
	Bitmap ndvortac;
	Bitmap ndfix;
	Bitmap ndwp;
	
			
	Matrix maskMatrix;
	Matrix maskfullMatrix;
	Matrix hsiMatrix;
	Matrix hsiarcMatrix;
	Matrix triangleMatrix;
	Matrix apheadMatrix;
	Matrix vorlupMatrix;
	Matrix vorlbackMatrix;
	Matrix vorrupMatrix;
	Matrix vorrbackMatrix;
	Matrix adflupMatrix;
	Matrix adflbackMatrix;
	Matrix adfrupMatrix;
	Matrix adfrbackMatrix;
	Matrix radialupMatrix;
	Matrix radialbackMatrix;
	Matrix defhorMatrix;
	Matrix defverMatrix;
	Matrix defnidMatrix;
	Matrix bugMatrix;
	Matrix windMatrix;
	Matrix circenterMatrix;
	Matrix cirupMatrix;
	Matrix cirbackMatrix;
	
	Matrix ndvorMatrix;
	Matrix ndfixMatrix;
	Matrix ndwpMatrix;
	
	
	Context mContext;
	
	//NAV database
	NAVdb navdb;	
	FIXdb fixdb;
	int sync;
	
	
	public Plane777(Context context) {
		
		mContext = context;
		
		//Default plane
		planeType = B777;
		
		//Initialize the matrices;
		maskMatrix = new Matrix();
		maskfullMatrix = new Matrix();
		hsiMatrix = new Matrix();
		hsiarcMatrix = new Matrix();
		
		triangleMatrix = new Matrix();
		apheadMatrix = new Matrix();
		vorlupMatrix = new Matrix();
		vorlbackMatrix = new Matrix();
		vorrupMatrix = new Matrix();
		vorrbackMatrix = new Matrix();
		adflupMatrix = new Matrix();
		adflbackMatrix = new Matrix();
		adfrupMatrix = new Matrix();
		adfrbackMatrix = new Matrix();
		radialupMatrix = new Matrix();
		radialbackMatrix = new Matrix();
		defhorMatrix = new Matrix();
		defverMatrix = new Matrix();
		defnidMatrix = new Matrix();
		bugMatrix = new Matrix();
		windMatrix = new Matrix();
		circenterMatrix = new Matrix();
		cirupMatrix = new Matrix();
		cirbackMatrix = new Matrix();
		ndvorMatrix = new Matrix();
		ndfixMatrix = new Matrix();
		ndwpMatrix = new Matrix();
		
		//Load bitmaps
		
		mask = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.navmask);
		maskfull = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.navmaskfull);
		hsi = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.navhsi2);
		hsiarc = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.navhsiarc2);
		symbols = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.navsymbols);
		aphead = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.aphdg);
		
		//Create bitmaps from symbols
		triangle = Bitmap.createBitmap(symbols, 335, 289, 70, 78);
		
		vorlup = Bitmap.createBitmap(symbols, 73, 280, 35, 83);
		vorlback = Bitmap.createBitmap(symbols, 73, 380, 35, 83);
				
		vorrup = Bitmap.createBitmap(symbols, 215, 267, 58, 110);
		vorrback = Bitmap.createBitmap(symbols, 215, 383, 58, 100);
		
		adflup = Bitmap.createBitmap(symbols, 112, 280, 35, 83);
		adflback = Bitmap.createBitmap(symbols, 112, 380, 35, 83);
		
		adfrup = Bitmap.createBitmap(symbols, 152, 267, 58, 110);
		adfrback = Bitmap.createBitmap(symbols, 152, 383, 58, 100);
		
		radialup = Bitmap.createBitmap(symbols, 298, 12, 21, 488);
		radialback = Bitmap.createBitmap(symbols, 465, 12, 21, 488);
		
		defhor = Bitmap.createBitmap(symbols, 111, 249, 178, 15);
		defnid = Bitmap.createBitmap(symbols, 374, 372, 30, 130);
		
		defver = Bitmap.createBitmap(symbols, 268, 85, 29, 180);
		bug = Bitmap.createBitmap(symbols, 352, 27, 37, 58);
		
		wind = Bitmap.createBitmap(symbols, 70, 115, 35, 114);
		
		circenter = Bitmap.createBitmap(symbols, 337, 200, 64, 84);
		cirup = Bitmap.createBitmap(symbols, 402, 102, 48, 203);
		cirback = Bitmap.createBitmap(symbols, 418, 311, 17, 192);
		
		ndsymbols = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.nd_symbols);
		
		//ndvortac = Bitmap.createBitmap(ndsymbols, 118, 0, 38, 34);
		ndvortac = Bitmap.createBitmap(ndsymbols, 39, 0, 38, 34);
		//ndvor = Bitmap.createBitmap(ndsymbols, 79, 0, 38, 34);
		ndvor = Bitmap.createBitmap(ndsymbols, 79, 0, 38, 34);
		ndfix = Bitmap.createBitmap(ndsymbols, 160, 0, 30, 26);
		ndwp = Bitmap.createBitmap(ndsymbols, 191, 0, 48, 49);
		
		//Initialize all the parameters
		scaleFactor = (float)1.0;
		
		heading = 330;
		apheading = 10;
		
		//VORL
		switchvorl = 0;
				
		vorlid = "QMS";
		vorldme = 9000;
		vorldmeinrange = true; // is DME in range ?
		vorlinrange = true;
		vorlfreq = (float)114.8;
		vorldirection = 10;
		
		
		//VORR
		switchvorr = 1;
		
		vorrid = "QMS";
		vorrdme = 9000;
		vorrdmeinrange = true; // is DME in range ?
		vorrinrange = true; // is nav in range ?
		vorrfreq = (float)114.8;
		vorrdirection = 20;
		
		//ADFL
		adflid = "ZUI";
		adflinrange = true;
		adflfreq = 290;
		adfldirection = (float)80.3;
				
		//ADFL
		adfrid = "ZUI";
		adfrinrange = true;
		adfrfreq = 290;
		adfrdirection = (float)80.3;
		
		//Radial NAV1
		radial = 330;
		realheading = 0;
		radialdef = 0;
		gsdef = 0;
		
		//Encoder Modes
		mode = 0;
		range = 10;
		modebut = false; //false == arc, true == circle
		
		//Speed
		truespeed = 0;
		groundspeed = 0;
		
		//windspeed

		windspeed = 0;
		windheading = 0;

		//Position
		lat = 0;
		lon = 0;
		
		reflat = 0;
		reflon = 0;
		
		shownav = true;
		showcir = true;
		showroute = true;
		showfix = true;
			
		//route
		
		latwp = new float[ROUTESIZE];
		lonwp = new float[ROUTESIZE];
		
		currentwp = "None";
		numwp = 0;
		
		
	}
	
	public void setdb(NAVdb[] nav, FIXdb[] fix)
	{
		navdb = nav[0];
		fixdb = fix[0];
	}
	
	public void readDB() {
		// Read Database
		//sync = 1;
		navdb = new NAVdb(mContext);
		navdb.readDB(); // Read nav.csv file from assets folder
				
		fixdb = new FIXdb(mContext);
		fixdb.readDB();
		//sync = 0;
		
	}
	
	public boolean checkUpdateDBNeeded()
	{
		return (navdb.calcDistance(lat, lon, reflat, reflon) > 50000);
	}
	
	public void updateDB()
	{
		//Calculate objects nearby
			reflat = lat;
			reflon = lon;
			
			navdb.calcQuickcoeff(lat);
			navdb.selectNear(lat, lon);
			
			
			
			fixdb.calcQuickcoeff(lat);			
			fixdb.selectNear(lat, lon);
	}
	
	public void draw(Canvas canvas) {
		
		Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        
        if (modebut == true) { //Circle
        	switch(mode) {
    			case 0:
    					drawCir(canvas,paint, 0); //APP
    					break;
    			case 1:
    					drawCir(canvas,paint, 1); //VOR
    					break;
    			case 2:
    					drawCir(canvas,paint, 2); //MAP
    					break;
    			case 3:
    					drawPln(canvas,paint);
    					break;
    			default:
    					break;
        	}
        } else { //Arc
        	switch(mode) {
        		case 0:
        				drawArc(canvas,paint, 0); //APP
        				break;
        		case 1:
        				drawArc(canvas,paint, 1); //VOR
        				break;
        		case 2:
        				drawArc(canvas,paint, 2); //MAP
        				break;
        		case 3:
        				drawPln(canvas,paint);
        				break;
        		default:
        				break;
        	}
        }
        
		//drawHsiArc(canvas, paint);
	
	}
	
	public void drawCir(Canvas canvas, Paint paint, int curmode)
	{
		int offsety = 25; // y offset of the center of the arc 
		
		//Prepare the mask
		maskfullMatrix.reset();
		maskfullMatrix.postTranslate(-maskfull.getWidth()/2, -maskfull.getHeight()/2 );
		maskfullMatrix.postScale(scaleFactor, scaleFactor);
		maskfullMatrix.postTranslate(centerx, centery);
		
		//prepare hsi
		hsiMatrix.reset();
		hsiMatrix.postTranslate(-hsi.getWidth()/2, -hsi.getHeight()/2 );
		hsiMatrix.postRotate(-heading);
		hsiMatrix.postScale((float)(scaleFactor*.85),(float)(scaleFactor*.85));
		hsiMatrix.postTranslate(centerx, centery + (float)(offsety*scaleFactor));
		        
		//draw hsiarc
		canvas.drawBitmap(hsi,hsiMatrix,paint);
		
		//Draw Concentric Circles
		//drawCircles(canvas,paint);
		
		//Draw NAV objects
		if (shownav)
			drawNAVobjects(canvas,paint,offsety);
			
		if (showfix)
			drawFixobjects(canvas,paint,offsety);
		
		//Draw Route wp
		if (showroute)
			drawRoute(canvas,paint,offsety);
		
		//draw mask
		canvas.drawBitmap(maskfull,maskfullMatrix,paint);
				
		//Heading text
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);	
		paint.setTextSize(24*scaleFactor);
		paint.setStrokeWidth((int)(1*scaleFactor));
		paint.setTextAlign(Align.CENTER);
				
		if (heading < 10) {
			canvas.drawText(String.format("00%d", (int)heading), centerx, centery - (int)(180*scaleFactor), paint);
		} else if (heading < 100) {
			canvas.drawText(String.format("0%d", (int)heading), centerx, centery - (int)(180*scaleFactor), paint);
		} else {
			canvas.drawText(String.format("%d", (int)heading), centerx, centery - (int)(180*scaleFactor), paint);
		}
		
		//paint.setTextSize(18*scaleFactor);
		//canvas.drawText(String.format("%d",(range/2)), centerx - (float)(10*scaleFactor), centery - (float)(20*scaleFactor), paint);
		
		//paint.setColor(Color.BLACK);
		//canvas.drawRect((centerx - 254*scaleFactor), (centery + offsety*scaleFactor), (centerx + 254*scaleFactor), 2*centery, paint);
		
		
		paint.setColor(Color.GREEN);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		//canvas.drawText("TRK", centerx - (int)(50*scaleFactor), centery - (int)(180*scaleFactor), paint);
		canvas.drawText("MAG", centerx + (int)(55*scaleFactor), centery - (int)(180*scaleFactor), paint);
		
		
		//Draw Radial and GS if required
		if (curmode == 0) {
			canvas.drawText("HDG", centerx - (int)(50*scaleFactor), centery - (int)(180*scaleFactor), paint);
			drawRadialcirc(canvas,paint);
			drawGScirc(canvas,paint);	
			
		}
		
		if (curmode == 1) {
			canvas.drawText("HDG", centerx - (int)(50*scaleFactor), centery - (int)(180*scaleFactor), paint);
			drawRadialcirc(canvas,paint);
			drawAPheadingCir(canvas, paint);
		}
		
		if (curmode == 2) {
			canvas.drawText("TRK", centerx - (int)(50*scaleFactor), centery - (int)(180*scaleFactor), paint);
			drawAPheadingCir(canvas, paint);
		}
		//Draw VORL
		
		if (switchvorl == 1) {
						
			
			drawVORL(canvas, paint, curmode);
			
		} else if (switchvorl == -1) {
			drawADFL(canvas, paint,curmode);
		}
		
		if (switchvorr == 1) {
			drawVORR(canvas, paint, curmode);
		} else if (switchvorr == -1) {
			drawADFR(canvas, paint,curmode);
		}
			
		//prepare and draw center of circle (Triangle)
		if (curmode == 2) { // draw triangle
			triangleMatrix.reset();
			triangleMatrix.postTranslate(-triangle.getWidth()/2, -triangle.getHeight()/2 );
			triangleMatrix.postScale((float)(0.5*scaleFactor),(float)(0.5*scaleFactor));
			triangleMatrix.postTranslate(centerx, centery + (float)(offsety*scaleFactor));
				
			canvas.drawBitmap(triangle, triangleMatrix, paint);
		} else { // draw center of VOR
			circenterMatrix.reset();
			circenterMatrix.postTranslate(-circenter.getWidth()/2, -circenter.getHeight()/2 );
			circenterMatrix.postScale((float)(0.75*scaleFactor),(float)(0.75*scaleFactor));
			circenterMatrix.postTranslate(centerx, centery + (float)(offsety*scaleFactor));
			
			canvas.drawBitmap(circenter, circenterMatrix, paint);
		}
		
	    
		
		
		//Draw vertical line
		
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth((2*scaleFactor));
		
		canvas.drawLine(centerx, centery + (float)((offsety - 100)*scaleFactor) , centerx, centery + (float)((offsety - 170)*scaleFactor), paint);
		canvas.drawLine(centerx, centery + (float)((offsety + 100)*scaleFactor) , centerx, centery + (float)((offsety + 170)*scaleFactor), paint);
		
		canvas.drawLine(centerx - (float)(15*scaleFactor),
				centery + (float)((offsety - 120)*scaleFactor),
				centerx + (float)(15*scaleFactor), centery + (float)((offsety - 120)*scaleFactor), paint);
		
		canvas.drawLine(centerx - (float)(15*scaleFactor),
				centery + (float)((offsety + 120)*scaleFactor),
				centerx + (float)(15*scaleFactor), centery + (float)((offsety + 120)*scaleFactor), paint);
		
		
		//Draw true speed text 
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
			
		if (truespeed > 100) { 
			canvas.drawText(String.format("TAS %d", (int)truespeed), centerx - (int)(130*scaleFactor), centery - (int)(230*scaleFactor), paint);
		}
		
		canvas.drawText(String.format("GS %d", (int)groundspeed), centerx - (int)(200*scaleFactor), centery - (int)(230*scaleFactor), paint);
		
		//Draw wind speed and heading
		drawWind(canvas, paint);
		
		//Waypoint name
		drawWPtext(canvas, paint);
		
		//Hide borders
		//paint.setColor(Color.rgb(0xb6, 0xb2, 0xa7));
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);		
		//canvas.drawRect((centerx - 380*scaleFactor), 0, (centerx - 256*scaleFactor), 2*centery, paint);
		//canvas.drawRect((centerx + 256*scaleFactor), 0, (centerx + 380*scaleFactor), 2*centery, paint);
		
		canvas.drawRect(0, 0, (centerx - 256*scaleFactor), 2*centery, paint);
		canvas.drawRect((centerx + 256*scaleFactor), 0, 2*centerx, 2*centery, paint);
	}
	
	public void drawArc(Canvas canvas, Paint paint, int curmode)
	{
		int offsety = 160; // y offset of the center of the arc 
		
		//Prepare the mask
		maskMatrix.reset();
		maskMatrix.postTranslate(-mask.getWidth()/2, -mask.getHeight()/2 );
		maskMatrix.postScale(scaleFactor, scaleFactor);
		maskMatrix.postTranslate(centerx, centery);
		
		//prepare hsiarc
		hsiarcMatrix.reset();
		hsiarcMatrix.postTranslate(-hsiarc.getWidth()/2, -hsiarc.getHeight()/2 );
		hsiarcMatrix.postRotate(-heading);
		hsiarcMatrix.postScale((float)(scaleFactor*1.5),(float)(scaleFactor*1.5));
		hsiarcMatrix.postTranslate(centerx, centery + (float)(offsety*scaleFactor));
		        
		//draw hsiarc
		canvas.drawBitmap(hsiarc,hsiarcMatrix,paint);
		
		//Draw NAV objects
		if (shownav)
			drawNAVobjects(canvas,paint,offsety);
		
		if (showfix)
			drawFixobjects(canvas,paint,offsety);
				
		//Draw Route wp
		if (showroute)
			drawRoute(canvas,paint,offsety);
		
		//Draw Concentric Circles
		if (showcir)
			drawCircles(canvas,paint);
				
		//draw mask
		canvas.drawBitmap(mask,maskMatrix,paint);
				
		//Heading text
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);	
		paint.setTextSize(24*scaleFactor);
		paint.setStrokeWidth((int)(1*scaleFactor));
		paint.setTextAlign(Align.CENTER);
				
		if (heading < 10) {
			canvas.drawText(String.format("00%d", (int)heading), centerx, centery - (int)(210*scaleFactor), paint);
		} else if (heading < 100) {
			canvas.drawText(String.format("0%d", (int)heading), centerx, centery - (int)(210*scaleFactor), paint);
		} else {
			canvas.drawText(String.format("%d", (int)heading), centerx, centery - (int)(210*scaleFactor), paint);
		}
		
		// Draw range in the middle circle
		paint.setTextSize(18*scaleFactor);
		canvas.drawText(String.format("%d",(range/2)), centerx - (float)(10*scaleFactor), centery - (float)(20*scaleFactor), paint);
		
		paint.setColor(Color.BLACK);
		canvas.drawRect((centerx - 254*scaleFactor), (centery + offsety*scaleFactor), (centerx + 254*scaleFactor), 2*centery, paint);
		
		
		paint.setColor(Color.GREEN);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		//canvas.drawText("TRK", centerx - (int)(50*scaleFactor), centery - (int)(210*scaleFactor), paint);
		canvas.drawText("MAG", centerx + (int)(55*scaleFactor), centery - (int)(210*scaleFactor), paint);
		
		//Draw Radial and GS if required
		

		//Draw Radial and GS if required
		if (curmode == 0) {
			canvas.drawText("HDG", centerx - (int)(50*scaleFactor), centery - (int)(210*scaleFactor), paint);
			drawRadialARC(canvas,paint);
			drawGSArc(canvas,paint);	
			
		}
		
		if (curmode == 1) {
			canvas.drawText("HDG", centerx - (int)(50*scaleFactor), centery - (int)(210*scaleFactor), paint);
			drawRadialARC(canvas,paint);
			drawAPheading(canvas, paint);
		}
		
		if (curmode == 2) {
			canvas.drawText("TRK", centerx - (int)(50*scaleFactor), centery - (int)(210*scaleFactor), paint);
			drawAPheading(canvas, paint);
		}
		
		//Draw VORL
		
		if (switchvorl == 1) {
						
			
			drawVORL(canvas, paint, curmode);
			
		} else if (switchvorl == -1) {
			drawADFL(canvas, paint,curmode);
		}
		
		if (switchvorr == 1) {
			drawVORR(canvas, paint, curmode);
		} else if (switchvorr == -1) {
			drawADFR(canvas, paint, curmode);
		}
			
		//prepare and draw Triangle
	    triangleMatrix.reset();
		triangleMatrix.postTranslate(-triangle.getWidth()/2, -triangle.getHeight()/2 );
		triangleMatrix.postScale((float)(0.5*scaleFactor),(float)(0.5*scaleFactor));
		triangleMatrix.postTranslate(centerx, centery + (float)(offsety*scaleFactor));
		
		canvas.drawBitmap(triangle, triangleMatrix, paint);
		
		//Draw vertical line
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth((2*scaleFactor));
		
		canvas.drawLine(centerx, centery + (float)((offsety - 95*0.9)*scaleFactor) , centerx, centery - (float)(185*scaleFactor), paint);
		canvas.drawLine(centerx - (float)(10*scaleFactor),
				centery + (float)((offsety - 95*0.9)*scaleFactor),
				centerx + (float)(10*scaleFactor), centery + (float)((offsety - 95*0.9)*scaleFactor), paint);
		canvas.drawLine(centerx - (float)(10*scaleFactor),
				centery + (float)((offsety - 190*0.9)*scaleFactor),
				centerx + (float)(10*scaleFactor), centery + (float)((offsety - 190*0.9)*scaleFactor), paint);
		canvas.drawLine(centerx - (float)(10*scaleFactor),
				centery + (float)((offsety - 285*0.9)*scaleFactor),
				centerx + (float)(10*scaleFactor), centery + (float)((offsety - 285*0.9)*scaleFactor), paint);
	
		
		//Draw true speed text 
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
			
		if (truespeed > 100) { 
			canvas.drawText(String.format("TAS %d", (int)truespeed), centerx - (int)(130*scaleFactor), centery - (int)(230*scaleFactor), paint);
		}
		
		canvas.drawText(String.format("GS %d", (int)groundspeed), centerx - (int)(200*scaleFactor), centery - (int)(230*scaleFactor), paint);
		
		//Draw wind speed and heading
		drawWind(canvas, paint);
		
		//Waypoint name
		drawWPtext(canvas, paint);
		
		//Hide borders
		//paint.setColor(Color.rgb(0xb6, 0xb2, 0xa7));
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);		
		//canvas.drawRect((centerx - 380*scaleFactor), 0, (centerx - 256*scaleFactor), 2*centery, paint);
		//canvas.drawRect((centerx + 256*scaleFactor), 0, (centerx + 380*scaleFactor), 2*centery, paint);
		canvas.drawRect(0, 0, (centerx - 256*scaleFactor), 2*centery, paint);
		canvas.drawRect((centerx + 256*scaleFactor), 0, 2*centerx, 2*centery, paint);
		
	}
	
	public void drawWind(Canvas canvas, Paint paint)
	{
		//float rotation = heading - (float)(apheading);
		
		float rotation = realheading;
		
		String aux;
				
		if (windspeed < 10) {
			aux = String.format("0%d",(int)windspeed);
		} else {
			aux = String.format("%d",(int)windspeed);
		}
		
		if (windheading < 10) {
			canvas.drawText(String.format(" 00%d / ", (int)windheading)+aux, centerx - (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		} else if (windheading < 100) {
			canvas.drawText(String.format(" 0%d / ", (int)windheading)+aux, centerx - (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		} else {
			canvas.drawText(String.format(" %d / ", (int)windheading)+aux, centerx - (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		}
		
		//prepare and draw Triangle
	    windMatrix.reset();
		windMatrix.postTranslate(-wind.getWidth()/2, -wind.getHeight()/2 );
		windMatrix.postScale((float)(0.25*scaleFactor),(float)(0.25*scaleFactor));
		windMatrix.postRotate(-rotation + windheading);
		windMatrix.postTranslate(centerx - (int)(200*scaleFactor), centery - (float)(190*scaleFactor));
		
		canvas.drawBitmap(wind, windMatrix, paint);
		
		
	}
		
	
	public void drawPln(Canvas canvas, Paint paint)
	{
		
	}
	
	
	
	void drawAPheading(Canvas canvas, Paint paint)
	{
		int offsety = 160; // y offset of the center of the arc
		//AP Heading indicator
		float rotation = heading - (float)(apheading);
						
		apheadMatrix.reset();
		apheadMatrix.postTranslate(-aphead.getWidth()/2, -aphead.getHeight()/2);
		apheadMatrix.postScale(scaleFactor, scaleFactor);
		apheadMatrix.postTranslate(0, -(355*scaleFactor));
		apheadMatrix.postRotate(-rotation);
				
		apheadMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
								
		canvas.drawBitmap(aphead, apheadMatrix, paint);
		
		//Calculate x,y coordinates for the line
		float x = -(float)(scaleFactor*350*Math.cos((90-rotation)/180*Math.PI))+centerx;
		float y = -(float)(scaleFactor*350*Math.sin((90-rotation)/180*Math.PI))+centery + (float)(offsety*scaleFactor);
		
		paint.setColor(Color.MAGENTA);
		paint.setStrokeWidth((2*scaleFactor));
		paint.setPathEffect(new DashPathEffect(new float[] {(12*scaleFactor),(15*scaleFactor)}, 0));
		canvas.drawLine(centerx, centery + (float)(offsety*scaleFactor), x, y , paint);
		
		paint.setPathEffect(null);		
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
	}
	
	void drawAPheadingCir(Canvas canvas, Paint paint)
	{
		int offsety = 25; // y offset of the center of the arc
		//AP Heading indicator
		float rotation = heading - (float)(apheading);
						
		apheadMatrix.reset();
		apheadMatrix.postTranslate(-aphead.getWidth()/2, -aphead.getHeight()/2);
		apheadMatrix.postScale(scaleFactor, scaleFactor);
		apheadMatrix.postTranslate(0, -(190*scaleFactor));
		apheadMatrix.postRotate(-rotation);
				
		apheadMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
								
		canvas.drawBitmap(aphead, apheadMatrix, paint);
		
		//Calculate x,y coordinates for the line
		float x = -(float)(scaleFactor*180*Math.cos((90-rotation)/180*Math.PI))+centerx;
		float y = -(float)(scaleFactor*180*Math.sin((90-rotation)/180*Math.PI))+centery + (float)(offsety*scaleFactor);
		
		paint.setColor(Color.MAGENTA);
		paint.setStrokeWidth((2*scaleFactor));
		paint.setPathEffect(new DashPathEffect(new float[] {(12*scaleFactor),(15*scaleFactor)}, 0));
		canvas.drawLine(centerx, centery + (float)(offsety*scaleFactor), x, y , paint);
		
		paint.setPathEffect(null);		
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
	}
	
	void drawVORL(Canvas canvas, Paint paint, int curmode) 
	{
		int offsety = 160; // y offset of the center of the arc
		float dme = (float)(vorldme/1852.);
		
		paint.setColor(Color.GREEN);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		
		canvas.drawText("VOR L", centerx - (float)(200*scaleFactor), centery + (float)((offsety + 30)*scaleFactor), paint);
		
		if ((vorldmeinrange == true) && (vorlinrange == true)) { 
			canvas.drawText(vorlid, centerx - (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);
			canvas.drawText(String.format("DME %3.1f", dme), centerx - (float)(200*scaleFactor), centery + (float)((offsety + 70)*scaleFactor), paint);
		} else {
			if (vorlid.equals("")) {
				canvas.drawText(vorlid, centerx - (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);
			} else {
				canvas.drawText(String.format("%4.2f",vorlfreq), centerx - (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);
			}
			canvas.drawText("DME - - -", centerx - (float)(200*scaleFactor), centery + (float)((offsety + 70)*scaleFactor), paint);
		}
		
		if (modebut == false) { //arc
			if (curmode != 0) { 
				//Draw Arrows
				vorlupMatrix.reset();
				vorlupMatrix.postTranslate(-vorlup.getWidth()/2, -vorlup.getHeight()/2);
				vorlupMatrix.postScale((float)(0.7*scaleFactor), (float)(0.7*scaleFactor));
				vorlupMatrix.postTranslate(0, -(335*scaleFactor));
				vorlupMatrix.postRotate(vorldirection);
				vorlupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
		
				canvas.drawBitmap(vorlup, vorlupMatrix, paint);
		
				//Draw Arrows
				vorlbackMatrix.reset();
				vorlbackMatrix.postTranslate(-vorlback.getWidth()/2, -vorlback.getHeight()/2);
				vorlbackMatrix.postScale((float)(0.7*scaleFactor), (float)(0.7*scaleFactor));
				vorlbackMatrix.postTranslate(0, + (335*scaleFactor));
				vorlbackMatrix.postRotate(vorldirection);
				vorlbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
				
				canvas.drawBitmap(vorlback, vorlbackMatrix, paint);
			}
		} else { //circle
			offsety = 25;
			if (curmode != 0) { 
				
				//Draw Arrows
				vorlupMatrix.reset();
				vorlupMatrix.postTranslate(-vorlup.getWidth()/2, -vorlup.getHeight()/2);
				vorlupMatrix.postScale((float)(0.6*scaleFactor), (float)(0.6*scaleFactor));
				vorlupMatrix.postTranslate(0, -(150*scaleFactor));
				vorlupMatrix.postRotate(vorldirection);
				vorlupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
		
				canvas.drawBitmap(vorlup, vorlupMatrix, paint);
		
				//Draw Arrows
				vorlbackMatrix.reset();
				vorlbackMatrix.postTranslate(-vorlback.getWidth()/2, -vorlback.getHeight()/2);
				vorlbackMatrix.postScale((float)(0.6*scaleFactor), (float)(0.6*scaleFactor));
				vorlbackMatrix.postTranslate(0, + (150*scaleFactor));
				vorlbackMatrix.postRotate(vorldirection);
				vorlbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
				
				canvas.drawBitmap(vorlback, vorlbackMatrix, paint);
			}
			
		}
	}
	
	void drawVORR(Canvas canvas, Paint paint, int curmode) 
	{
		int offsety = 160; // y offset of the center of the arc
		float dme = (float)(vorrdme/1852.);
		
		paint.setColor(Color.GREEN);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		
		canvas.drawText("VOR R", centerx + (float)(200*scaleFactor), centery + (float)((offsety + 30)*scaleFactor), paint);
		
		//if ((vorrdmeinrange == true) && (vorrinrange == true)) { 
		if ((vorrinrange == true)) {
			canvas.drawText(vorrid, centerx + (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);
			canvas.drawText(String.format("DME %3.1f", dme), centerx + (float)(200*scaleFactor), centery + (float)((offsety + 70)*scaleFactor), paint);
		} else {
			if (vorrid.equals("")) {
				canvas.drawText(vorrid, centerx + (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);
			} else {
				canvas.drawText(String.format("%4.2f",vorrfreq), centerx + (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);
			}
			canvas.drawText("DME - - -", centerx + (float)(200*scaleFactor), centery + (float)((offsety + 70)*scaleFactor), paint);
		}
		
		if (modebut == false) { //arc
					
			if (curmode != 0) {
				//Draw Arrows
				vorrupMatrix.reset();
				vorrupMatrix.postTranslate(-vorrup.getWidth()/2, -vorrup.getHeight()/2);
				vorrupMatrix.postScale((float)(0.4*scaleFactor), (float)(0.6*scaleFactor));
				vorrupMatrix.postTranslate(0, -(335*scaleFactor));
				vorrupMatrix.postRotate(vorrdirection);
				vorrupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
		
				canvas.drawBitmap(vorrup, vorrupMatrix, paint);
		
				//Draw Arrows
				vorrbackMatrix.reset();
				vorrbackMatrix.postTranslate(-vorrback.getWidth()/2, -vorrback.getHeight()/2);
				vorrbackMatrix.postScale((float)(0.4*scaleFactor), (float)(0.6*scaleFactor));
				vorrbackMatrix.postTranslate(0, + (335*scaleFactor));
				vorrbackMatrix.postRotate(vorrdirection);
				vorrbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
				
				canvas.drawBitmap(vorrback, vorrbackMatrix, paint);
			}
		} else { //circ
			offsety = 25;
			if (curmode != 0) {
				//Draw Arrows
				vorrupMatrix.reset();
				vorrupMatrix.postTranslate(-vorrup.getWidth()/2, -vorrup.getHeight()/2);
				vorrupMatrix.postScale((float)(0.4*scaleFactor), (float)(0.5*scaleFactor));
				vorrupMatrix.postTranslate(0, -(150*scaleFactor));
				vorrupMatrix.postRotate(vorrdirection);
				vorrupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
		
				canvas.drawBitmap(vorrup, vorrupMatrix, paint);
		
				//Draw Arrows
				vorrbackMatrix.reset();
				vorrbackMatrix.postTranslate(-vorrback.getWidth()/2, -vorrback.getHeight()/2);
				vorrbackMatrix.postScale((float)(0.4*scaleFactor), (float)(0.5*scaleFactor));
				vorrbackMatrix.postTranslate(0, + (150*scaleFactor));
				vorrbackMatrix.postRotate(vorrdirection);
				vorrbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
				
				canvas.drawBitmap(vorrback, vorrbackMatrix, paint);
			}
		}
	}
	
	void drawADFL(Canvas canvas, Paint paint, int curmode) {
		int offsety = 160; // y offset of the center of the arc

		paint.setColor(Color.CYAN);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));

		canvas.drawText("ADF  L", centerx - (float)(200*scaleFactor), centery + (float)((offsety + 30)*scaleFactor), paint);
		
		if (adflinrange == true) { 
			canvas.drawText(adflid, centerx - (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);		
		} else {		
			canvas.drawText(String.format("%d",adflfreq), centerx - (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);
		}
		
		if (curmode == 0) return;
		
		if (adflinrange == true) {
			
			if (modebut == false) { //arc
			
				//Draw Arrows
				adflupMatrix.reset();
				adflupMatrix.postTranslate(-adflup.getWidth()/2, -adflup.getHeight()/2);
				adflupMatrix.postScale((float)(0.7*scaleFactor), (float)(0.7*scaleFactor));
				adflupMatrix.postTranslate(0, -(335*scaleFactor));
				adflupMatrix.postRotate(adfldirection);
				adflupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
				
				canvas.drawBitmap(adflup, adflupMatrix, paint);
				
				//Draw Arrows
				adflbackMatrix.reset();
				adflbackMatrix.postTranslate(-adflback.getWidth()/2, -adflback.getHeight()/2);
				adflbackMatrix.postScale((float)(0.7*scaleFactor), (float)(0.7*scaleFactor));
				adflbackMatrix.postTranslate(0, + (335*scaleFactor));
				adflbackMatrix.postRotate(adfldirection);
				adflbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
						
				canvas.drawBitmap(adflback, adflbackMatrix, paint);
			
			} else {
				offsety = 25;
				//Draw Arrows
				adflupMatrix.reset();
				adflupMatrix.postTranslate(-adflup.getWidth()/2, -adflup.getHeight()/2);
				adflupMatrix.postScale((float)(0.6*scaleFactor), (float)(0.6*scaleFactor));
				adflupMatrix.postTranslate(0, -(150*scaleFactor));
				adflupMatrix.postRotate(adfldirection);
				adflupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
				
				canvas.drawBitmap(adflup, adflupMatrix, paint);
				
				//Draw Arrows
				adflbackMatrix.reset();
				adflbackMatrix.postTranslate(-adflback.getWidth()/2, -adflback.getHeight()/2);
				adflbackMatrix.postScale((float)(0.6*scaleFactor), (float)(0.6*scaleFactor));
				adflbackMatrix.postTranslate(0, + (150*scaleFactor));
				adflbackMatrix.postRotate(adfldirection);
				adflbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
						
				canvas.drawBitmap(adflback, adflbackMatrix, paint);
			}
		}
	} 
	
	void drawADFR(Canvas canvas, Paint paint, int curmode) {
		int offsety = 160; // y offset of the center of the arc

		paint.setColor(Color.CYAN);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));

		canvas.drawText("ADF  R", centerx + (float)(200*scaleFactor), centery + (float)((offsety + 30)*scaleFactor), paint);
		
		if (adfrinrange == true) { 
			canvas.drawText(adfrid, centerx + (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);		
		} else {		
			canvas.drawText(String.format("%d",adfrfreq), centerx + (float)(200*scaleFactor), centery + (float)((offsety + 50)*scaleFactor), paint);
		}
		
		if (curmode == 0) return;
		
		if (adfrinrange == true) { 
			if (modebut == false) { //arc
				//Draw Arrows
				adfrupMatrix.reset();
				adfrupMatrix.postTranslate(-adfrup.getWidth()/2, -adfrup.getHeight()/2);
				adfrupMatrix.postScale((float)(0.4*scaleFactor), (float)(0.6*scaleFactor));
				adfrupMatrix.postTranslate(0, -(335*scaleFactor));
				adfrupMatrix.postRotate(adfrdirection);
				adfrupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
						
				canvas.drawBitmap(adfrup, adfrupMatrix, paint);
						
				//Draw Arrows
				adfrbackMatrix.reset();
				adfrbackMatrix.postTranslate(-adfrback.getWidth()/2, -adfrback.getHeight()/2);
				adfrbackMatrix.postScale((float)(0.4*scaleFactor), (float)(0.6*scaleFactor));
				adfrbackMatrix.postTranslate(0, + (335*scaleFactor));
				adfrbackMatrix.postRotate(adfrdirection);
				adfrbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
								
				canvas.drawBitmap(adfrback, adfrbackMatrix, paint);
			} else { //circ
				offsety = 25;
				//Draw Arrows
				adfrupMatrix.reset();
				adfrupMatrix.postTranslate(-adfrup.getWidth()/2, -adfrup.getHeight()/2);
				adfrupMatrix.postScale((float)(0.4*scaleFactor), (float)(0.5*scaleFactor));
				adfrupMatrix.postTranslate(0, -(150*scaleFactor));
				adfrupMatrix.postRotate(adfrdirection);
				adfrupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
						
				canvas.drawBitmap(adfrup, adfrupMatrix, paint);
						
				//Draw Arrows
				adfrbackMatrix.reset();
				adfrbackMatrix.postTranslate(-adfrback.getWidth()/2, -adfrback.getHeight()/2);
				adfrbackMatrix.postScale((float)(0.4*scaleFactor), (float)(0.5*scaleFactor));
				adfrbackMatrix.postTranslate(0, + (150*scaleFactor));
				adfrbackMatrix.postRotate(adfrdirection);
				adfrbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
								
				canvas.drawBitmap(adfrback, adfrbackMatrix, paint);
			}
		}
				
	}
	
	void drawRadialARC(Canvas canvas, Paint paint)
	{
		int offsety = 160; // y offset of the center of the arc
		
		//float rotation = realheading - (float)(radial);
		float rotation = heading - (float)(radial);
				
		// Draw radial arrows
		radialupMatrix.reset();
		radialupMatrix.postTranslate(-radialup.getWidth()/2, -radialup.getHeight()/2);
		radialupMatrix.postScale((float)(0.6*scaleFactor), (float)(0.6*scaleFactor));
		radialupMatrix.postTranslate(0, -(195*scaleFactor));
		radialupMatrix.postRotate(-rotation);
				
		radialupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
								
		canvas.drawBitmap(radialup, radialupMatrix, paint);
		
		radialbackMatrix.reset();
		radialbackMatrix.postTranslate(-radialback.getWidth()/2, -radialback.getHeight()/2);
		radialbackMatrix.postScale((float)(0.6*scaleFactor), (float)(0.6*scaleFactor));
		radialbackMatrix.postTranslate(0, +(195*scaleFactor));
		radialbackMatrix.postRotate(-rotation);
				
		radialbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
								
		canvas.drawBitmap(radialback, radialbackMatrix, paint);
		
		//Draw deflection
		defnidMatrix.reset();
		defnidMatrix.postTranslate(-defnid.getWidth()/2, -defnid.getHeight()/2);
		defnidMatrix.postScale((float)(0.4*scaleFactor), (float)(0.75*scaleFactor));
		defnidMatrix.postTranslate((float)(radialdef*65*scaleFactor), 0);
		//float defa =  10;
		//defnidMatrix.postTranslate(defa, 0);
		defnidMatrix.postRotate(-rotation);
						
		defnidMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
					
		paint.setColor(Color.WHITE);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		
		if (vorlinrange == true) {
			canvas.drawBitmap(defnid, defnidMatrix, paint);			
		}
		
		if ((vorldmeinrange == true) && (vorlinrange == true)) {			
			float dme = (float)(vorldme/1852.);	
			canvas.drawText(String.format("DME %3.1f", dme), centerx + (float)(200*scaleFactor), centery - (int)((190)*scaleFactor), paint);
		}
		
		//Draw horizontal deflection circles
		defhorMatrix.reset();
		defhorMatrix.postTranslate(-defhor.getWidth()/2, -defhor.getHeight()/2);
		defhorMatrix.postScale((float)(0.8*scaleFactor), (float)(0.8*scaleFactor));
		//defhorMatrix.postTranslate(0, -(195*scaleFactor));
		defhorMatrix.postRotate(-rotation);
				
		defhorMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
		
		canvas.drawBitmap(defhor, defhorMatrix, paint);
				
		//Draw CRS text
				
		if (radial < 10) {
			canvas.drawText(String.format("CRS 00%d", (int)radial), centerx + (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		} else if (radial < 100) {
			canvas.drawText(String.format("CRS 0%d", (int)radial), centerx + (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		} else {
			canvas.drawText(String.format("CRS %d", (int)radial), centerx + (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		}
					
	}
	
	void drawRadialcirc(Canvas canvas, Paint paint)
	{
		int offsety = 25; // y offset of the center of the arc
		
		//float rotation = realheading - (float)(radial);
		float rotation = heading - (float)(radial);
				
		// Draw radial arrows
		cirupMatrix.reset();
		cirupMatrix.postTranslate(-cirup.getWidth()/2, -cirup.getHeight()/2);
		cirupMatrix.postScale((float)(0.6*scaleFactor), (float)(0.5*scaleFactor));
		cirupMatrix.postTranslate(0, -(110*scaleFactor));
		cirupMatrix.postRotate(-rotation);
				
		cirupMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
								
		canvas.drawBitmap(cirup, cirupMatrix, paint);
		
		cirbackMatrix.reset();
		cirbackMatrix.postTranslate(-cirback.getWidth()/2, -cirback.getHeight()/2);
		cirbackMatrix.postScale((float)(0.6*scaleFactor), (float)(0.5*scaleFactor));
		cirbackMatrix.postTranslate(0, +(110*scaleFactor));
		cirbackMatrix.postRotate(-rotation);
				
		cirbackMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
								
		canvas.drawBitmap(cirback, cirbackMatrix, paint);
		
		//Draw deflection
		defnidMatrix.reset();
		defnidMatrix.postTranslate(-defnid.getWidth()/2, -defnid.getHeight()/2);
		defnidMatrix.postScale((float)(0.4*scaleFactor), (float)(0.9*scaleFactor));
		defnidMatrix.postTranslate((float)(radialdef*81.25*scaleFactor), 0);
		defnidMatrix.postRotate(-rotation);
						
		defnidMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
					
		paint.setColor(Color.WHITE);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		
		if (vorlinrange == true) {
			canvas.drawBitmap(defnid, defnidMatrix, paint);			
		}
		
		if ((vorldmeinrange == true) && (vorlinrange == true)) {			
			float dme = (float)(vorldme/1852.);	
			canvas.drawText(String.format("DME %3.1f", dme), centerx + (float)(200*scaleFactor), centery - (int)((190)*scaleFactor), paint);
		}
						
		//Draw horizontal deflection circles
		defhorMatrix.reset();
		defhorMatrix.postTranslate(-defhor.getWidth()/2, -defhor.getHeight()/2);
		defhorMatrix.postScale((float)(1*scaleFactor), (float)(1*scaleFactor));
		//defhorMatrix.postTranslate(0, -(195*scaleFactor));
		defhorMatrix.postRotate(-rotation);
				
		defhorMatrix.postTranslate(centerx,centery + (float)(offsety*scaleFactor));
		
		canvas.drawBitmap(defhor, defhorMatrix, paint);
				
		//Draw CRS text
				
		if (radial < 10) {
			canvas.drawText(String.format("CRS 00%d", (int)radial), centerx + (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		} else if (radial < 100) {
			canvas.drawText(String.format("CRS 0%d", (int)radial), centerx + (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		} else {
			canvas.drawText(String.format("CRS %d", (int)radial), centerx + (int)(200*scaleFactor), centery - (int)(210*scaleFactor), paint);
		}
					
	}
	
	
	void drawGSArc(Canvas canvas, Paint paint)
	{
		int offsety = 160; // y offset of the center of the arc
		
		float vercenter = offsety - defver.getHeight()/2;
		defverMatrix.reset();
		defverMatrix.postTranslate(-defver.getWidth()/2, -defver.getHeight()/2);
		defverMatrix.postScale((float)(0.8*scaleFactor), (float)(0.8*scaleFactor));
		//defhorMatrix.postTranslate(0, -(195*scaleFactor));
		//defhorMatrix.postRotate(-rotation);				
		defverMatrix.postTranslate(centerx + (int)(230*scaleFactor),centery + (float)((offsety-65)*scaleFactor));
		
		canvas.drawBitmap(defver, defverMatrix, paint);
		
		bugMatrix.reset();
		bugMatrix.postTranslate(-bug.getWidth()/2, -bug.getHeight()/2);
		bugMatrix.postScale((float)(0.3*scaleFactor), (float)(0.3*scaleFactor));
		bugMatrix.postRotate(90);
		bugMatrix.postTranslate(centerx + (int)(230*scaleFactor),centery + (float)((offsety-65 - 65*gsdef)*scaleFactor));
		
		canvas.drawBitmap(bug, bugMatrix, paint);
		
		//Draw ILS text 
		paint.setColor(Color.WHITE);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		
		if (vorlid.equals("")) {
			canvas.drawText(String.format("ILS %4.2f",vorlfreq), centerx + (int)(200*scaleFactor), centery - (int)(230*scaleFactor), paint);
		} else {
			canvas.drawText("ILS "+ vorlid, centerx + (int)(200*scaleFactor), centery - (int)(230*scaleFactor), paint);
		}
		
	}
	
	void drawGScirc(Canvas canvas, Paint paint)
	{
		int offsety = 25; // y offset of the center of the arc
		
		float vercenter = offsety - defver.getHeight()/2;
		defverMatrix.reset();
		defverMatrix.postTranslate(-defver.getWidth()/2, -defver.getHeight()/2);
		defverMatrix.postScale((float)(0.8*scaleFactor), (float)(0.8*scaleFactor));
		//defhorMatrix.postTranslate(0, -(195*scaleFactor));
		//defhorMatrix.postRotate(-rotation);				
		defverMatrix.postTranslate(centerx + (int)(230*scaleFactor),centery + (float)((offsety)*scaleFactor));
		
		canvas.drawBitmap(defver, defverMatrix, paint);
		
		bugMatrix.reset();
		bugMatrix.postTranslate(-bug.getWidth()/2, -bug.getHeight()/2);
		bugMatrix.postScale((float)(0.3*scaleFactor), (float)(0.3*scaleFactor));
		bugMatrix.postRotate(90);
		bugMatrix.postTranslate(centerx + (int)(230*scaleFactor),centery + (float)((offsety - 65*gsdef)*scaleFactor));
		
		canvas.drawBitmap(bug, bugMatrix, paint);
		
		//Draw ILS text 
		paint.setColor(Color.WHITE);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		
		if (vorlid.equals("")) {
			canvas.drawText(String.format("ILS %4.2f",vorlfreq), centerx + (int)(200*scaleFactor), centery - (int)(230*scaleFactor), paint);
		} else {
			canvas.drawText("ILS "+ vorlid, centerx + (int)(200*scaleFactor), centery - (int)(230*scaleFactor), paint);
		}
		
	}
	
	void drawCircles(Canvas canvas, Paint paint)
	{
		int offsety = 160; // y offset of the center of the arc
		//draw concentric circles
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);		
		paint.setStrokeWidth((2*scaleFactor));
				
		//Draw Circles
		canvas.drawCircle(centerx, centery + (float)(offsety*scaleFactor), (float)(95*0.9*scaleFactor), paint);
		canvas.drawCircle(centerx, centery + (float)(offsety*scaleFactor), (float)(190*0.9*scaleFactor), paint);
		canvas.drawCircle(centerx, centery + (float)(offsety*scaleFactor), (float)(285*0.9*scaleFactor), paint);
	}
	
	void drawNAVobjects(Canvas canvas, Paint paint, int offsety)
	{			
			
		
		//paint.setColor(Color.CYAN);
		paint.setStyle(Style.STROKE);
		paint.setTextSize((float)(10*scaleFactor));
		paint.setStrokeWidth(0);
		
		
		float dist,distx, disty, angle;
		for (int i = 0; i<navdb.mnear; i++ ){
			dist = navdb.calcDistance(lat, lon, navdb.mlatitude[i], navdb.mlongitude[i]);
			distx = (float)navdb.distx;
			disty = (float)navdb.disty;
			
			distx = (distx/1852)*(324/range); //pixels
			disty = (disty/1852)*(324/range); //pixels
			dist = (dist/1852)*(324/range); //pixels
			
			angle = (float)Math.atan2(disty,distx);						
			angle = angle + (float)(realheading/180*Math.PI);
			
			distx = (float) (dist*Math.cos(angle));
			disty = (float)(dist*Math.sin(angle));
			
			//Airports
			if (navdb.mID[i] == 1) {
				paint.setColor(Color.GREEN);
				paint.setStrokeWidth((float)(1.5*scaleFactor));
				
				canvas.drawCircle(centerx + (float)distx*scaleFactor, 
						centery + (float)((offsety-disty)*scaleFactor),
						(float)(6*scaleFactor), paint);
				
				paint.setStrokeWidth(0);
				canvas.drawText(navdb.mname[i],
					centerx + (float)(distx + 7)*scaleFactor,
					centery + (float)((offsety-disty+10)*scaleFactor),
					paint);
			}
			
			
			//ADF
			if (navdb.mID[i] == 2) {
				paint.setColor(Color.CYAN);
				
				ndvorMatrix.reset();
				ndvorMatrix.postTranslate(-ndvor.getWidth()/2, -ndvor.getHeight()/2);
				ndvorMatrix.postScale((float)(0.5*scaleFactor), (float)(0.5*scaleFactor));
				ndvorMatrix.postTranslate(centerx + (float)distx*scaleFactor, centery + (float)((offsety-disty)*scaleFactor));
				
				canvas.drawBitmap(ndvor, ndvorMatrix, paint);
			
				canvas.drawText(navdb.mname[i],
					centerx + (float)(distx + 7)*scaleFactor,
					centery + (float)((offsety-disty+10)*scaleFactor),
					paint);
			}
			
			//Draw VOR			
			if (navdb.mID[i] == 3) {
				paint.setColor(Color.GREEN);
				ndvorMatrix.reset();
				ndvorMatrix.postTranslate(-ndvortac.getWidth()/2, -ndvortac.getHeight()/2);
				ndvorMatrix.postScale((float)(0.5*scaleFactor), (float)(0.5*scaleFactor));
				ndvorMatrix.postTranslate(centerx + (float)distx*scaleFactor, centery + (float)((offsety-disty)*scaleFactor));
				
				canvas.drawBitmap(ndvortac, ndvorMatrix, paint);
			
				canvas.drawText(navdb.mname[i],
					centerx + (float)(distx + 7)*scaleFactor,
					centery + (float)((offsety-disty+10)*scaleFactor),
					paint);
			}
		}
		
		
		
		
	}
	
	void drawFixobjects(Canvas canvas, Paint paint, int offsety){
		paint.setStyle(Style.STROKE);
		paint.setTextSize((float)(10*scaleFactor));
		paint.setStrokeWidth(0);
		
		
		float dist,distx, disty, angle;
		//Fix points
		paint.setColor(Color.CYAN);
				
		for (int i = 0; i<fixdb.mnear; i++ ){
			dist = fixdb.calcDistance(lat, lon, fixdb.mlatitude[i], fixdb.mlongitude[i]);
			distx = (float)fixdb.distx;
			disty = (float)fixdb.disty;
					
			distx = (distx/1852)*(324/range); //pixels
			disty = (disty/1852)*(324/range); //pixels
			dist = (dist/1852)*(324/range); //pixels
					
			angle = (float)Math.atan2(disty,distx);						
			//angle = angle + (float)(heading/180*Math.PI);
			angle = angle + (float)(realheading/180*Math.PI);
					
			distx = (float) (dist*Math.cos(angle));
			disty = (float)(dist*Math.sin(angle));
					
			ndfixMatrix.reset();
			ndfixMatrix.postTranslate(-ndfix.getWidth()/2, -ndfix.getHeight()/2);
			ndfixMatrix.postScale((float)(0.5*scaleFactor), (float)(0.5*scaleFactor));
			ndfixMatrix.postTranslate(centerx + (float)distx*scaleFactor, centery + (float)((offsety-disty)*scaleFactor));
					
			canvas.drawBitmap(ndfix, ndfixMatrix, paint);
					//canvas.drawCircle(centerx + (float)distx*scaleFactor, 
					//		centery + (float)((offsety-disty)*scaleFactor),
					//		(float)(5*scaleFactor), paint);
					
			canvas.drawText(fixdb.mname[i],
					centerx + (float)(distx + 7)*scaleFactor,
					centery + (float)((offsety-disty+10)*scaleFactor),
					paint);			
		}
	}
	
	void drawRoute(Canvas canvas, Paint paint, int offsety)
	{
		paint.setColor(Color.MAGENTA);
		paint.setStyle(Style.STROKE);
		paint.setTextSize((float)(10*scaleFactor));
		paint.setStrokeWidth(2*scaleFactor);
				
				
		float dist,distx, disty, angle;

		float pointx[] = new float[ROUTESIZE];
		float pointy[] = new float[ROUTESIZE];
		
		int count = 0;
		
		if (numwp > ROUTESIZE)
			numwp = ROUTESIZE;
		
		for (int i = 0; i<numwp; i++ ){
			//if (latwp[i] == 0 && lonwp[i] == 0)
			//	break;
			//Log.d("SAUL",String.format("Num WP = %d", numwp));
			//Log.d("SAUL",String.format("Lat= %f Lon= %f ", lat, lon));
			//Log.d("SAUL",String.format("Lat1= %f Lon1= %f ", latwp[0], lonwp[0]));
			
			
			count++;
			
			dist = fixdb.calcDistance(lat, lon, latwp[i], lonwp[i]);
			distx = (float)fixdb.distx;
			disty = (float)fixdb.disty;
			
			distx = (distx/1852)*(324/range); //pixels
			disty = (disty/1852)*(324/range); //pixels
			dist = (dist/1852)*(324/range); //pixels
			
			angle = (float)Math.atan2(disty,distx);						
			//angle = angle + (float)(heading/180*Math.PI);
			angle = angle + (float)(realheading/180*Math.PI);
			
			distx = (float) (dist*Math.cos(angle));
			disty = (float)(dist*Math.sin(angle));
			
			ndwpMatrix.reset();
			ndwpMatrix.postTranslate(-ndwp.getWidth()/2, -ndwp.getHeight()/2);
			ndwpMatrix.postScale((float)(0.5*scaleFactor), (float)(0.5*scaleFactor));
			pointx[i] = centerx + (float)distx*scaleFactor;
			pointy[i] = centery + (float)((offsety-disty)*scaleFactor);
			//ndwpMatrix.postTranslate(centerx + (float)distx*scaleFactor, centery + (float)((offsety-disty)*scaleFactor));
			ndwpMatrix.postTranslate(pointx[i],pointy[i]);
			
			canvas.drawBitmap(ndwp, ndwpMatrix, paint);
				
		}
		
		for (int i = 0; i < (numwp - 1); i++) {
			canvas.drawLine(pointx[i], pointy[i], pointx[i+1], pointy[i+1], paint);
		}
		
		
		
	}
	
	void drawWPtext(Canvas canvas, Paint paint)
	{
		if (mode == 0) return;
		
		//Draw WP text 
		paint.setColor(Color.MAGENTA);
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setTextSize(18*scaleFactor);
		paint.setStrokeWidth((int)(0.8*scaleFactor));
		//canvas.drawText("Hola", centerx, centery, paint);
			
		//Log.d("Saul",currentwp);
				
		if (currentwp.equals("")) {
			canvas.drawText("N/A", centerx + (int)(200*scaleFactor), centery - (int)(230*scaleFactor), paint);
		} else {
			canvas.drawText(currentwp, centerx + (int)(200*scaleFactor), centery - (int)(230*scaleFactor), paint);
		}
		
	}
	
}
