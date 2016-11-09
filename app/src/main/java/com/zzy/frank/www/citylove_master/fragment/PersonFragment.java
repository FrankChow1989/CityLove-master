package com.zzy.frank.www.citylove_master.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.GuanZhuActivity;
import com.zzy.frank.www.citylove_master.activity.PersonalInfoActivity;
import com.zzy.frank.www.citylove_master.activity.PhoneVerifyActivity;
import com.zzy.frank.www.citylove_master.activity.PhotoActivity;
import com.zzy.frank.www.citylove_master.activity.ShezhiActivity;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.activity.VIPUpdateActivity;
import com.zzy.frank.www.citylove_master.activity.VisitActivity;
import com.zzy.frank.www.citylove_master.activity.ZhengyouIfActivity;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends TakePhotoFragment implements View.OnClickListener
{
    View view;
    RoundImageView imageView;
    Uri imageUri;

    AlertDialog dialog;
    WindowManager m;
    Display d;

    TextView tv1, tv2;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);

        sp = getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);
        editor = sp.edit();
        String pic = sp.getString("headpic", "");
        imageView = (RoundImageView) view.findViewById(R.id.id_per_head);
        Glide.with(this)
                .load(pic)
                .error(R.drawable.icon_center_header)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        imageUri = Uri.fromFile(file);

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ShowDialog();
            }
        });

        return view;
    }

    private void showImg(ArrayList<TImage> images)
    {
        Glide.with(this)
                .load(images.get(0).getPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        editor.putString("headpic", images.get(0).getPath());
        editor.commit();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.liner_zuijin_vist, R.id.liner_guanzhu, R.id.linear_personalinfo, R.id.linear_zhengyouif, R.id.linear_shenzhi, R.id.id_person_vip, R.id.linear_photo, R.id.id_person_vip_lin, R.id.id_person_photo_lin})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.liner_zuijin_vist:
                intent.setClass(getActivity(), VisitActivity.class);
                break;
            case R.id.liner_guanzhu:
                intent.setClass(getActivity(), GuanZhuActivity.class);
                break;
            case R.id.linear_personalinfo:
                intent.setClass(getActivity(), PersonalInfoActivity.class);
                break;
            case R.id.linear_zhengyouif:
                intent.setClass(getActivity(), ZhengyouIfActivity.class);
                break;
            case R.id.linear_shenzhi:
                intent.setClass(getActivity(), ShezhiActivity.class);
                break;
            case R.id.id_person_vip:
                intent.setClass(getActivity(), VIPActivity.class);
                break;
            case R.id.linear_photo:
                intent.setClass(getActivity(), PhotoActivity.class);
                break;
            case R.id.id_person_vip_lin:
                intent.setClass(getActivity(), VIPUpdateActivity.class);
                break;
            case R.id.id_person_photo_lin:
                intent.setClass(getActivity(), PhoneVerifyActivity.class);
                break;
        }
        startActivity(intent);
    }

    private void configCompress(TakePhoto takePhoto)
    {
        int maxSize = Integer.parseInt("800");
        int maxPixel = Integer.parseInt("800");
        CompressConfig config = new CompressConfig.Builder().setMaxPixel(maxSize).setMaxPixel(maxPixel).create();
        takePhoto.onEnableCompress(config, true);
    }

    private CropOptions getCropOptions()
    {
        int height = Integer.parseInt("800");
        int width = Integer.parseInt("800");
        CropOptions.Builder builder = new CropOptions.Builder();

        builder.setOutputX(width).setOutputY(height);
        builder.setWithOwnCrop(true);
        return builder.create();
    }

    private void ShowDialog()
    {
        m = getActivity().getWindowManager();
        d = m.getDefaultDisplay();

        int width = d.getWidth();
        int height = d.getHeight();

        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        imageUri = Uri.fromFile(file);
        configCompress(getTakePhoto());

        dialog = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_pic_picker, null);
        dialog.setView(view, 0, 0, 0, 0);

        tv1 = (TextView) view.findViewById(R.id.id_dialog_camria);
        tv2 = (TextView) view.findViewById(R.id.id_dialog_photo);

        tv1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getTakePhoto().onPickFromCaptureWithCrop(imageUri, getCropOptions());
                dialog.dismiss();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getTakePhoto().onPickFromGalleryWithCrop(imageUri, getCropOptions());
                dialog.dismiss();
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(width / 2 + 200, height / 4 + 20);

    }

    @Override
    public void takeSuccess(TResult result)
    {
        showImg(result.getImages());
    }

    @Override
    public void takeFail(TResult result, String msg)
    {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel()
    {
        super.takeCancel();
    }
}
