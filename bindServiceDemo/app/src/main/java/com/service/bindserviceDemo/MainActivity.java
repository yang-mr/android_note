package com.service.bindserviceDemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.service.bindserviceDemo.service.IMyBinder;
import com.service.bindserviceDemo.service.MyService;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private MyConn conn;
    private IMyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //开启服务按钮的点击事件
    public void start(View view) {
        intent = new Intent(this, MyService.class);
        conn = new MyConn();
        //绑定服务，
        // 第一个参数是intent对象，表面开启的服务。
        // 第二个参数是绑定服务的监听器
        // 第三个参数一般为BIND_AUTO_CREATE常量，表示自动创建bind
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    //调用服务方法按钮的点击事件
    public void invoke(View view) {
        if (null != myBinder)
        myBinder.invokeMethodInMyService();
        else {
            Toast.makeText(getApplicationContext(), "服务还没开启。。。",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("yw", componentName.toString());
            myBinder = (IMyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
