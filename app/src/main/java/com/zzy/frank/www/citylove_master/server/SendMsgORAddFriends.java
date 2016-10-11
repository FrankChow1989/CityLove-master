package com.zzy.frank.www.citylove_master.server;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.dao.UserDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pc on 2016/10/11.
 */
public class SendMsgORAddFriends extends Service
{

    PushApplication mApplication;
    List<User> mUser = new ArrayList<>();
    List<String> mUserIDs = new ArrayList<>();
    final int ADDUSERTIME = 1;

    public static ArrayList<onNewMessageListener> msgListeners = new ArrayList<onNewMessageListener>();
    public static ArrayList<onNewFriendListener> friendListeners = new ArrayList<onNewFriendListener>();

    Random random = new Random();

    User user;
    User user1;

    public static interface onNewMessageListener
    {
        public abstract void onNewMessage(Message message);
    }

    public static interface onNewFriendListener
    {
        public abstract void onNewFriend(User u);
    }

    Handler myHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    for (onNewFriendListener listener : friendListeners)
                        listener.onNewFriend(user1);
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("---------------OnCreate----------------");
        mApplication = (PushApplication) this.getApplication();
        mUser = mApplication.getUserDB().getUser();
        mUserIDs = mApplication.getUserDB().getUserIds();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        System.out.println("---------------StartService----------------");

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("------------执行-------------");
                UserDB userDB = PushApplication.getInstance().getUserDB();
                user = userDB.selectInfo("0001");
                user1 = userDB.selectInfo("000" + random.nextInt(10));

                // 漏网之鱼
                if (user == null)
                {
                    user = new User("0001", "1", "你好", "媚儿", R.drawable.h1, 1);
                    userDB.addUser(user);
                    // 通知监听的面板

                    Message message = new Message();
                    message.what = 0;
                    myHandler.sendMessage(message);
                }

                if (user1 == null)
                {
                    user1 = new User("000" + random.nextInt(10), "1", "在干嘛?", "喵喵", R.drawable.h2, 1);
                    userDB.addUser(user1);
                    // 通知监听的面板
                    Message message = new Message();
                    message.what = 0;
                    myHandler.sendMessage(message);
                }
            }
        }).start();


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 20 * 1000 * ADDUSERTIME; // 这是一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
