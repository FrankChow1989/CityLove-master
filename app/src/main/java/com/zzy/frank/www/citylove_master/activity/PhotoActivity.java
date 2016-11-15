package com.zzy.frank.www.citylove_master.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.adapter.PhotoAdapter;
import com.zzy.frank.www.citylove_master.bean.MyPhoto;
import com.zzy.frank.www.citylove_master.util.T;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoActivity extends TakePhotoActivity
{

    @Bind(R.id.id_recy_photo)
    RecyclerView idRecyPhoto;
    PhotoAdapter photoAdapter;
    List<MyPhoto> myPhotos;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String[] user_pics;

    AlertDialog dialog;
    WindowManager m;
    Display d;

    TextView tv1, tv2;

    final int PIC_NUM = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        initView();
        photoAdapter = new PhotoAdapter(myPhotos, this);
        idRecyPhoto.setAdapter(photoAdapter);

        photoAdapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                if (position == 0)
                {
                    ShowDialog();
                }
            }

            @Override
            public void onItemLongClick(View view, final int position)
            {

                ArrayList<String> list = new ArrayList<String>();
                list.add("删除");

                if (position != 0)
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(PhotoActivity.this);
                    dialog.setAdapter(new ArrayAdapter<String>(PhotoActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, list), new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            myPhotos.remove(position);
                            photoAdapter.notifyDataSetChanged();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    Uri imageUri;

    private void ShowDialog()
    {
        m = getWindowManager();
        d = m.getDefaultDisplay();

        int width = d.getWidth();
        int height = d.getHeight();

        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        imageUri = Uri.fromFile(file);
        configCompress(getTakePhoto());

        dialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
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
                getTakePhoto().onPickMultipleWithCrop(PIC_NUM, getCropOptions());
                dialog.dismiss();
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(width / 2 + 200, height / 3 + 20);
    }


    private void initView()
    {
        Resources r = this.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(r, R.drawable.take_photo_bt);
        myPhotos = new ArrayList<>();
        MyPhoto myPhoto = new MyPhoto();
        myPhoto.setPhoto(bmp);
        myPhotos.add(myPhoto);

        sp = getSharedPreferences("user", MODE_PRIVATE);
        editor = sp.edit();

        user_pics = getSharedPreference("photo");

        for (int i = 1; i < user_pics.length; i++)
        {
            MyPhoto photo = new MyPhoto();
            photo.setLocal_photo(user_pics[i]);
            myPhotos.add(photo);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        //设置RecyclerView的布局管理
        idRecyPhoto.setLayoutManager(layoutManager);

        //设置RecyclerView的Item间分割线
//        DividerItemDecoration decor = new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL_LIST);
//        mRecyclerView.addItemDecoration(decor);
    }

    @OnClick({R.id.id_photo_back, R.id.id_photo_edit})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_photo_back:
                break;
            case R.id.id_photo_edit:
                break;
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        user_pics = new String[myPhotos.size()];
        for (int i = 1; i < myPhotos.size(); i++)
        {
            user_pics[i] = myPhotos.get(i).getLocal_photo();
        }
        setSharedPreference1("photo", user_pics);
    }

    /**
     *
     *
     */
    public void setSharedPreference1(String key, String[] values)
    {
        String regularEx = "#";
        String str = "";
        if (values != null && values.length > 0)
        {
            for (String value : values)
            {
                str += value;
                str += regularEx;
            }
            editor = sp.edit();
            editor.putString(key, str);
            editor.commit();
        }
    }

    /**
     * sp取数据;
     */

    public String[] getSharedPreference(String key)
    {
        String regularEx = "#";
        String[] str = null;
        String values;
        sp = getSharedPreferences("user", MODE_PRIVATE);
        values = sp.getString(key, "");
        str = values.split(regularEx);
        return str;
    }

    @Override
    public void takeCancel()
    {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg)
    {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result)
    {
        super.takeSuccess(result);
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images)
    {
        //处理图片
        for (int i = 0; i < images.size(); i++)
        {
            MyPhoto photo = new MyPhoto();
            photo.setLocal_photo(images.get(i).getPath());
            myPhotos.add(photo);
            photoAdapter.notifyDataSetChanged();
        }
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
}
