package android.override.sensor;

import android.override.sensor.IOverrideSensorDataListener;

interface IOverrideSensorService {
    void registerSensorDataListener(String packageName, IOverrideSensorDataListener listener);
    void enable_sensor(String sensorName);
    void disable_sensor(String sensorName);
}