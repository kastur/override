package android.override.sensor;

interface IOverrideSensorDataListener {
    void onSensorDataChanged(int sensorType, in float[] values);
}