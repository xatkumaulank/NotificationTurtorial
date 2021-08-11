package com.example.notificationturtorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.notificationturtorial.databinding.ActivityMainBinding;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final String TITLE_PUSH_NOTIFICATION = "Vì sao các tỉnh chậm tiêm vắc xin?";
    private static final String CONTENT_PUSH_NOTIFICATION = "Các tỉnh nói do thiếu nhân lực\n" +
            "\n" +
            "Ông Nguyễn Thanh Tùng - giám đốc Sở Y tế tỉnh Hậu Giang - giải thích: Do tỉnh thiếu tủ trữ bảo quản nên chậm trễ trong tiêm vắc xin ngừa COVID-19. Vắc xin nhận từ Bộ Y tế phải gửi tại Viện Pasteur TP.HCM và nhận từng đợt, tiêm hết đợt này mới đem về đợt khác. \n" +
            "\n" +
            "Ngoài ra, theo ông Tùng, do triển khai tiêm cho doanh nghiệp, phải đưa lực lượng xuống tận nơi nên nhân lực hạn chế.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        binding.btnSendNotification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification_2();
            }
        });
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle(TITLE_PUSH_NOTIFICATION)
                .setContentText(CONTENT_PUSH_NOTIFICATION)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(CONTENT_PUSH_NOTIFICATION))
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.pink))
                .build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(getNotificationId(),notification);


//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (manager != null){
//            manager.notify(getNotificationId(),notification);
//        }

    }
    private void sendNotification_2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.hoadeptrai);

        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle("Notification Turtorial")
                .setContentText("Push local notification channel 2")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                .setColor(getResources().getColor(R.color.purple_200))
                .build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(getNotificationId(),notification);


//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (manager != null){
//            manager.notify(getNotificationId(),notification);
//        }

    }
    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}