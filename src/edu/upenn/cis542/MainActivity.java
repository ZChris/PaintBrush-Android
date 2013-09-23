/**
 * Paint Brush Main Activity
 * @author Soumyadeep Ghoshal, Karan Sawhney
 * @email: sghoshal@seas.upenn.edu, ksawhney@seas.upenn.edu
 */
package edu.upenn.cis542;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private int second = 0;
	Timer timer;
	final Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button clearButton = (Button) findViewById(R.id.clearButton);
		
		clearButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				PaintBrushView paintBrush = (PaintBrushView)findViewById(R.id.paintView);
		    	paintBrush.clear();
			}
		});	
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask (){
			
			@Override
			public void run() {
				updateGUI();
			}
		}, 0, 1000);
	}

	public void updateGUI () {
		handler.post(r);
	}
	
	final Runnable r = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			second++;
			PaletteView palView = (PaletteView) findViewById(R.id.paletteView);
			palView.setTime(second);
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
