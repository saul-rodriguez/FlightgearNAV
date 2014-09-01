package com.rodriguez.saul.flightgearpfd;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Plane {

	public int centerx;
	public int centery;
	public float scaleFactor;
	
	float heading;
	int apheading;
	
	//VORL
	int switchvorl;
	
	String vorlid;
	float  vorldme;
	boolean vorldmeinrange; // is DME in range ?
	boolean vorlinrange; // is nav in range ?
	float vorlfreq;
	float vorldirection;
	
	//VORR
	int switchvorr;
	
	String vorrid;
	float  vorrdme;
	boolean vorrdmeinrange; // is DME in range ?
	boolean vorrinrange; // is nav in range ?
	float vorrfreq;
	float vorrdirection;
	
	//ADFL
	String adflid;
	boolean adflinrange;
	int adflfreq;
	float adfldirection;
	
	//ADFR
	String adfrid;
	boolean adfrinrange;
	int adfrfreq;
	float adfrdirection;
	
	//RADIAL NAV1
	float radial;
	float realheading;
	float radialdef;
	float gsdef;
	
	//Encoder Modes
	int mode;
	int range;
	boolean modebut;
	
	float truespeed;
	float groundspeed;
	
	float windspeed;
	float windheading;
	
	float lat;
	float lon;
	
	float reflat;
	float reflon;
	boolean shownav;
	boolean showcir;
	boolean showroute;
	boolean showfix;
	
	//route
	float[] latwp;
	float[] lonwp;
	final int ROUTESIZE = 12; //number if waypoints
	/*
	float horizontRollAngle;
	float horizontPitchAngle;	
	float verticalSpeed;
	float speed;
	float altitude;
	float heading;
	float locnavQuality; //the localizer for ILS in range when > 0.9
	boolean locnav;     //Is the localizer activated?
	float headingLoc;   //Localizer deflection (normalized) -1.0 to 1.0
	boolean gsInRange; //Is the glideslope in range?
	boolean gsActive; //Is glidescope activated?
	float gsDeflection; //Deflection of the glideslope normalizer (-1.0 to 1.0)
	int radioaltimeter; //radioaltimeter feet;
	float mach; 		//mach speed
	float stallspeed;  //min speed
	boolean stallwarning; //turn on when stallspeed is valid
	float flaps; //flap status
	float maxspeed; //maximum speed
	String apIndicator; //Autopilot indicator
	String pitchMode; // status of AP pitch conf.
	String rollMode; //  status of AP roll conf.
	String speedMode; // Status of AP speed conf.
	float apaltitude; // AP set altitude
	float apactualaltitude; //AP actual/current altitude
	float apspeed; // AP speed
	int apheading; //AP heading bug
	boolean dmeinrange; // is DME in range ?
	float dme; // dme distance
	*/
	
	public Bitmap mask = null;
		
	
	public void draw(Canvas canvas) {
		
	}
	
	public void readDB() {
		
	}
	
	public boolean checkUpdateDBNeeded() {
		return false;
	}
	
	public void updateDB() {
		
	}
	
	public void setdb(NAVdb[] nav, FIXdb[] fix)
	{
	
	}
}
