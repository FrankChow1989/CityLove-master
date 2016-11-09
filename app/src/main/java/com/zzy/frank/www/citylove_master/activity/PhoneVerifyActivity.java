package com.zzy.frank.www.citylove_master.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zzy.frank.www.citylove_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhoneVerifyActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);
        ButterKnife.bind(this);
        init();
    }

    private void init()
    {
        toolBar.setTitle("手机验证");
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
}
