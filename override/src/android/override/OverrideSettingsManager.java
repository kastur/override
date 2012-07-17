package android.override;

import android.override.context.RandomWalkLocationProvider;

public class OverrideSettingsManager {
    private static OverrideSettingsManager instance = new OverrideSettingsManager();

    public static OverrideSettingsManager getInstance() {
        return instance;
    }

    public boolean checkOverrideLocation(String packageName) {
        return true;
    }

    public String getOverrideLocationProvider(String packageName) {
        return RandomWalkLocationProvider.RANDOM_WALK_LOCATION_PROVIDER;
    }

    public boolean checkOverrideAccelerometer(String packageName) {
        return false;
    }
}
