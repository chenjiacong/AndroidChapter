package com.example.chapter5.move;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter5.R;

public class MoveActivity extends AppCompatActivity implements View.OnClickListener {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private Button sendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        initView();
        intentFilter = new IntentFilter();

        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    private void initView() {
        sendBroadcast = (Button) findViewById(R.id.sendBroadcast);
        sendBroadcast.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendBroadcast) {
            Intent intent=new Intent("android.net.conn.CONNECTIVITY_CHANGE");
            sendBroadcast(intent);

            /*1.发送有序广播，需要先设置哪个广播有序，在intentFilter设置priority
           静态广播 AndroidManifest:
             <intent-filter android:priority="100>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
            </intent-filter>

         动态广播   代码：
       IntentFilter   intentFilter = new IntentFilter();
      intentFilter.setPriority(100);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    NetworkChangeReceiver   networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);


               2.     Intent intent=new Intent("android.net.conn.CONNECTIVITY_CHANGE");
                       sendOrderedBroadcast(intent,null);

                3.需要对后面的广播进行拦截，在先接收到的广播方法onReceive()调用abortBroadcast();
                  就能终止传递
            */

        }
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is isAvailable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}