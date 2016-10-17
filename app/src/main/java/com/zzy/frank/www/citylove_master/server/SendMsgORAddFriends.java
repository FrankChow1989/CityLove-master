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
import com.zzy.frank.www.citylove_master.bean.ChatMessage;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.dao.MessageDB;
import com.zzy.frank.www.citylove_master.dao.UserDB;
import com.zzy.frank.www.citylove_master.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pc on 2016/10/11.
 */
public class SendMsgORAddFriends extends Service
{

    PushApplication mApplication;
    List<String> mUserIDs = new ArrayList<>();
    final int ADDUSERTIME = 1;

    public static ArrayList<onNewMessageListener> msgListeners = new ArrayList<onNewMessageListener>();
    public static ArrayList<onNewFriendListener> friendListeners = new ArrayList<onNewFriendListener>();

    Random random = new Random();

    int id;

    User user;
    ChatMessage msgs;

    public static interface onNewMessageListener
    {
        public abstract void onNewMessage(ChatMessage message);
    }

    public static interface onNewFriendListener
    {
        public abstract void onNewFriend(User u);
    }

    Handler myHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if (msg.what == 0)
            {
                for (onNewFriendListener listener : friendListeners)
                {
                    listener.onNewFriend(user);
                }
                for (onNewMessageListener listener : msgListeners)
                {
                    listener.onNewMessage(msgs);
                }
            } else if (msg.what == 1)
            {
                for (onNewMessageListener listener : msgListeners)
                {
                    listener.onNewMessage(msgs);
                }
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
        mUserIDs = mApplication.getUserDB().getUserIds();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("------------执行-------------");
                id = random.nextInt(10);
                UserDB userDB = PushApplication.getInstance().getUserDB();
                user = userDB.selectInfo("000" + id);

                System.out.println("-------------id----------------:" + id);

                // 漏网之鱼
                if (user == null)
                {
                    user = new User("000" + id, "1", "", "媚儿", R.drawable.a3, 1);
                    userDB.addUser(user);
                    // 将新来的消息进行存储
                    msgs = new ChatMessage("哈哈", "https://oetlj49uy.qnssl.com/ce.jpg", "", true, "000" + id, R.drawable.h1, "1", "媚儿", false, TimeUtil.getTime(System.currentTimeMillis()));
                    PushApplication.getInstance().getMessageDB()
                            .add("000" + id, msgs);

                    // 通知监听的面板
                    Message message = new Message();
                    message.what = 0;
                    myHandler.sendMessage(message);
                } else if (user != null)
                {
                    msgs = new ChatMessage("哈哈", "https://oetlj49uy.qnssl.com/ce.jpg", "", true, "000" + id, R.drawable.h3, "2", "媚儿", false, TimeUtil.getTime(System.currentTimeMillis()));
                    PushApplication.getInstance().getMessageDB()
                            .add("000" + id, msgs);

                    // 通知监听的面板
                    Message message = new Message();
                    message.what = 1;
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
