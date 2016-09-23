package com.zzy.frank.www.citylove_master.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

    @OnClick({R.id.id_homemm_photo, R.id.id_homemm_weixin, R.id.id_homemm_sendletter, R.id.id_homemm_sayhi, R.id.id_homemm_guanzhu})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_homemm_photo:
                break;
            case R.id.id_homemm_weixin:
                break;
            case R.id.id_homemm_sendletter:
                break;
            case R.id.id_homemm_sayhi:
                break;
            case R.id.id_homemm_guanzhu:
                break;
        }
    }
}
