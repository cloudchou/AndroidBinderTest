package com.cloud.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

/**
 * ClassName:CloudService
 * 
 * @author Zhouyaoyun
 * @version
 * @since Ver 1.1
 * @Date 2014-4-18 上午11:26:28
 * 
 * @see
 */
public class CloudService extends Service {
    private final static String TAG = CloudService.class.getSimpleName();

    class CloudMananger extends ICloudManager.Stub {

        @Override
        public void print(String str) throws RemoteException {
            Log.d(TAG, "[ThreadId " + Thread.currentThread().getId() + "] [ProcessId" + Process.myPid()
                    + "]CloudService receive client print msg request: " + str);
        }

        @Override
        public int add(int a, int b) throws RemoteException {
            Log.d(TAG, "[ThreadId " + Thread.currentThread().getId() + "] [ProcessId" + Process.myPid()
                    + "[CloudService receive client add request : ");
            return a + b;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "[ThreadId " + Thread.currentThread().getId() + "] [ProcessId" + Process.myPid() + "]  onCreate");
    }

    private CloudMananger manager = new CloudMananger();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "[ThreadId " + Thread.currentThread().getId() + "] [ProcessId" + Process.myPid() + "]  onBind");
        return manager;
    }

}
