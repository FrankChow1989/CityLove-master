package com.zzy.frank.www.citylove_master.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.adapter.ChatMessageAdapter;
import com.zzy.frank.www.citylove_master.bean.ChatMessage;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.dao.UserDB;
import com.zzy.frank.www.citylove_master.util.NetUtil;
import com.zzy.frank.www.citylove_master.util.T;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChattingActivity extends AppCompatActivity
{


    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.id_chatting_listview)
    ListView idChattingListview;
    @Bind(R.id.id_chatting_edit)
    EditText idChattingEdit;
    @Bind(R.id.ly_chat_bottom)
    RelativeLayout lyChatTitle;
    @Bind(R.id.ly_chat_bottom1)
    TextView lyChatBottom1;

    LinearLayout button1, button2;
    Button button;

    List<ChatMessage> mData = new ArrayList<ChatMessage>();
    String userId;

    private ChatMessageAdapter mAdapter;

    private PushApplication mApplication;
    private User mFromUser;
    private UserDB mUserDB;

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    Dialog dialog;
    Window dialogWindow;
    WindowManager m;
    Display d;
    WindowManager.LayoutParams p;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        ButterKnife.bind(this);
        mApplication = (PushApplication) getApplication();
        mUserDB = mApplication.getUserDB();
        sp = getSharedPreferences("isSend", MODE_PRIVATE);
        editor = sp.edit();

        Intent intent = getIntent();
        userId = intent.getStringExtra("userid");
        System.out.println("---userid---" + userId);

        if (TextUtils.isEmpty(userId))
        {
            finish();
        }

        mFromUser = mUserDB.getUser(userId);
        toolBar.setTitle(mFromUser.getNick());
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        initView();


        // TODO: 2016/10/19 信息动态
    }

    private void initView()
    {
        mData = mApplication.getMessageDB().find(mFromUser.getUserId(), 1, 10);
//        System.out.println("message:" + mData.get(0).getMessage() + "pic:" + mData.get(0).getPic_msg() + "record_path:" +
//                mData.get(0) + "isComing:" + mData.get(0).isComing() + "userId:" + mData.get(0).getUserId() +
//                "icon:" + mData.get(0).getIcon() + "msgType:" + mData.get(0).getMsgType() + "nickname:" + mData.get(0).getNickname()
//                + "readed:" + mData.get(0).isReaded() + "dateStr:" + mData.get(0).getDateStr()
//        );

        mAdapter = new ChatMessageAdapter(this, mData);
        idChattingListview.setAdapter(mAdapter);
        idChattingListview.setSelection(mData.size() - 1);


        System.out.println("-----------------sp_isSend--------------------:" + sp.getBoolean("isSend", false));

        if (sp.getBoolean("isSendMsg", false) == true)
        {

            lyChatTitle.setGravity(View.GONE);
            lyChatBottom1.setVisibility(View.VISIBLE);
        } else
        {
            lyChatTitle.setGravity(View.VISIBLE);
            lyChatBottom1.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.id_chatting_send, R.id.id_chatting_checkweixin, R.id.ly_chat_bottom1})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_chatting_send:

                String msg = idChattingEdit.getText().toString();
                if (TextUtils.isEmpty(msg))
                {
                    T.showShort(getApplicationContext(), "您还未填写消息呢!");
                    return;
                }

//                if (!NetUtil.isNetConnected(mApplication))
//                {
//                    T.showShort(mApplication, "当前无网络连接！");
//                    return;
//                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setComing(false);
                chatMessage.setDate(new Date());
                chatMessage.setMessage(msg);
                chatMessage.setNickname("月光使者");
                chatMessage.setUserId("1001");

                //消息存入数据库
                mApplication.getMessageDB().add(mFromUser.getUserId(), chatMessage);
                mData.add(chatMessage);

                mAdapter.notifyDataSetChanged();
                idChattingListview.setSelection(mData.size() - 1);
                idChattingEdit.setText("");

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // 得到InputMethodManager的实例
                if (imm.isActive())
                {
                    // 如果开启
                    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
                }

                editor.putBoolean("isSendMsg", true);
                editor.commit();

                lyChatTitle.setGravity(View.GONE);
                lyChatBottom1.setVisibility(View.VISIBLE);

                break;
            case R.id.id_chatting_checkweixin:

                dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_custem);
                dialogWindow = dialog.getWindow();

                button1 = (LinearLayout) dialogWindow.findViewById(R.id.id_dialog_year);
                button2 = (LinearLayout) dialogWindow.findViewById(R.id.id_dialog_month);
                button = (Button) dialogWindow.findViewById(R.id.id_dialog_button);

                button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                    }
                });

                m = getWindowManager();
                d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
                p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65
                dialogWindow.setAttributes(p);
                dialog.show();

                break;
            case R.id.ly_chat_bottom1:

                dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_custem);
                dialogWindow = dialog.getWindow();

                button1 = (LinearLayout) dialogWindow.findViewById(R.id.id_dialog_year);
                button2 = (LinearLayout) dialogWindow.findViewById(R.id.id_dialog_month);
                button = (Button) dialogWindow.findViewById(R.id.id_dialog_button);

                button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                    }
                });

                m = getWindowManager();
                d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
                p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65
                dialogWindow.setAttributes(p);
                dialog.show();

                break;
        }
    }
}
