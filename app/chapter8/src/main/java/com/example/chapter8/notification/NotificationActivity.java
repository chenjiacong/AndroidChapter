package com.example.chapter8.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.chapter8.R;
import com.example.chapter8.cameraAlbum.CameraAlbumActivity;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button openNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
    }

    private void initView() {
        openNotification = (Button) findViewById(R.id.openNotification);
        openNotification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.openNotification) {
            Intent intent = new Intent(this, CameraAlbumActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("通知")
//                    .setContentText("点击跳转到系统摄影页面")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    //长文字
                   .setStyle(new NotificationCompat.BigTextStyle().bigText("if you click this notification,you will jump the view ofCamera and album."))
                 //灯光
                    //   .setLights(Color.GREEN,1000,1000)
                    //大图片
                   // .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background)))
                  //通知栏的重要性
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                    .build();
            manager.notify(1, notification);
        }
    }
}