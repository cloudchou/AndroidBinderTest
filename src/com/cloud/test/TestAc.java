package com.cloud.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

/**
 * ClassName:TestAc
 * 
 * @author Zhouyaoyun
 * @version
 * @since Ver 1.1
 * @Date 2014-4-18 下午1:13:05
 * 
 * @see
 */
public class TestAc extends Activity {
    private static final String TAG = TestAc.class.getSimpleName();
    private ICloudManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "[ThreadId " + Thread.currentThread().getId() + "] [ProcessId" + Process.myPid() + "]  onCreate");
        findViewById(R.id.btn_print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d(TAG, "=========== Client call CloudService print function");
                    manager.print("Hello world");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d(TAG, "======Client call CloudService add function");
                    int a = manager.add(3, 2);
                    Log.d(TAG, "======Client add function reuslt : " + a);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, CloudService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "[ThreadId " + Thread.currentThread().getId() + "] [ProcessId" + Process.myPid()
                    + "]  onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "[ThreadId " + Thread.currentThread().getId() + "] [ProcessId" + Process.myPid()
                    + "]  onServiceConnected");
            manager = ICloudManager.Stub.asInterface(service);
            findViewById(R.id.btn_print).setEnabled(true);
            findViewById(R.id.btn_add).setEnabled(true);
        }
    };

    @Override
    protected void onDestroy() {

        // TODO Auto-generated method stub
        super.onDestroy();

    }

}
