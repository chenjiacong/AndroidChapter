package com.example.chapter4.Summary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chapter4.R;
public class NewsContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }
    public void refresh(String newsTitle,String newsContent){
        LinearLayout linearLayout=view.findViewById(R.id.visible_layout);
        linearLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText=view.findViewById(R.id.news_title);
        TextView newsContentText=view.findViewById(R.id.new_content);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
    }
}
