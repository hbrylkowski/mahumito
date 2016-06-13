package com.brylkowski.mahumito.mahumito.commands;

import com.brylkowski.mahumito.mahumito.models.SimpleObject;

public class GetSimpleObjectFromApi implements ApiResponse {

    @Override
    public Class getResponseType() {
        return SimpleObject.class;
    }
}
