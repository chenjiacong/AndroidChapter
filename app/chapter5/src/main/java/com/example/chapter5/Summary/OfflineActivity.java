package com.example.chapter5.Summary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chapter5.R;

public class OfflineActivity extends BaseActivity implements View.OnClickListener {

    private Button offlineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        initView();
    }

    private void initView() {
        offlineBtn = (Button) findViewById(R.id.offlineBtn);

        offlineBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.offlineBtn) {
            Intent intent=new Intent("com.example.broadcast.FORCE_OFFLINE");
            sendBroadcast(intent);
        }
    }
}