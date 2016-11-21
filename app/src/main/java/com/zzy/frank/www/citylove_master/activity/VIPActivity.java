package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zzy.frank.www.citylove_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VIPActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.id_vip_crown)
    SimpleDraweeView idVipCrown;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        ButterKnife.bind(this);

        toolBar.setTitle("VIP");
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

        Uri uri = Uri.parse("res://com.zzy.frank.www.citylove_master/" + R.drawable.crown);
        idVipCrown.setImageURI(uri);
    }

    @OnClick({R.id.liner_vip, R.id.id_bt_kefu})
    public void onClick(View view)
    {
        Intent intent = new Intent();

        switch (view.getId())
        {
            case R.id.liner_vip:
                intent.setClass(this, StartVIPActivity.class);
                break;
            case R.id.id_bt_kefu:
                intent.setClass(this, KefuChattingActivity.class);
                break;
        }
        startActivity(intent);
    }
}
