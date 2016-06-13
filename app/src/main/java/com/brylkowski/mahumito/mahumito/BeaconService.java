package com.brylkowski.mahumito.mahumito;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.brylkowski.mahumito.mahumito.activities.MapsActivity;
import com.brylkowski.mahumito.mahumito.activities.SwapBeaconActivity;
import com.brylkowski.mahumito.mahumito.commands.GetBeacon;
import com.brylkowski.mahumito.mahumito.commands.GetMyBeacons;
import com.brylkowski.mahumito.mahumito.commands.utilities.AsyncCommandWrapper;
import com.brylkowski.mahumito.mahumito.commands.utilities.CommandCallbackInterface;
import com.brylkowski.mahumito.mahumito.models.Beacon;
import com.brylkowski.mahumito.mahumito.providers.ApiInterface;
import com.google.android.gms.common.api.Api;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.device.BeaconRegion;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.listeners.IBeaconListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleIBeaconListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;
import com.kontakt.sdk.android.common.profile.IBeaconRegion;

import java.util.UUID;

public class BeaconService extends Service {
    private ProximityManager proximityManager;

    public BeaconService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        KontaktSDK.initialize("IBeIxRCDApxqzfNxCdTihsLPeuzeEQQC");

        proximityManager = new ProximityManager(this);
        BeaconRegion region = new BeaconRegion.Builder()
            .setProximity(UUID.fromString("f7826da6-4fa2-4e98-8024-bc5b71e0893e"))
            .setMajor(1939)
            .build();

        proximityManager.spaces().iBeaconRegion(region);

        proximityManager.setIBeaconListener(createIBeaconListener());
        startScanning();
        super.onCreate();
    }

    private void startScanning() {
        proximityManager.connect(new OnServiceReadyListener() {
            @Override
            public void onServiceReady() {
                proximityManager.startScanning();
            }
        });
    }

    private IBeaconListener createIBeaconListener() {
        return new SimpleIBeaconListener() {
            @Override
            public void onIBeaconDiscovered(final IBeaconDevice ibeacon, IBeaconRegion region) {
                Log.i("BEACON", ibeacon.getUniqueId());
                AsyncCommandWrapper wrapper = new AsyncCommandWrapper(new CommandCallbackInterface<Beacon>() {
                    @Override
                    public void success(Beacon objectResponse) {
                        if(objectResponse.currentOwner() == ApiInterface.USER_ID)
                            return;
                        Drawable myDrawable = getResources().getDrawable(R.drawable.logo);
                        Bitmap myBitmap = ((BitmapDrawable)myDrawable).getBitmap();
                        NotificationCompat.Builder mBuilder =
                            (NotificationCompat.Builder) new NotificationCompat.Builder(BeaconService.this)
                                .setSmallIcon(R.drawable.ic_media_play)
                                .setLargeIcon(myBitmap)
                                .setContentTitle(objectResponse.name() + " is nearby.")
                                .setContentText("Claim it and fulfill its mission.");
                        Intent resultIntent = new Intent(BeaconService.this, SwapBeaconActivity.class);
                        resultIntent.removeExtra("BEACON");
                        resultIntent.putExtra("BEACON", objectResponse);
                        resultIntent.putExtra("NOTIFICATION_ID", UUID.randomUUID());
                        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        PendingIntent activity = PendingIntent.getActivity(BeaconService.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        mBuilder.setContentIntent(activity);
                        Notification notification = mBuilder.build();

                        mNotificationManager.notify(ibeacon.getMinor(), notification);
                    }

                    @Override
                    public void failure(Throwable reason) {

                    }
                });
                wrapper.doInBackground(new GetBeacon(ibeacon.getUniqueId()));
            }
        };
    }




}
