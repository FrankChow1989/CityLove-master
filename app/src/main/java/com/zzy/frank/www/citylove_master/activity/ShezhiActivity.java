package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShezhiActivity extends AppCompatActivity
{

    @Bind(R.id.id_dangqian)
    TextView idDangqian;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.id_shezhi_back, R.id.id_tuichu_acount, R.id.id_shezhi_advise, R.id.id_shezhi_checkupdae, R.id.id_shezhi_activtyabout, R.id.id_shezhi_about, R.id.id_shezhi_cleardata})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_shezhi_back:
                break;
            case R.id.id_tuichu_acount:

                break;
            case R.id.id_shezhi_advise:

                intent.setClass(this, AdviseActivity.class);

                break;
            case R.id.id_shezhi_checkupdae:
                break;
            case R.id.id_shezhi_activtyabout:

                intent.setClass(this,ActivityAbout.class);

                break;
            case R.id.id_shezhi_about:
                break;
            case R.id.id_shezhi_cleardata:
                break;
        }

        startActivity(intent);
    }
}
