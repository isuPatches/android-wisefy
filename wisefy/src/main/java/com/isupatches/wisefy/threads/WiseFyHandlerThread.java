package com.isupatches.wisefy.threads;


import android.os.HandlerThread;
import android.util.Log;
import com.isupatches.wisefy.util.LogUtil;


public class WiseFyHandlerThread extends HandlerThread {

    public static final String TAG = WiseFyHandlerThread.class.getSimpleName();

    private boolean mLoggingEnabled;

    public WiseFyHandlerThread(String name, boolean loggingEnabled) {
        super(name);
        this.mLoggingEnabled = loggingEnabled;
    }

    @Override
    public synchronized void start() {
        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
            Log.d(TAG, "WiseFyHandlerThread starting");
        }
        super.start();
    }

    @Override
    public boolean quitSafely() {
        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
            Log.d(TAG, "quitSafely called on WiseFyHandlerThread");
        }
        return super.quitSafely();
    }
}
