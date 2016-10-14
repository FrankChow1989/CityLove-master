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

    String id, pic;

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
        getData();
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

        System.out.println("-----------homemmitem------:" + id);

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

    private void getData()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://oetlj49uy.qnssl.com/home_items" + "001" + ".txt";
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject obj)
                    {
                        System.out.println("---sssssssssssssss---:" + obj);
                        pasreData(obj);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.getMessage();
            }
        })
        {
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
            {
                JSONObject jsonObject;
                try
                {
                    jsonObject = new JSONObject(new String(response.data, "GBK"));
                    return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                }
            }
        };
//        objRequest.setTag("obj");
        queue.add(objRequest);
    }

    private void pasreData(JSONObject obj)
    {
        try
        {
            toolBar.setTitle(obj.getString("nickname"));
            idHomemmId.setText(obj.getString("id"));
            idHomemmNickname.setText(obj.getString("nickname"));
            idHomemmLocal.setText(obj.getString("local"));
            idHomemmPhoto.setText("相册 " + obj.getString("picnum"));
            idHomemmDate.setText("20" + TimeUtil.getTime(System.currentTimeMillis() - 30000));
            idHomemmFocus.setText("关注 " + obj.getString("focus"));
            idHomemmWhatfor.setText(obj.getString("whatfor"));
            idHomemmGuannian.setText(obj.getString("guannian"));
            idHomemmFirstmeet.setText(obj.getString("firstmeet"));
            idHomemmAiaiplace.setText(obj.getString("aiaiplace"));
            idHomemmNicname1.setText(obj.getString("nickname"));
            idHomemmAge.setText(obj.getString("age"));
            idHomemmTall.setText(obj.getString("tall"));
            idHomemmShouru.setText(obj.getString("shouru"));
            idHomemmMarrie.setText(obj.getString("marrie"));

            idHomemmXueli.setText(obj.getString("xueli"));
            idHomemmZhiye.setText(obj.getString("zhiye"));
            idHomemmBrith.setText(obj.getString("brith"));
            idHomemmWigth.setText(obj.getString("wigth"));
            idHomemmXingzuo.setText(obj.getString("xingzuo"));

            idHomemmFriendage.setText(obj.getString("friendage"));
            idHomemmFriendwhere.setText(obj.getString("friendwhere"));
            idHomemmFriendtall.setText(obj.getString("friendtall"));
            idHomemmFriendxueli.setText(obj.getString("friendxueli"));
            idHomemmFriendshouru.setText(obj.getString("friendshouru"));

            Glide.with(this)
                    .load(pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(idHomemmPic);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
