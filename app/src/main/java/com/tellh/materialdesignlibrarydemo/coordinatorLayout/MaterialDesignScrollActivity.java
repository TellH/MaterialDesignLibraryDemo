package com.tellh.materialdesignlibrarydemo.coordinatorLayout;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

import com.tellh.materialdesignlibrarydemo.R;
import com.tellh.materialdesignlibrarydemo.utils.NotificationConstant;

/**
 * Created by tlh on 2016/4/17.
 */
public class MaterialDesignScrollActivity extends AppCompatActivity {
    private NotificationManager manger;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_material_design);
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        buildProgressStyleNotification();
//        buildBigTextStyleNotification();
//        buildInboxStyleNotification();
//        buildBigPictureStyleNotification();
//        buildBannerStyleNotification();
        buildMediaStyle();
    }

    private void buildBannerStyleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("横幅通知");
        builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
//        Intent intent = new Intent(this,ImageActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
//        builder.setContentIntent(pIntent);
        //这句是重点
//        builder.setFullScreenIntent(pIntent,true);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        manger.notify(NotificationConstant.TYPE_Hangup,notification);
    }

    private void buildInboxStyleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("InboxStyle");
        builder.setContentText("InboxStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        android.support.v4.app.NotificationCompat.InboxStyle style = new android.support.v4.app.NotificationCompat.InboxStyle();
        style.setBigContentTitle("BigContentTitle")
                .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行")
                .setSummaryText("SummaryText");
        builder.setStyle(style);
        builder.setAutoCancel(true);
//        Intent intent = new Intent(this,SettingsActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
//        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manger.notify(NotificationConstant.TYPE_Inbox,notification);
    }

    private void buildBigTextStyleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("BigTextStyle");
        builder.setContentText("BigTextStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.bigText("这里是点击通知后要显示的正文，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        style.setBigContentTitle("点击后的标题");
        //SummaryText没什么用 可以不设置
        style.setSummaryText("末尾只一行的文字内容");
        builder.setStyle(style);
        builder.setAutoCancel(false);
//        Intent intent = new Intent(this,ViewPagerActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
//        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manger.notify(NotificationConstant.TYPE_BigText,notification);
    }

    private void buildProgressStyleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        //禁止用户点击删除按钮删除
        builder.setAutoCancel(false);
        //禁止滑动删除
        builder.setOngoing(true);
        //取消右上角的时间显示
        builder.setShowWhen(false);
        builder.setContentTitle("下载中..."+80+"%");
        builder.setProgress(100,80,false);
        builder.setContentInfo(80+"%");
//        Intent intent = new Intent(this,DownloadService.class);
//        intent.putExtra("command",1);
        Notification notification = builder.build();
        manger.notify(NotificationConstant.TYPE_Progress,notification);
    }
    void buildBigPictureStyleNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("BigPictureStyle");
        builder.setContentText("BigPicture演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        android.support.v4.app.NotificationCompat.BigPictureStyle style = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        style.bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setStyle(style);
        builder.setAutoCancel(true);
//        Intent intent = new Intent(this,ImageActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        //设置点击大图后跳转
//        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        manger.notify(NotificationConstant.TYPE_BigPicture,notification);
    }
    void buildMediaStyle(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("MediaStyle");
        builder.setContentText("Song Title");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
//        Intent intent = new Intent(this,ImageActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
//        builder.setContentIntent(pIntent);
        //第一个参数是图标资源id 第二个是图标显示的名称，第三个图标点击要启动的PendingIntent
        builder.addAction(R.drawable.ic_skip_previous_white_24dp,"",null);
        builder.addAction(R.drawable.ic_stop_white_24dp,"",null);
        builder.addAction(R.drawable.ic_play_arrow_white_24dp,"",null);
        builder.addAction(R.drawable.ic_skip_next_white_24dp,"",null);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        style.setMediaSession(new MediaSessionCompat(this,"MediaSession",
                new ComponentName(this,Intent.ACTION_MEDIA_BUTTON),null).getSessionToken());
        //CancelButton在5.0以下的机器有效
//        style.setCancelButtonIntent(pIntent);
        style.setShowCancelButton(true);
        //设置要现实在通知右方的图标 最多三个
        style.setShowActionsInCompactView(2,3);
        builder.setStyle(style);
        builder.setShowWhen(false);
        Notification notification = builder.build();
        manger.notify(NotificationConstant.TYPE_Media,notification);
    }
}
