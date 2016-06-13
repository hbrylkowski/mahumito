package com.brylkowski.mahumito.mahumito.commands.utilities;

import android.support.annotation.NonNull;

import com.brylkowski.mahumito.mahumito.commands.ApiResponse;
import com.brylkowski.mahumito.mahumito.commands.ClaimBeacon;
import com.brylkowski.mahumito.mahumito.commands.GetBeacon;
import com.brylkowski.mahumito.mahumito.commands.GetBeacons;
import com.brylkowski.mahumito.mahumito.commands.GetMyBeacons;
import com.brylkowski.mahumito.mahumito.commands.GetSimpleObjectFromApi;
import com.brylkowski.mahumito.mahumito.commands.GiveBeacon;
import com.brylkowski.mahumito.mahumito.models.Beacon;
import com.brylkowski.mahumito.mahumito.models.SimpleObject;
import com.brylkowski.mahumito.mahumito.providers.ApiInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommandHandler {

    public Beacon handle(GetBeacon command) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<Beacon> beaconCall = service.getBeacon(command.beaconId());
        Beacon beacon = null;
        try {
            beacon = beaconCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beacon;
    }

    public List<Beacon> handle(GetMyBeacons command) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<List<Beacon>> beaconCall = service.getMyBeacons();
        try {
            return beaconCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Beacon> handle(GetBeacons command) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<List<Beacon>> beaconCall = service.getBeacons();
        try {
            return beaconCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void handle(ClaimBeacon command) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call beaconCall = service.claimBeacon(command.beaconId(), ApiInterface.USER_ID);
        try {
            beaconCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private String baseUrl() {
        return "http://402f5d1e.ngrok.io/api/";
    }

    public Object handleAll(ApiResponse command) {
        if(command instanceof GetBeacon){
            return handle((GetBeacon) command);
        }
        if(command instanceof GetMyBeacons){
            return handle((GetMyBeacons) command);
        }
        if(command instanceof GetBeacons){
            return handle((GetBeacons) command);
        }
        if(command instanceof ClaimBeacon){
            handle((ClaimBeacon) command);
        }
        if(command instanceof GiveBeacon){
            handle((GiveBeacon) command);
        }
        return null;
    }

    private void handle(GiveBeacon command) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call beaconCall = service.claimBeacon(command.beaconId(), "", command.receiverId());
        try {
            beaconCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
