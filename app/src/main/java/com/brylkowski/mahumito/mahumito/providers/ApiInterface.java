package com.brylkowski.mahumito.mahumito.providers;

import com.brylkowski.mahumito.mahumito.models.Beacon;
import com.brylkowski.mahumito.mahumito.models.SimpleObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    int USER_ID = 4;

    public SimpleObject getSimpleObject();

    @GET("beacon/{beaconId}/?format=json")
    public Call<Beacon> getBeacon(@Path("beaconId") String beaconId);

    @GET("beacon/byuserid/"+ USER_ID + "/?format=json")
    public Call<List<Beacon>> getMyBeacons();

    @GET("beacon/?format=json")
    public Call<List<Beacon>> getBeacons();

    @FormUrlEncoded
    @PUT("beacon/{beaconId}/?format=json")
    public Call<Object> claimBeacon(@Path("beaconId") String beaconId, @Field("pending_claim_user") Integer myUserID);

    @FormUrlEncoded
    @PUT("beacon/{beaconId}/?format=json")
    public Call<Object> claimBeacon(@Path("beaconId") String beaconId, @Field("pending_claim_user") String claim_user, @Field("current_owner") String ownerId);
}
