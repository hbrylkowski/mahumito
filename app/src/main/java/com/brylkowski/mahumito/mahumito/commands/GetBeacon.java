package com.brylkowski.mahumito.mahumito.commands;

import com.brylkowski.mahumito.mahumito.models.Beacon;
import com.brylkowski.mahumito.mahumito.models.SimpleObject;

public class GetBeacon implements ApiResponse {

    @Override
    public Class getResponseType() {
        return Beacon.class;
    }

    protected String beaconId;

    public GetBeacon(String beaconId) {
        this.beaconId = beaconId;
    }

    public String beaconId() {
        return beaconId;
    }
}
