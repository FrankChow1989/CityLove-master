package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.MainActivity;
import com.zzy.frank.www.citylove_master.R;

import java.text.DecimalFormat;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TellThemActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.id_tellthem_age1)
    TextView idTellthemAge1;
    @Bind(R.id.id_tellthem_high1)
    TextView idTellthemHigh1;
    @Bind(R.id.id_tellthem_length1)
    TextView idTellthemLength1;
    @Bind(R.id.id_tellthem_time1)
    TextView idTellthemTime1;
    @Bind(R.id.id_tellthem_age2)
    TextView idTellthemAge2;
    @Bind(R.id.id_tellthem_high2)
    TextView idTellthemHigh2;
    @Bind(R.id.id_tellthem_length2)
    TextView idTellthemLength2;
    @Bind(R.id.id_tellthem_time2)
    TextView idTellthemTime2;
    @Bind(R.id.id_tellthem_age3)
    TextView idTellthemAge3;
    @Bind(R.id.id_tellthem_high3)
    TextView idTellthemHigh3;
    @Bind(R.id.id_tellthem_length3)
    TextView idTellthemLength3;
    @Bind(R.id.id_tellthem_time3)
    TextView idTellthemTime3;
    @Bind(R.id.id_tellthem_age4)
    TextView idTellthemAge4;
    @Bind(R.id.id_tellthem_high4)
    TextView idTellthemHigh4;
    @Bind(R.id.id_tellthem_length4)
    TextView idTellthemLength4;
    @Bind(R.id.id_tellthem_time4)
    TextView idTellthemTime4;

    Random random = new Random();
    Random r = new Random();

    DecimalFormat df = new DecimalFormat("#.00");//保留2位小数

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell_them);
        ButterKnife.bind(this);
        toolBar.setTitle("同城送爱");
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

        initData();
    }

    private void initData()
    {
        idTellthemAge1.setText(random.nextInt(28) % (28 - 17 + 1) + 17 + "岁");
        idTellthemAge2.setText(random.nextInt(28) % (28 - 17 + 1) + 17 + "岁");
        idTellthemAge3.setText(random.nextInt(28) % (28 - 17 + 1) + 17 + "岁");
        idTellthemAge4.setText(random.nextInt(28) % (28 - 17 + 1) + 17 + "岁");

        idTellthemHigh1.setText(random.nextInt(172) % (172 - 157 + 1) + 157 + "cm");
        idTellthemHigh2.setText(random.nextInt(172) % (172 - 157 + 1) + 157 + "cm");
        idTellthemHigh3.setText(random.nextInt(172) % (172 - 157 + 1) + 157 + "cm");
        idTellthemHigh4.setText(random.nextInt(172) % (172 - 157 + 1) + 157 + "cm");

        idTellthemLength1.setText(df.format(r.nextDouble() * (4.3 - 0.3) + 0.3) + "km");
        idTellthemLength2.setText(df.format(r.nextDouble() * (4.3 - 0.3) + 0.3) + "km");
        idTellthemLength3.setText(df.format(r.nextDouble() * (4.3 - 0.3) + 0.3) + "km");
        idTellthemLength4.setText(df.format(r.nextDouble() * (4.3 - 0.3) + 0.3) + "km");

        idTellthemTime1.setText(random.nextInt(5) % (5 - 1 + 1) + 1 + "分钟");
        idTellthemTime2.setText(random.nextInt(5) % (5 - 1 + 1) + 1 + "分钟");
        idTellthemTime3.setText(random.nextInt(5) % (5 - 1 + 1) + 1 + "分钟");
        idTellthemTime4.setText(random.nextInt(5) % (5 - 1 + 1) + 1 + "分钟");

    }

    @OnClick(R.id.id_bt_tellthem)
    public void onClick()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
