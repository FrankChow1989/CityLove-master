package com.zzy.frank.www.citylove_master.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzy.frank.www.citylove_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/10/19.
 */
public class ChattingSeePicActivity extends AppCompatActivity
{
    String mPicUrl;
    @Bind(R.id.id_chat_seepic)
    ImageView idChatSeepic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_see_pic);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mPicUrl = intent.getStringExtra("pic");

        Uri uri = Uri.parse(mPicUrl);
        idChatSeepic.setImageURI(uri);

    }
}
