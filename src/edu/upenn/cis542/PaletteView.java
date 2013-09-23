/**
 * Code handling Palette View
 */
package edu.upenn.cis542;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaletteView extends View {
	
	protected ShapeDrawable redSquare;
	protected ShapeDrawable yellowSquare;
	protected ShapeDrawable blueSquare;
	protected ShapeDrawable greenSquare;
	protected ShapeDrawable whiteSquare;
	
	private Paint thin, thick;
	private Paint timeDisp;
	private String time = new String("00:00");

	public PaletteView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	public PaletteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes squares of different colors, thin, THICK and timer
	 */
	protected void init() {
		redSquare = new ShapeDrawable(new RectShape());
		redSquare.getPaint().setColor(Color.RED);
		redSquare.setBounds(0, 0, 40, 40);
		
		yellowSquare = new ShapeDrawable(new RectShape());
		yellowSquare.getPaint().setColor(Color.YELLOW);
		yellowSquare.setBounds(40, 0, 80, 40);
		
		greenSquare = new ShapeDrawable(new RectShape());
		greenSquare.getPaint().setColor(Color.GREEN);
		greenSquare.setBounds(80, 0, 120, 40);
		
		blueSquare = new ShapeDrawable(new RectShape());
		blueSquare.getPaint().setColor(Color.BLUE);
		blueSquare.setBounds(120, 0, 160, 40);
		
		whiteSquare = new ShapeDrawable(new RectShape());
		whiteSquare.getPaint().setColor(Color.WHITE);
		whiteSquare.setBounds(160, 0, 200, 40);
		
		thin = new Paint();
		thin.setColor(Color.WHITE);
		thin.setTextAlign(Paint.Align.LEFT);
		thin.setTypeface(Typeface.SANS_SERIF);
		thin.setTextSize(20);
		
		thick = new Paint();
		thick.setColor(Color.WHITE);
		thick.setTextAlign(Paint.Align.LEFT);
		thick.setTypeface(Typeface.SANS_SERIF);
		thick.setTextSize(20);	
		
		timeDisp = new Paint();
		timeDisp.setColor(Color.WHITE);
		timeDisp.setTextAlign(Paint.Align.LEFT);
		timeDisp.setTypeface(Typeface.SANS_SERIF);
		timeDisp.setTextSize(20);
		
	}
	
	/**
	 * Draws the square, thin, THICK and timer on the palette
	 */
	protected void onDraw(Canvas canvas) {
		redSquare.draw(canvas);
		yellowSquare.draw(canvas);
		greenSquare.draw(canvas);
		blueSquare.draw(canvas);
		whiteSquare.draw(canvas);
			
		canvas.drawText("thin", 205, 35, thin);
		canvas.drawText("THICK", 260, 35, thin);
		canvas.drawText(time, 320, 35, timeDisp);
	}

	/**
	 * Converts the time into Min:Sec format and updates on the palette
	 * @param sec
	 */
	public void setTime(int sec) {
		// TODO Auto-generated method stub
		int seconds, minutes;
		String secString, minString;
		
		seconds = sec % 60;
		minutes = sec / 60;
		
		if (seconds < 10 ) 
			secString = "0" + seconds;
		else
			secString = "" + seconds;
		
		if(minutes < 10)
			minString = "0" + minutes;
		else
			minString = "" + minutes;
		
		time = minString + ":" + secString;
		//Log.d("","Time is: " + time + "\n");
		this.invalidate();	
	}
	
	/**
	 * Action on palette objects being clicked upon
	 */
	public boolean onTouchEvent (MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			
			PaintBrushView paintBrushView = (PaintBrushView) getRootView().findViewById(R.id.paintView);
			
			if(x > 0 && x < 40 && y > 0 && y < 40) {
				paintBrushView.color = Color.RED;
				paintBrushView.p.setColor(Color.RED);
				paintBrushView.invalidate();
				return true;
			}
			
			if(x > 40 && x < 80 && y > 0 && y < 40) {
				paintBrushView.color = Color.YELLOW;
				paintBrushView.p.setColor(Color.YELLOW);		
				paintBrushView.invalidate();
				return true;
			}
			
			if(x > 80 && x < 120 && y > 0 && y < 40) {
				paintBrushView.color = Color.GREEN;
				paintBrushView.p.setColor(Color.GREEN);		
				paintBrushView.invalidate();
				return true;
			}
			
			if(x > 120 && x < 160 && y > 0 && y < 40) {
				paintBrushView.color = Color.BLUE;
				paintBrushView.p.setColor(Color.BLUE);			
				paintBrushView.invalidate();
				return true;
			}
			
			if(x > 160 && x < 200 && y > 0 && y < 40) {
				paintBrushView.color = Color.WHITE;
				paintBrushView.p.setColor(Color.WHITE);		
				paintBrushView.invalidate();
				return true;
			}
			
			if(x > 200 && x < 255) {
				paintBrushView.strokeWidth = 2;
				paintBrushView.p.setStrokeWidth(2);
				paintBrushView.invalidate();
				return true;
			}
			
			if (x > 260 && x < 315) {
				paintBrushView.strokeWidth = 6;
				paintBrushView.p.setStrokeWidth(6);
				paintBrushView.invalidate();
				return true;
			}
		}
		return false;
	}
}
