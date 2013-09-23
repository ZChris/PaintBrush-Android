/**
 * Code Handling Paint Brush view
 */
package edu.upenn.cis542;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintBrushView extends View {
	
	private Bitmap bitmap;
	private Point startCoordinate;
	private Point endCoordinate;
	
	public int color;
	public int strokeWidth;
	public Paint p;
	public Canvas canvas;
	
	boolean somethingDrawn = false;
	
	/**
	 * Primary Constructor
	 * @param context
	 */
	public PaintBrushView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Secondary constructor
	 * @param context
	 * @param attrs
	 */
	public PaintBrushView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Sets the default color and stroke width.
	 */
	public void init() {
		setDrawingCacheEnabled(true);
		color = Color.WHITE;
		strokeWidth = 2;
		p = new Paint();
		p.setColor(Color.WHITE);
		p.setStrokeWidth(2);			
	}
	
	/**
	 * Called when something drawn. Gets the drawing cache and stores in a Bitmap object
	 * Reproduces on the screen
	 * @param Canvas canvas
	 */
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		bitmap = this.getDrawingCache();
		canvas.drawBitmap(bitmap, 0, 0, p);
	}
	
	/**
	 * Gets coordinates and draws a point or a line.
	 * When pressure sensed, Motion event = ACTION_DOWN.
	 * When Moving event = ACTION_MOVE
	 * @param Motion Event
	 * @return boolean
	 */
	public boolean onTouchEvent(MotionEvent e) {
		
		if(e.getAction() == MotionEvent.ACTION_DOWN) {
			int x = (int) e.getX();
			int y = (int) e.getY();
			startCoordinate = new Point(x, y);
			canvas = new Canvas();
			canvas.setBitmap(bitmap);
			canvas.drawPoint(x, y, p);
			invalidate();
			somethingDrawn = true;
			return true;
		}
		
		else if(e.getAction() == MotionEvent.ACTION_MOVE ) {
			int x = (int) e.getX();
			int y = (int) e.getY();
			endCoordinate = new Point(x, y);
			canvas = new Canvas();
			canvas.setBitmap(bitmap);
			canvas.drawLine(startCoordinate.x, startCoordinate.y, endCoordinate.x, endCoordinate.y, p);
			invalidate();
			startCoordinate = endCoordinate;
			return true;
		}	
		return false;
	}
	
	/**
	 * Clears the contents of the PaintBrushView
	 */
	public void clear() {
		if(somethingDrawn) {
			canvas.drawColor(Color.BLACK);
			invalidate();
		}
	}
}