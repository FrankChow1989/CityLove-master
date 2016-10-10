package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomemmItemActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.id_homemm_id)
    TextView idHomemmId;
    @Bind(R.id.id_homemm_nickname)
    TextView idHomemmNickname;
    @Bind(R.id.id_homemm_local)
    TextView idHomemmLocal;
    @Bind(R.id.id_homemm_long)
    TextView idHomemmLong;
    @Bind(R.id.id_homemm_photo)
    TextView idHomemmPhoto;
    @Bind(R.id.id_homemm_weixin)
    TextView idHomemmWeixin;
    @Bind(R.id.id_homemm_sendletter)
    RoundImageView idHomemmSendletter;
    @Bind(R.id.id_homemm_sayhi)
    RoundImageView idHomemmSayhi;
    @Bind(R.id.id_homemm_guanzhu)
    RoundImageView idHomemmGuanzhu;

    boolean isGuanZhu = false;
    @Bind(R.id.id_homemm_scroll)
    ScrollView idHomemmScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemm_item);
        ButterKnife.bind(this);
        initView();
    }

    private void initView()
    {
        toolBar.setTitle("晴天娃娃");
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

    }

    @OnClick({R.id.id_homemm_photo, R.id.id_homemm_weixin, R.id.id_homemm_sendletter, R.id.id_homemm_sayhi, R.id.id_homemm_guanzhu, R.id.homemm_head4})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_homemm_photo:
                intent.setClass(this, GirlsPhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.id_homemm_weixin:
                idHomemmScroll.scrollTo(0, 1600);
                break;
            case R.id.id_homemm_sendletter:
                // TODO: 2016/9/26 跳转聊天窗口
                break;
            case R.id.id_homemm_sayhi:
                idHomemmSayhi.setImageResource(R.mipmap.ic_launcher);
                break;
            case R.id.id_homemm_guanzhu:

                if (isGuanZhu == false)
                {
                    idHomemmGuanzhu.setImageResource(R.mipmap.ic_launcher);
                    isGuanZhu = true;

                } else if (isGuanZhu == true)
                {
                    idHomemmGuanzhu.setImageResource(R.drawable.a3);
                    isGuanZhu = false;
                }
                break;
            case R.id.homemm_head4:



                break;
        }
    }
}
