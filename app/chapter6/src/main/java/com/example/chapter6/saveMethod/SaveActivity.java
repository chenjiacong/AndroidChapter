package com.example.chapter6.saveMethod;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter6.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText fileEdit;
    private EditText username;
    private EditText password;
    private CheckBox checkBox;
    private Button send;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        initView();
        //文件保存
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            fileEdit.setText(inputText);
            fileEdit.setSelection(inputText.length());
            Toast.makeText(this, "restoring succeeded", Toast.LENGTH_SHORT).show();
        }


        //SharedPreference保存
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("remember", false);
        if (isRemember) {
            String account = pref.getString("username", "");
            String password1 = pref.getString("password", "");
            username.setText(account);
            password.setText(password1);
            checkBox.setChecked(true);
        }
    }

    private String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String content = fileEdit.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            save(content);
        }

    }

    private void initView() {
        fileEdit = (EditText) findViewById(R.id.fileEdit);
        username = (EditText) findViewById(R.id.username);
        username.setOnClickListener(this);
        password = (EditText) findViewById(R.id.password);
        password.setOnClickListener(this);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(this);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send) {
            String userName = username.getText().toString().trim();
            String passWord = password.getText().toString().trim();
            editor = pref.edit();
            if (!userName.isEmpty() && !passWord.isEmpty()) {
                if (checkBox.isChecked()) {

                    editor.putBoolean("remember", true);
                    editor.putString("username", userName);
                    editor.putString("password", passWord);

                } else {
                    editor.clear();
                }
                editor.apply();
                finish();
            }

        }
    }

    private void submit() {
        // validate
        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "usernameString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "passwordString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}