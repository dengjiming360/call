package com.example.twentyfour;
import android.content.SharedPreferences;
public class StroreDoubleUtil {
        public static SharedPreferences.Editor putDouble(final SharedPreferences.Editor edit, final String key, final double value) {
            return edit.putLong(key, Double.doubleToRawLongBits(value));
        }

        public static double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
            if (!prefs.contains(key))
                return defaultValue;
            return Double.longBitsToDouble(prefs.getLong(key, 0));
        }
}
