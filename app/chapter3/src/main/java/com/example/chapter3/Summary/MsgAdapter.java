package com.example.chapter3.Summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter3.R;

import java.util.ArrayList;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder3> {
    private List<Msg> msgList=new ArrayList<>();
    public MsgAdapter(List<Msg> msgList){
        this.msgList=msgList;
    }
    @NonNull
    @Override
    public MsgAdapter.ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        ViewHolder3 viewHolder3=new ViewHolder3(view);
        return viewHolder3;
    }

    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder3 holder, int position) {
  Msg msg=msgList.get(position);
  if (msg.getType()==Msg.TYPE_RECEIVE){
      holder.rightLayout.setVisibility(View.GONE);
      holder.leftLayout.setVisibility(View.VISIBLE);
      holder.leftMsg.setText(msg.getContent());
  }else {
      holder.rightLayout.setVisibility(View.VISIBLE);
      holder.leftLayout.setVisibility(View.GONE);
      holder.rightMsg.setText(msg.getContent());
  }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            leftLayout=itemView.findViewById(R.id.left_layout);
            rightLayout=itemView.findViewById(R.id.right_layout);
            leftMsg=itemView.findViewById(R.id.left_msg);
            rightMsg=itemView.findViewById(R.id.right_msg);
        }
    }
}
