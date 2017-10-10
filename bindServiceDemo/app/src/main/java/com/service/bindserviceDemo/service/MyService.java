package com.service.bindserviceDemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private void serviceMethod() {
        Toast.makeText(getApplicationContext(), "服务里的方法执行了。。。",
                Toast.LENGTH_SHORT).show();
    }

    private class MyBinder extends Binder implements IMyBinder {

        @Override
        public void invokeMethodInMyService() {
            serviceMethod();
        }
    }
}
