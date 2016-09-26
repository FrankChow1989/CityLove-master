package com.zzy.frank.www.citylove_master.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.zzy.frank.www.citylove_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdviseActivity extends AppCompatActivity
{

    @Bind(R.id.id_advise_edittext)
    EditText idAdviseEdittext;
    @Bind(R.id.id_advise_phone)
    EditText idAdvisePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advise);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.id_advise_back, R.id.id_advise_send})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_advise_back:
                break;
            case R.id.id_advise_send:
                break;
        }
    }
}
