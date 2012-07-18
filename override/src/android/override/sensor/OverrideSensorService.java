package android.override.sensor;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class OverrideSensorService extends Service {

    private HashMap<String, IOverrideSensorDataListener> mListeners;
    private String mAccelerometerSensorName;


    @Override
    public void onCreate() {
        super.onCreate();
        mListeners = new HashMap<String, IOverrideSensorDataListener>();

        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometerSensorName = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).getName();

        timer.schedule(mTimerTask, 1000, 1000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private class ServiceBinder extends IOverrideSensorService.Stub {
        @Override
        public void registerSensorDataListener(String packageName, IOverrideSensorDataListener listener) {
            synchronized(mListeners) {
                mListeners.put(packageName, listener);
            }
        }

        @Override
        public void enable_sensor(String sensorName) throws RemoteException {
        }

        @Override
        public void disable_sensor(String sensorName) throws RemoteException {
        }
    }
    IBinder mBinder = new ServiceBinder();


    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            synchronized (mListeners) {
                ArrayList<IOverrideSensorDataListener> deadListeners = new ArrayList<IOverrideSensorDataListener>();
                for (IOverrideSensorDataListener listener : mListeners.values()) {
                    try {
                        listener.onSensorDataChanged(Sensor.TYPE_ACCELEROMETER, new float[] {(float)Math.random(), (float)Math.random(), (float)Math.random()});
                    } catch (RemoteException ex) {
                        deadListeners.add(listener);
                    }
                }

                for (IOverrideSensorDataListener deadListener : deadListeners) {
                    //TODO: Remove dead listeners.
                }
            }
        }
    };

    Timer timer = new Timer("PeriodicSensorUpdates");
}
