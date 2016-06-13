package com.brylkowski.mahumito.mahumito;

import android.test.suitebuilder.annotation.LargeTest;


import com.brylkowski.mahumito.mahumito.commands.GetSimpleObjectFromApi;
import com.brylkowski.mahumito.mahumito.commands.utilities.AsyncCommandWrapper;
import com.brylkowski.mahumito.mahumito.commands.utilities.CommandCallbackInterface;
import com.brylkowski.mahumito.mahumito.commands.utilities.CommandExecutionFailure;
import com.brylkowski.mahumito.mahumito.commands.utilities.CommandHandler;
import com.brylkowski.mahumito.mahumito.models.SimpleObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
@LargeTest
public class AsyncCommandWrapperTest {

    @Test
    public void testAsyncCallIsExecuted() {
        GetSimpleObjectFromApi command = mock(GetSimpleObjectFromApi.class);
        CommandHandler handler = new CommandHandler();
        CommandCallbackInterface callback = mock(CommandCallbackInterface.class);

        AsyncCommandWrapper asyncCommandWrapper = new AsyncCommandWrapper(handler, callback);
        asyncCommandWrapper.doInBackground(command);
        SimpleObject simpleObject = new SimpleObject("Label", 69);

        verify(callback, times(1)).success(simpleObject);
    }

    @Test
    public void asyncCallFail() {
        GetSimpleObjectFromApi command = mock(GetSimpleObjectFromApi.class);
        CommandHandler handler = mock(CommandHandler.class);
        CommandExecutionFailure reason = new CommandExecutionFailure();
        Mockito.doThrow(reason).when(handler).handle(command);
        CommandCallbackInterface callback = mock(CommandCallbackInterface.class);

        AsyncCommandWrapper asyncCommandWrapper = new AsyncCommandWrapper(handler, callback);
        asyncCommandWrapper.doInBackground(command);

        verify(callback, times(1)).failure(reason);
    }

}