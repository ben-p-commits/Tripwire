package com.fooinc.tripwire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;


public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar and notification bar
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//select layout
		setContentView(R.layout.activity_splash);
		
		transitionToMain();
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		checkConnectivityToProceed();
	}
	
	private boolean checkConnectivityToProceed(){
		ConnectionManager cm = new ConnectionManager(this);
		return (cm.confirmGPSConnection() && cm.confirmNetworkConnection());
	}
	private void transitionToMain(){
		 Intent intent = new Intent(this, MainActivity.class);
		    startActivity(intent);
	}



}
