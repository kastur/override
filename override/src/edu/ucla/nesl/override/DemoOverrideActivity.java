package edu.ucla.nesl.override;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.override.context.RandomWalkLocationProvider;
import android.override.location.OverrideLocationManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DemoOverrideActivity extends Activity
{
    private static final String TAG = "DemoOverrideActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b = (Button)findViewById(R.id.btnStart);
        b.setOnClickListener(onClickListener);


        locMan = new OverrideLocationManager(this.getApplicationContext());

        startService(new android.content.Intent(this, RandomWalkLocationProvider.class));
    }

    OverrideLocationManager locMan;

    android.view.View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    };

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "Received location = {" + location.getLatitude() + ", " + location.getLongitude() + "};");
            TextView textView = (TextView)findViewById(R.id.txtLocation);
            textView.setText("lat = " + location.getLatitude() + ", lon = " + location.getLongitude())  ;
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
