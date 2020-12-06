package com.example.chapter10.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter10.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private Button intentService;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
    }

    private void initView() {
        startService = (Button) findViewById(R.id.startService);
        stopService = (Button) findViewById(R.id.stopService);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService = (Button) findViewById(R.id.bindService);
        bindService.setOnClickListener(this);
        unbindService = (Button) findViewById(R.id.unbindService);
        unbindService.setOnClickListener(this);
        intentService = (Button) findViewById(R.id.intentService);
        intentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.startService) {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        } else if (id == R.id.stopService) {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        } else if (id == R.id.bindService) {
            Intent intent = new Intent(this, MyService.class);
            bindService(intent, connection, BIND_AUTO_CREATE);
        } else if (id == R.id.unbindService) {
            unbindService(connection);
        } else if (id == R.id.intentService) {
            Log.d("service", "thread is: "+Thread.currentThread().getId());
            Intent intent=new Intent(this,MyIntentService.class);
            startService(intent);
        }
    }
}