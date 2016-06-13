package com.brylkowski.mahumito.mahumito;

import android.app.Application;

import com.kontakt.sdk.android.common.KontaktSDK;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KontaktSDK.initialize(this);
    }
}
