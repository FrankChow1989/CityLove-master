package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.MainActivity;
import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.server.SendMsgORAddFriends;
import com.zzy.frank.www.citylove_master.util.ObjectAnim;
import com.zzy.frank.www.citylove_master.util.ValueAnim;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity
{

    boolean isShow = true;
    @Bind(R.id.start_title)
    TextView startTitle;
    @Bind(R.id.start_content)
    TextView startContent;
    @Bind(R.id.id_wel_rg)
    RadioGroup idWelRg;

    private int width;

    PushApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mApplication = (PushApplication) this.getApplication();
        getWindowData();

        idWelRg.setVisibility(View.INVISIBLE);

        if (isShow)
        {
            ObjectAnim.startObj(startTitle, 0.0f, 1.0f);
            isShow = false;
        } else
        {
            ObjectAnim.startObj(startTitle, 1.0f, 0.0f);
            isShow = true;
        }

        new Handler().postDelayed(new Runnable()
        {

            public void run()
            {
                idWelRg.setVisibility(View.VISIBLE);
                ValueAnim.startValue(startContent, "x", 0, width - 120);
                ObjectAnim.startObj(idWelRg, 0.0f, 1.0f);
            }

        }, 1000);


        //开启服务;ss
        Intent intent = new Intent(this, SendMsgORAddFriends.class);
        startService(intent);


        //添加聊天MM
//        if (mUserIDs.size() == 0)
//        {
//            User user = new User("0001", "1", "你好", "媚儿", R.drawable.h1, 1);
//            User user1 = new User("0002", "1", "在干嘛?", "吉美", R.drawable.h0, 1);
//            User user2 = new User("0003", "1", "咋不回我了?", "月光使者", R.drawable.h2, 1);
//            User user3 = new User("0004", "1", "加个微信吧", "风一样的女子", R.drawable.h3, 1);
//            User user4 = new User("0005", "1", "给我说说吧!", "happy", R.drawable.h4, 1);
//            User user5 = new User("0006", "1", "你是做什么的?", "寂寞的星星", R.drawable.h5, 1);
//            mUser.add(user);
//            mUser.add(user1);
//            mUser.add(user2);
//            mUser.add(user4);
//            mUser.add(user3);
//            mUser.add(user5);
//            mApplication.getUserDB().addUser(mUser);
//        }
//
//        System.out.println("------mUser---1---:" + mUser.size());
//
//
//        ChatMessage chatMessage = new ChatMessage("啦啦啦啦啦啦~", true,
//                "0001", R.drawable.h1, "媚儿", false,
//                TimeUtil.getTime(System.currentTimeMillis()));
//        ChatMessage chatMessage1 = new ChatMessage("在干嘛呢？", true,
//                "0002", R.drawable.h0, "洁儿", false,
//                TimeUtil.getTime(System.currentTimeMillis()));
//        ChatMessage chatMessage2 = new ChatMessage("你喜欢我吗？", true,
//                "0003", R.drawable.h2, "月光使者", false,
//                TimeUtil.getTime(System.currentTimeMillis()));
//        ChatMessage chatMessage3 = new ChatMessage("你是做什么的？", true,
//                "0004", R.drawable.h3, "风一样的女子", false,
//                TimeUtil.getTime(System.currentTimeMillis()));
//
//        mApplication.getMessageDB().add("0001", chatMessage);
//        mApplication.getMessageDB().add("0002", chatMessage1);
//        mApplication.getMessageDB().add("0003", chatMessage2);
//        mApplication.getMessageDB().add("0004", chatMessage3);

    }

    private void getWindowData()
    {
        WindowManager manager = getWindowManager();
        width = manager.getDefaultDisplay().getWidth();
    }

    @OnClick({R.id.id_login_qq, R.id.id_login_weixin})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_login_qq:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.id_login_weixin:
                break;
        }
        finish();
    }
}
