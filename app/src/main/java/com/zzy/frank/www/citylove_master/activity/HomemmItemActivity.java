package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;
import com.zzy.frank.www.citylove_master.util.TimeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

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
    @Bind(R.id.id_homemm_sayhi)
    RoundImageView idHomemmSayhi;
    @Bind(R.id.id_homemm_guanzhu)
    RoundImageView idHomemmGuanzhu;

    boolean isGuanZhu = false;
    @Bind(R.id.id_homemm_scroll)
    ScrollView idHomemmScroll;

    String id, pic, name, addr, distance, local, focus;
    String[] photos, space, data, info, condition;

    @Bind(R.id.id_homemm_pic)
    ImageView idHomemmPic;
    @Bind(R.id.id_homemm_date)
    TextView idHomemmDate;
    @Bind(R.id.id_homemm_focus)
    TextView idHomemmFocus;
    @Bind(R.id.id_homemm_whatfor)
    TextView idHomemmWhatfor;
    @Bind(R.id.id_homemm_guannian)
    TextView idHomemmGuannian;
    @Bind(R.id.id_homemm_firstmeet)
    TextView idHomemmFirstmeet;
    @Bind(R.id.id_homemm_aiaiplace)
    TextView idHomemmAiaiplace;
    @Bind(R.id.id_homemm_age)
    TextView idHomemmAge;
    @Bind(R.id.id_homemm_tall)
    TextView idHomemmTall;
    @Bind(R.id.id_homemm_shouru)
    TextView idHomemmShouru;
    @Bind(R.id.id_homemm_marrie)
    TextView idHomemmMarrie;
    @Bind(R.id.id_homemm_xueli)
    TextView idHomemmXueli;
    @Bind(R.id.id_homemm_zhiye)
    TextView idHomemmZhiye;
    @Bind(R.id.id_homemm_brith)
    TextView idHomemmBrith;
    @Bind(R.id.id_homemm_wigth)
    TextView idHomemmWigth;
    @Bind(R.id.id_homemm_xingzuo)
    TextView idHomemmXingzuo;
    @Bind(R.id.id_homemm_friendage)
    TextView idHomemmFriendage;
    @Bind(R.id.id_homemm_friendwhere)
    TextView idHomemmFriendwhere;
    @Bind(R.id.id_homemm_friendtall)
    TextView idHomemmFriendtall;
    @Bind(R.id.id_homemm_friendxueli)
    TextView idHomemmFriendxueli;
    @Bind(R.id.id_homemm_friendshouru)
    TextView idHomemmFriendshouru;
    @Bind(R.id.id_homemm_nicname1)
    TextView idHomemmNicname1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemm_item);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData()
    {
        toolBar.setTitle(name);
        idHomemmId.setText(id);
        idHomemmNickname.setText(name);
        idHomemmLocal.setText(addr);
        idHomemmPhoto.setText("相册 " + photos.length);
        idHomemmDate.setText("20" + TimeUtil.getTime(System.currentTimeMillis() - 30000));
        idHomemmFocus.setText("关注 " + focus);
        idHomemmLong.setText(distance);


        idHomemmWhatfor.setText(space[0]);
        idHomemmGuannian.setText(space[1]);
        idHomemmFirstmeet.setText(space[2]);
        idHomemmAiaiplace.setText(space[3]);


        idHomemmNicname1.setText(data[0]);
        idHomemmAge.setText(data[2]);
        idHomemmTall.setText(data[4]);
        idHomemmShouru.setText(data[5]);
        idHomemmMarrie.setText(data[5]);

        idHomemmXueli.setText(info[0]);
        idHomemmZhiye.setText(info[1]);
        idHomemmBrith.setText(info[2]);
        idHomemmWigth.setText(info[3]);
        idHomemmXingzuo.setText(info[4]);

        idHomemmFriendage.setText(condition[0]);
        idHomemmFriendwhere.setText(condition[1]);
        idHomemmFriendtall.setText(condition[2]);
        idHomemmFriendxueli.setText(condition[3]);
        idHomemmFriendshouru.setText(condition[4]);

        Glide.with(this)
                .load(pic)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(idHomemmPic);
    }

    private void initView()
    {
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

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pic = intent.getStringExtra("pic");
        name = intent.getStringExtra("name");
        addr = intent.getStringExtra("addr");
        local = intent.getStringExtra("local");
        distance = intent.getStringExtra("distance");
        focus = intent.getStringExtra("focus");

        photos = intent.getStringArrayExtra("photos");
        space = intent.getStringArrayExtra("space");
        data = intent.getStringArrayExtra("data");
        info = intent.getStringArrayExtra("info");
        condition = intent.getStringArrayExtra("condition");


    }

    @OnClick({R.id.id_homemm_photo, R.id.id_homemm_weixin, R.id.id_homemm_sendletter, R.id.id_homemm_sayhi, R.id.id_homemm_guanzhu, R.id.homemm_head4})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_homemm_photo:
                intent.setClass(this, GirlsPhotoActivity.class);
                intent.putExtra("photo", photos);
                startActivity(intent);
                break;
            case R.id.id_homemm_weixin:
                idHomemmScroll.scrollTo(0, 1600);
                break;
            case R.id.id_homemm_sendletter:
                // TODO: 2016/9/26 跳转聊天窗口
                break;
            case R.id.id_homemm_sayhi:
                idHomemmSayhi.setImageResource(R.drawable.hi_gray);
                break;
            case R.id.id_homemm_guanzhu:

                if (isGuanZhu == false)
                {
                    idHomemmGuanzhu.setImageResource(R.drawable.watch);
                    isGuanZhu = true;

                } else if (isGuanZhu == true)
                {
                    idHomemmGuanzhu.setImageResource(R.drawable.unwatch);
                    isGuanZhu = false;
                }
                break;
            case R.id.homemm_head4:

                break;
        }
    }

}
