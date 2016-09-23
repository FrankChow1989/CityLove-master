package com.zzy.frank.www.citylove_master.activity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.adapter.PhotoAdapter;
import com.zzy.frank.www.citylove_master.bean.MyPhoto;
import com.zzy.frank.www.citylove_master.galleryfinal.listener.GlidePauseOnScrollListener;
import com.zzy.frank.www.citylove_master.galleryfinal.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class PhotoActivity extends AppCompatActivity
{

    @Bind(R.id.id_recy_photo)
    RecyclerView idRecyPhoto;

    PhotoAdapter photoAdapter;
    List<MyPhoto> myPhotos;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    String[] user_pics;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        initView();

        initData();

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
            public void onItemLongClick(View view, int position)
            {
            }
        });

    }

    //初始化数据
    private void initData()
    {
        mPhotoList = new ArrayList<>();
    }

    //图片选择
    private List<PhotoInfo> mPhotoList;
    private boolean muti;
    ThemeConfig themeConfig = null;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;


    private void ShowDialog()
    {
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        ImageLoader imageLoader;
        PauseOnScrollListener pauseOnScrollListener = null;
        imageLoader = new GlideImageLoader();
        pauseOnScrollListener = new GlidePauseOnScrollListener(false, true);

        muti = false;
//        int maxSize = 1;
//        functionConfigBuilder.setMutiSelectMaxSize(maxSize);
        themeConfig = ThemeConfig.DEFAULT;
        final boolean mutiSelect = muti;
        functionConfigBuilder.setEnableEdit(true);
        functionConfigBuilder.setRotateReplaceSource(false);
        functionConfigBuilder.setEnableCrop(true);
        functionConfigBuilder.setCropSquare(true);
        functionConfigBuilder.setCropReplaceSource(false);
        functionConfigBuilder.setForceCrop(true);
        functionConfigBuilder.setForceCropEdit(true);
        functionConfigBuilder.setEnableCamera(true);
        functionConfigBuilder.setEnablePreview(true);
        functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
        final FunctionConfig functionConfig = functionConfigBuilder.build();

        CoreConfig coreConfig = new CoreConfig.Builder(PhotoActivity.this, imageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(pauseOnScrollListener)
                .setNoAnimcation(true)
                .build();
        GalleryFinal.init(coreConfig);

        ActionSheet.createBuilder(PhotoActivity.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消(Cancel)").setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener()
                             {
                                 @Override
                                 public void onDismiss(ActionSheet actionSheet, boolean isCancel)
                                 {
                                 }

                                 @Override
                                 public void onOtherButtonClick(ActionSheet actionSheet, int index)
                                 {
                                     String path = "/sdcard/pk1-2.jpg";

                                     switch (index)
                                     {
                                         case 0:
                                             System.out.println("----------------------------" + mutiSelect);
                                             if (mutiSelect)
                                             {
                                                 GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                             } else
                                             {
                                                 GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                             }
                                             break;
                                         case 1:
                                             GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                                             break;
                                         default:
                                             break;
                                     }
                                 }
                             }
                ).show();
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback()
    {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList)
        {
            if (resultList != null)
            {
                mPhotoList.addAll(resultList);
            }

            MyPhoto myPhoto = new MyPhoto();
            myPhoto.setId("1");
            myPhoto.setLocal_photo(mPhotoList.get(mPhotoList.size() - 1).getPhotoPath());
            myPhotos.add(myPhoto);
            photoAdapter.notifyDataSetChanged();

//            for (int i = 0; i < mPhotoList.size(); i++)
//            {
//                user_pics[i] = mPhotoList.get(i).getPhotoPath();
//            }
//            setSharedPreference(myPhoto.getId(), user_pics);

        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg)
        {
            Toast.makeText(PhotoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };


    private void initView()
    {
        Resources r = this.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(r, R.drawable.h1);
        myPhotos = new ArrayList<>();
        MyPhoto myPhoto = new MyPhoto();
        myPhoto.setPhoto(bmp);
        myPhotos.add(myPhoto);

        sp = getSharedPreferences("user", MODE_PRIVATE);
        editor = sp.edit();


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

    /**
     * sp�������;
     */

    public void setSharedPreference(String key, String[] values)
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

}
