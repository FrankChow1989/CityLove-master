package com.zzy.frank.www.citylove_master.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.adapter.ChatMessageAdapter;
import com.zzy.frank.www.citylove_master.bean.ChatMessage;
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

    List<ChatMessage> mData = new ArrayList<ChatMessage>();
    private ChatMessageAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        ButterKnife.bind(this);

        toolBar.setTitle("月光使者");
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
        initEvent();

    }

    private void initEvent()
    {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setComing(true);
        chatMessage.setDate(new Date());
        chatMessage.setMessage("你好!");
        chatMessage.setNickname("月光使者");
        chatMessage.setUserId("100");
        mData.add(chatMessage);
    }

    private void initView()
    {
        mAdapter = new ChatMessageAdapter(this, mData);
        idChattingListview.setAdapter(mAdapter);
    }

    @OnClick({R.id.id_chatting_send, R.id.id_chatting_checkweixin})
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

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setComing(false);
                chatMessage.setDate(new Date());
                chatMessage.setMessage(msg);
                chatMessage.setNickname("月光使者");
                chatMessage.setUserId("100");

                mData.add(chatMessage);

                mAdapter.notifyDataSetChanged();
                idChattingListview.setSelection(mData.size() - 1);
                idChattingEdit.setText("");

                break;
            case R.id.id_chatting_checkweixin:
                break;
        }
    }
}
