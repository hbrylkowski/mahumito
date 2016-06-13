package com.brylkowski.mahumito.mahumito.commands;

import com.brylkowski.mahumito.mahumito.models.Beacon;

import java.util.List;

public class GetMyBeacons implements ApiResponse {

    @Override
    public Class getResponseType() {
        return List.class;
    }

}
