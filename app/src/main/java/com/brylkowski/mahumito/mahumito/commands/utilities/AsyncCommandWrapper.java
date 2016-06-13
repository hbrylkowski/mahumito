package com.brylkowski.mahumito.mahumito.commands.utilities;

import android.os.AsyncTask;
import android.os.StrictMode;

import com.brylkowski.mahumito.mahumito.commands.ApiResponse;

public class AsyncCommandWrapper extends AsyncTask<Object, Integer, Long>{
    protected CommandHandler handler;
    protected CommandCallbackInterface callback;

    public AsyncCommandWrapper(CommandHandler handler, CommandCallbackInterface callback) {
        this.handler = handler;
        this.callback = callback;
    }

    public AsyncCommandWrapper(CommandCallbackInterface callback) {
        this.callback = callback;
        this.handler = new CommandHandler();
    }

    @Override
    public Long doInBackground(Object... commands) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            ApiResponse command = (ApiResponse) commands[0];

            if(callback !=null){
                callback.success(handler.handleAll(command));
            }
        } catch (Throwable e){
            e.printStackTrace();
            if(callback !=null)
                callback.failure(e);
        }
        return null;
    }

    public AsyncCommandWrapper() {
        this.handler = new CommandHandler();
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }
}
