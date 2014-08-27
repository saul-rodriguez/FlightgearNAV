package com.rodriguez.saul.flightgearpfd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.webkit.WebView;


public class myWebView  extends WebView {
	Context mContext;
	
	int width;
	int height;
	
	public int x,y;
	
	public myWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		mContext = context;
		
		x = 0;
		y = 0;
		
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		width = this.getWidth();
		height = this.getHeight();
		
		Log.d("Saul",String.format("Width WebView %d", width));
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);
		
		canvas.drawCircle((float)(width/2. + x),(float)(height/2. - y), 10, paint);
		
	}
	
	

}
