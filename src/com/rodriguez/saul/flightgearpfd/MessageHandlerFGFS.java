/*
 * Copyright (C) 2014  Saul Rodriguez

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    NOTE: this class is a modified version of GPL code available at https://github.com/Juanvvc/FlightGearMap
 * 
 */


package com.rodriguez.saul.flightgearpfd;

import java.util.Date;

import android.util.Log;

public class MessageHandlerFGFS {
	String[] data;
	String[] outData;
	private Date date = new Date();
	//private MovingAverage[] averages;
	
	public MessageHandlerFGFS() 
	{
		data = null;	
		
	}
	
	public void parse(final String input) 
	{
		// strip string with new line
		String realInput = input.substring(0, input.indexOf("\n"));
		data = realInput.split(":");

		date = new Date();

		// check that we have the desired number of parameters
		// just read the last data. If throws IndexOutOfBounds, the
		// other extreme is sending wrong data
				
		//getFloat(MAXSPEED);
	}
	
	public static final int HEADING = 0; // heading in degrees
	public static final int APHEADING = 1; // AP heading bug
	
	public static final int VORLID = 2;
	public static final int VORLDME = 3;
	public static final int VORLDMEINRANGE = 4;
	public static final int VORLINRANGE = 5;
	public static final int VORLFREQ = 6;
	public static final int SWITCHLEFT = 7;
	public static final int VORLDIR = 8;
	
	public static final int VORRID = 9;
	public static final int VORRDME = 10;
	public static final int VORRDMEINRANGE = 11;
	public static final int VORRINRANGE = 12;
	public static final int VORRFREQ = 13;
	public static final int SWITCHRIGHT = 14;
	public static final int VORRDIR = 15;
	
	public static final int ADFLID = 16;
	public static final int ADFLINRANGE = 17;
	public static final int ADFLFREQ = 18;
	public static final int ADFLDIR = 19;
	
	public static final int ADFRID = 20;
	public static final int ADFRINRANGE = 21;
	public static final int ADFRFREQ = 22;
	public static final int ADFRDIR = 23;
	
	public static final int RADIALDIR = 24;
	public static final int RADIALHEAD = 25;
	public static final int RADIALDEF = 26;
	public static final int GSDEF = 27;
	
	public static final int MODE = 28;
	public static final int RANGE = 29;
	public static final int MODEBUT = 30;
	public static final int TRUESPEED = 31;
	public static final int GROUNDSPEED = 32;
	public static final int WINDHEADING = 33;
	public static final int WINDSPEED = 34;
	
	public static final int LATITUDE = 35;
	public static final int LONGITUDE = 36;
	
	public static final int LATWP1 = 37;
	public static final int LONWP1 = 38;
	public static final int LATWP2 = 39;
	public static final int LONWP2 = 40;
	public static final int LATWP3 = 41;
	public static final int LONWP3 = 42;
	public static final int LATWP4 = 43;
	public static final int LONWP4 = 44;
	public static final int LATWP5 = 45;
	public static final int LONWP5 = 46;
	public static final int LATWP6 = 47;
	public static final int LONWP6 = 48;
	public static final int LATWP7 = 49;
	public static final int LONWP7 = 50;
	public static final int LATWP8 = 51;
	public static final int LONWP8 = 52;
	public static final int LATWP9 = 53;
	public static final int LONWP9 = 54;
	public static final int LATWP10 = 55;
	public static final int LONWP10 = 56;
	public static final int LATWP11 = 57;
	public static final int LONWP11 = 58;
	public static final int LATWP12 = 59;
	public static final int LONWP12 = 60;
	
	public static final int CURRENTWP = 61;
	public static final int NUMWP = 62;
	
	
	public int getInt(int i) 
	{
		if (data == null) {
			return 0;
		}
		return Integer.valueOf(data[i]);
	}

	public float getFloat(int i) 
	{
		if (data == null) {
			return 0;
		}
		//MovingAverage ma = this.averages[i];
		//if (ma==null) {
		//return Float.valueOf(data[i]);
		//} else {
		//return ma.getData(Float.valueOf(data[i]));
		//}
		return Float.valueOf(data[i]);
	}

	public String getString(int i) 
	{
		if (data == null) {
			return "";
		}
//		Log.d("Saul getstring index", String.format("%d", i));
//		Log.d("Saul getstring String: ",data[i]);
		return data[i];
	}

	public boolean getBool(int i)
	{
		if (data == null) {
			return false;
		}
		return data[i].equals("1");
	}

	public Date getDate() 
	{
		return date;
	}

	public boolean hasData() 
	{
		return data != null;
	}
			
}
