package android.override.location;

import android.content.Intent;
import android.os.IBinder;

public class OverrideLocationService extends android.app.Service {

    OverrideLocationServiceImpl mBinder;
    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new OverrideLocationServiceImpl(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
