package com.fooinc.tripwire;

import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity {

	// Google Map
	private GoogleMap googleMap;
	private EditText addressField;
	private Geocoder geocoder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			initilizeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		addressField = (EditText)findViewById(R.id.address_field);
		geocoder = new Geocoder(this);
	}
	//function to load map
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

			//fly to Current location
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			googleMap.setMyLocationEnabled(true);

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(), "Sorry! unable to create map. See LogCat for error info!", Toast.LENGTH_SHORT).show();
			} 
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	public void search(View view){
		Toast.makeText(getApplicationContext(), "Searching: \"" + addressField.getText() + "\"", Toast.LENGTH_SHORT).show();	
		try{
			String locationName = addressField.getText().toString();
			List<Address> addressList = geocoder.getFromLocationName(locationName, 5);
			if(addressList != null && addressList.size() > 0) {
				double lat =(addressList.get(0).getLatitude());  
                double lng =(addressList.get(0).getLongitude());  

                LatLng dest = new LatLng(lat, lng);
                CameraPosition cp = new CameraPosition.Builder().target(dest).zoom(6).build();
    			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
                
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

