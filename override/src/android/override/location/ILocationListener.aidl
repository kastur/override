package android.override.location;

import android.location.Location;
import android.os.Bundle;

oneway interface ILocationListener {
    void onLocationChanged(in Location location);
    void onStatusChanged(String provider, int status, in Bundle extras);
    void onProviderEnabled(String provider);
    void onProviderDisabled(String provider);
}
