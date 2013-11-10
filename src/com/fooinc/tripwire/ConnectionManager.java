package com.fooinc.tripwire;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
 
public class ConnectionManager {
     
    private Context _context;
     
    public ConnectionManager(Context context){
        this._context = context;
    }
 
    public boolean isConnectingToInternet(){
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
}