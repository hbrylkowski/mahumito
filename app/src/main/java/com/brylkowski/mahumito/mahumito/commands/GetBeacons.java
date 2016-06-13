package com.brylkowski.mahumito.mahumito.commands;

import java.util.List;

public class GetBeacons implements ApiResponse {

    @Override
    public Class getResponseType() {
        return List.class;
    }

}
