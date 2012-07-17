package android.override.location;

import android.override.location.ILocationListener;
import android.location.Location;
import android.os.Bundle;

interface IOverrideLocationService
{
    // The following functions are visible to applications.
    void requestLocationUpdates(String provider, boolean singleShot, in ILocationListener listener);
    void removeUpdates(in ILocationListener listener);
    Location getLastKnownLocation(String provider);

    // The following functions are used by location providers.
    void addProvider(String provider);
    void removeProvider(String provider);
    void reportLocation(in Location location);
}
