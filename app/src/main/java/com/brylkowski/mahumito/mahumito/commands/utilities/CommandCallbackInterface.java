package com.brylkowski.mahumito.mahumito.commands.utilities;

public interface CommandCallbackInterface<T> {
    public void success(T objectResponse);
    public void failure(Throwable reason);
}
