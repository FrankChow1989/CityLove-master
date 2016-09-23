package com.zzy.frank.www.citylove_master;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.RemoteViews;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zzy.frank.www.citylove_master.dao.MessageDB;
import com.zzy.frank.www.citylove_master.dao.UserDB;
import com.zzy.frank.www.citylove_master.util.SharePreferenceUtil;

/**
 * Created by pc on 2016/9/21.
 */
public class PushApplication extends Application
{
    private static PushApplication mApplication;

    private NotificationManager mNotificationManager;
    private Notification mNotification;

    //请求队列
    public static RequestQueue mRequestQueue;

    /**
     * 预定义的头像
     */
//    public static final int[] heads = {R.drawable.h0, R.drawable.h1,
//            R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
//            R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9,
//            R.drawable.h10, R.drawable.h11, R.drawable.h12, R.drawable.h13,
//            R.drawable.h14, R.drawable.h15, R.drawable.h16, R.drawable.h17,
//            R.drawable.h18};

    private UserDB userDB;
    private MessageDB messageDB;

    public synchronized static PushApplication getInstance()
    {
        return mApplication;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mApplication = this;
        initData();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues() {
        return mRequestQueue;
    }

    private void initData()
    {
        mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        userDB = new UserDB(this);
        messageDB = new MessageDB(this);
    }

    public NotificationManager getNotificationManager()
    {
        if (mNotificationManager == null)
            mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        return mNotificationManager;
    }

    /**
     * 创建挂机图标
     */
    @SuppressWarnings("deprecation")
//    public void showNotification()
//    {
//        if (!mSpUtil.getMsgNotify())// 如果用户设置不显示挂机图标，直接返回
//            return;
//
//        int icon = R.mipmap.ic_launcher;
//        CharSequence tickerText = "云送正在后台运行";
//
//        long when = System.currentTimeMillis();
//        mNotification = new Notification(icon, tickerText, when);
//
//        // 放置在"正在运行"栏目中
//        mNotification.flags = Notification.FLAG_ONGOING_EVENT;
//
//        RemoteViews contentView = new RemoteViews(getPackageName(),
//                R.layout.notify_status_bar_latest_event_view);
//        contentView.setImageViewResource(R.id.icon,
//                heads[mSpUtil.getHeadIcon()]);
//        contentView.setTextViewText(R.id.title, mSpUtil.getNick());
//        contentView.setTextViewText(R.id.text, tickerText);
//        contentView.setLong(R.id.time, "setTime", when);
//        // 指定个性化视图
//        mNotification.contentView = contentView;
//
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        // 指定内容意图
//        mNotification.contentIntent = contentIntent;
//
//        mNotificationManager.notify(0x000,
//                mNotification);
//    }

    public MessageDB getMessageDB()
    {
        return messageDB;
    }

    public UserDB getUserDB()
    {
        return userDB;
    }
}
