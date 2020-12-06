package com.example.chapter5.Summary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter5.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView username;
    private EditText password;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        username = (TextView) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send) {
            if (!username.getText().toString().trim().isEmpty() &&
                    !password.getText().toString().trim().isEmpty()){
                Intent intent=new Intent(this,OfflineActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "请输入密码或账户", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }


}