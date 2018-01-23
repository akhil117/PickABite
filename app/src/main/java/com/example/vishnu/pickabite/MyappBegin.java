package com.example.vishnu.pickabite;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by vishnu on 20/1/18.
 */

public class MyappBegin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
