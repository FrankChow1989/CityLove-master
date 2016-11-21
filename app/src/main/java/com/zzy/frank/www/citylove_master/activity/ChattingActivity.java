package com.zzy.frank.www.citylove_master.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.Recorder.AudioRecorderButton;
import com.zzy.frank.www.citylove_master.Recorder.MediaManager;
import com.zzy.frank.www.citylove_master.adapter.ChatMessageAdapter;
import com.zzy.frank.www.citylove_master.bean.ChatMessage;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.dao.UserDB;
import com.zzy.frank.www.citylove_master.face.FaceRelativeLayout;
import com.zzy.frank.www.citylove_master.ui.Dialog_VIP;
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
    @Bind(R.id.et_sendmessage)
    EditText idChattingEdit;
    @Bind(R.id.face_kefu_chat_bottom)
    FaceRelativeLayout lyChatTitle;
    @Bind(R.id.ly_chat_bottom1)
    TextView lyChatBottom1;
    @Bind(R.id.btn_send)
    Button btnSend;
    @Bind(R.id.et_sendmessage_fram)
    FrameLayout etSendmessageFram;
    @Bind(R.id.id_chat_img_voice)
    ImageButton idChatImgVoice;
    @Bind(R.id.id_bt_chat_voice)
    AudioRecorderButton idBtChatVoice;
    @Bind(R.id.ll_facechoose)
    RelativeLayout llFacechoose;
    @Bind(R.id.id_chat_img)
    ImageButton idChatImg;
    @Bind(R.id.id_extend_img)
    ImageButton idExtendImg;
    @Bind(R.id.id_chat_round)
    SimpleDraweeView id_chat_round;

    List<ChatMessage> mData = new ArrayList<ChatMessage>();
    String userId;
    private ChatMessageAdapter mAdapter;
    private PushApplication mApplication;
    private User mFromUser;
    private UserDB mUserDB;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

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

        idChattingEdit.addTextChangedListener(textWatcher);

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


        idBtChatVoice.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener()
        {
            @Override
            public void onFinish(float seconds, String filePath)
            {
                ChatMessage recorder = new ChatMessage();
                recorder.setComing(false);
                recorder.setNickname("月光使者");
                recorder.setUserId("1001");
                recorder.setMsgType("2");
                recorder.setRecord_timelength((int) seconds);
                recorder.setRecord_path(filePath);
                recorder.setDate(new Date());
                mApplication.getMessageDB().add(mFromUser.getUserId(), recorder);
                mData.add(recorder);
                mAdapter.notifyDataSetChanged();

                lyChatBottom1.setVisibility(View.VISIBLE);
            }
        });

        Uri uri = Uri.parse(mFromUser.getHeadIcon());
        id_chat_round.setImageURI(uri);
    }


    private TextWatcher textWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            btnSend.setVisibility(View.VISIBLE);
            idExtendImg.setVisibility(View.GONE);

            if ("".equals(idChattingEdit.getText().toString()))
            {
                btnSend.setVisibility(View.GONE);
                idExtendImg.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s)
        {
        }
    };


    @OnClick({R.id.btn_send, R.id.id_chatting_checkweixin, R.id.ly_chat_bottom1, R.id.chatting_zengsong, R.id.id_chat_img, R.id.id_chat_img_voice})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_send:

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
                chatMessage.setNickname("月光使者");
                chatMessage.setUserId("1001");
                chatMessage.setMsgType("0");
                chatMessage.setDate(new Date());
                chatMessage.setMessage(msg);

                //消息存入数据库
                mApplication.getMessageDB().add(mFromUser.getUserId(), chatMessage);
                mData.add(chatMessage);
                mAdapter.notifyDataSetChanged();
                idChattingEdit.setText("");

                editor.putBoolean("isSendMsg", true);
                editor.commit();

                llFacechoose.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // 得到InputMethodManager的实例
                if (imm.isActive())
                {
                    // 如果开启
                    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
                }

                lyChatBottom1.setVisibility(View.VISIBLE);
                break;
            case R.id.id_chatting_checkweixin:
                Dialog_VIP.show(this);
                break;
            case R.id.ly_chat_bottom1:
                Dialog_VIP.show(this);
                break;
            case R.id.chatting_zengsong:
                Dialog_VIP.show(this);
                break;
            case R.id.id_chat_img:
                etSendmessageFram.setVisibility(View.GONE);
                idChatImg.setVisibility(View.GONE);
                btnSend.setVisibility(View.GONE);
                idExtendImg.setVisibility(View.VISIBLE);
                idChatImgVoice.setVisibility(View.VISIBLE);
                idBtChatVoice.setVisibility(View.VISIBLE);

                InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(idChattingEdit.getWindowToken(), 0);

                break;
            case R.id.id_chat_img_voice:
                etSendmessageFram.setVisibility(View.VISIBLE);
                idChatImg.setVisibility(View.VISIBLE);
                idChatImgVoice.setVisibility(View.GONE);
                idBtChatVoice.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && ((FaceRelativeLayout) findViewById(R.id.face_kefu_chat_bottom))
                .hideFaceView())
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        MediaManager.release();
    }
}
