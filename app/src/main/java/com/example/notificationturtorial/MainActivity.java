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
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.example.notificationturtorial.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
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
        binding.btnCustomNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomNotification();
            }
        });
    }

    private void sendCustomNotification() {
        //collapsed
        Uri sound = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.custom_notification);

        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_custom_notification);
        remoteViews.setTextViewText(R.id.tv_title_custom_notification,"Phó bí thư TP HCM: 'Có thể kéo dài giãn cách đến 15/9'");
        remoteViews.setTextViewText(R.id.tv_message_custom_notification,"Theo ông Phan Văn Mãi, dự kiến hai ngày nữa TP HCM công bố kế hoạch phòng chống Covid trong 30 ngày tới, tinh thần sẽ tiếp tục giãn cách theo Chỉ thị 16.\n" +
                "\n" +
                "Thông tin được Phó bí thư thường trực Thành ủy TP HCM Phan Văn Mãi nói tại cuộc họp báo cung cấp thông tin công tác phòng, chống Covid-19 trên địa bàn trưa 13/8. Cuộc họp diễn ra trong bối cảnh thành phố ghi nhận hơn 137.000 ca nhiễm trong đợt dịch thứ tư, bước qua ngày thứ 36 giãn cách xã hội theo Chỉ thị 16 và 16 tăng cường.");


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String strDate = simpleDateFormat.format(new Date());
        remoteViews.setTextViewText(R.id.tv_time_custom_notfication,strDate);

        //expanded
        RemoteViews remoteViewsExpanded = new RemoteViews(getPackageName(),R.layout.layout_custom_notification_expand);
        remoteViewsExpanded.setTextViewText(R.id.tv_title_custom_notification_expand,"Phó bí thư TP HCM: 'Có thể kéo dài giãn cách đến 15/9'");
        remoteViewsExpanded.setTextViewText(R.id.tv_message_custom_notification_expand,"Theo ông Phan Văn Mãi, dự kiến hai ngày nữa TP HCM công bố kế hoạch phòng chống Covid trong 30 ngày tới, tinh thần sẽ tiếp tục giãn cách theo Chỉ thị 16.\n" +
                "\n" +
                "Thông tin được Phó bí thư thường trực Thành ủy TP HCM Phan Văn Mãi nói tại cuộc họp báo cung cấp thông tin công tác phòng, chống Covid-19 trên địa bàn trưa 13/8. Cuộc họp diễn ra trong bối cảnh thành phố ghi nhận hơn 137.000 ca nhiễm trong đợt dịch thứ tư, bước qua ngày thứ 36 giãn cách xã hội theo Chỉ thị 16 và 16 tăng cường.");
        remoteViewsExpanded.setImageViewResource(R.id.img_custom_notification_expand,R.drawable.hoadeptrai);


        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setSound(sound)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViewsExpanded)
                .build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(getNotificationId(),notification);
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle(TITLE_PUSH_NOTIFICATION)
                .setContentText(CONTENT_PUSH_NOTIFICATION)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(CONTENT_PUSH_NOTIFICATION))
                .setLargeIcon(bitmap)
                .setSound(uri)
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
        Uri sound = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.custom_notification);


        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle("Notification Turtorial")
                .setContentText("Push local notification channel 2")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(bitmap)
                .setSound(sound)
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