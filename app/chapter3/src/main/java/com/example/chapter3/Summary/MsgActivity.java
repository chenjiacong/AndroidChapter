package com.example.chapter3.Summary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter3.R;

import java.util.ArrayList;
import java.util.List;

public class MsgActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerList;
    private EditText editMsg;
    private Button send;
    private List<Msg> msgList=new ArrayList<>();
    private  MsgAdapter msgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        initMsg();
        initView();

    }

    private void initMsg() {
        Msg msg1=new Msg("HELLO GAY",Msg.TYPE_RECEIVE);
        msgList.add(msg1);
        Msg msg2=new Msg("hi,nice to meet you",Msg.TYPE_SEND);
        msgList.add(msg2);
    }

    private void initView() {
        recyclerList = (RecyclerView) findViewById(R.id.recyclerList);
        editMsg = (EditText) findViewById(R.id.editMsg);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(this);
        msgAdapter=new MsgAdapter(msgList);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerList.setLayoutManager(manager);
        recyclerList.setAdapter(msgAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send) {
            if (!editMsg.getText().toString().isEmpty()){
             String content= editMsg.getText().toString();
             Msg msg=new Msg(content,Msg.TYPE_SEND);
             msgList.add(msg);
             msgAdapter.notifyItemInserted(msgList.size()-1);
             recyclerList.scrollToPosition(msgList.size()-1);
             editMsg.setText("");
            }
        }
    }


}