package com.fooinc.tripwire;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;


import com.google.android.gms.location.LocationListener;

public class LocationService extends Service implements LocationListener {

    private LocationManager locationManager;

    private Location location;

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {

        

        if (intent.getAction().equals("startListening")) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) this);
        }
        else {
            if (intent.getAction().equals("stopListening")) {
                locationManager.removeUpdates((android.location.LocationListener) this);
                locationManager = null;
            }
        }

        return START_STICKY;

    }

    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }

    public void onLocationChanged(final Location location) {
        this.location = location;   
        // TODO this is where you'd do something like context.sendBroadcast()
    }

    public void onProviderDisabled(final String provider) {
    }

    public void onProviderEnabled(final String provider) {
    }

    public void onStatusChanged(final String arg0, final int arg1, final Bundle arg2) {
    }

}
