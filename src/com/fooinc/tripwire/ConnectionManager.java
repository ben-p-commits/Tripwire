package com.fooinc.tripwire;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
 
public class ConnectionManager {
     
    private Context _context;
     
    public ConnectionManager(Context context){
        this._context = context;
    }
    public boolean confirmNetworkConnection()
    {
    	//confirm network connection
    	boolean result;
    	if(isConnectingToInternet()){
    		Toast.makeText(_context, "Network connected!", Toast.LENGTH_SHORT).show();
    		result = true;
    	}else{
    		Toast.makeText(_context, "No network connection! Please turn on wifi or data services.", Toast.LENGTH_SHORT).show();
    		result = false;
    	}
    	return result;
    }
    public boolean confirmGPSConnection()
    {
    	boolean result;
		LocationManager locationManager = (LocationManager) _context.getSystemService(_context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(_context, "GPS is enabled!", Toast.LENGTH_SHORT).show();
            result = true;
        }else{
            showGPSDisabledAlertToUser(_context);
            result = false;
        }
        return result;
		
    }
    private boolean isConnectingToInternet(){
        ConnectivityManager conn = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
          if (conn != null) 
          {
              NetworkInfo[] info = conn.getAllNetworkInfo();
              if (info != null) 
                  for (int i = 0; i < info.length; i++) 
                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
                      {
                          return true;
                      }
 
          }
          return false;
    }
	private void showGPSDisabledAlertToUser( final Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("GPS is disabled on the device. Would you like to enable it?")
        .setCancelable(false)
        .setPositiveButton("Go to settings to enable",
                new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Intent callGPSSettingIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(callGPSSettingIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}