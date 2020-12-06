package com.example.chapter5.local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.chapter5.R;

public class LocalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button localBrocast;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        initView();
        /*本地广播优势
        * 1.发送的广播只能在应用内部传递
        * 2.广播接收器也只能接收本应用发送的广播
        * 3.不需担心安全漏洞隐患
        * 4.本地广播比发送系统全局广播更高效
        * */
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter("com.example.broadcast");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    private void initView() {
        localBrocast = (Button) findViewById(R.id.localBrocast);

        localBrocast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.localBrocast) {
            Intent intent = new Intent("com.example.broadcast");
            localBroadcastManager.sendBroadcast(intent);
        }
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "接收到了", Toast.LENGTH_SHORT).show();
        }
    }
}