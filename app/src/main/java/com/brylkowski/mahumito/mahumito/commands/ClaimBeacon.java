package com.brylkowski.mahumito.mahumito.commands;

import com.brylkowski.mahumito.mahumito.models.Beacon;

public class ClaimBeacon implements ApiResponse {

    @Override
    public Class getResponseType() {
        return Beacon.class;
    }

    protected String beaconId;

    public ClaimBeacon(String beaconId) {
        this.beaconId = beaconId;
    }

    public String beaconId() {
        return beaconId;
    }
}
