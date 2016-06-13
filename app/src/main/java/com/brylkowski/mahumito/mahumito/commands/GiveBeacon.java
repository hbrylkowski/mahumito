package com.brylkowski.mahumito.mahumito.commands;

import com.brylkowski.mahumito.mahumito.models.Beacon;

public class GiveBeacon implements ApiResponse {

    @Override
    public Class getResponseType() {
        return Beacon.class;
    }

    protected String beaconId;
    protected String receiverId;

    public GiveBeacon(String beaconId, String receiverId) {
        this.beaconId = beaconId;
        this.receiverId = receiverId;
    }

    public String beaconId() {
        return beaconId;
    }

    public String receiverId() {
        return receiverId;
    }
}
