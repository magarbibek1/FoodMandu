package com.bibek.foodmandu.strictmode;

public class StrictModeClass {
    public static void strictmode() {
        android.os.StrictMode.ThreadPolicy policy =
                new android.os.StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();

        android.os.StrictMode.setThreadPolicy(policy);
    }
}
