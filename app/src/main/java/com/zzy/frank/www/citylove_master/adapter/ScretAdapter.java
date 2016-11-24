package com.zzy.frank.www.citylove_master.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.ScretItem;
import com.zzy.frank.www.citylove_master.util.StackBlurManager;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by pc on 2016/11/8.
 */
public class ScretAdapter extends BaseAdapter
{
    private List<ScretItem> mList;
    private Activity mContext;
    private LayoutInflater mInflater;
    private StackBlurManager mStackBlurManager;
    private CommentAdapter mCommentAdapter;

    public ScretAdapter(List<ScretItem> mList, Activity mContext)
    {
        this.mList = mList;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);

        System.out.println("----------msgs----------:"+mList.get(2).getMsgs().size());
    }

    @Override
    public int getCount()
    {
        return mList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        final VedioItemHolder holder;

        if (convertView == null)
        {
            holder = new VedioItemHolder();
            convertView = mInflater.inflate(R.layout.item_homemm_vedio, parent, false);

            holder.idHomemmSecrtIcon = (SimpleDraweeView) convertView.findViewById(R.id.id_homemm_secrt_icon);
            holder.idHomemmSecrtName = (TextView) convertView.findViewById(R.id.id_homemm_secrt_name);
            holder.idHomemmSecrtLocal = (TextView) convertView.findViewById(R.id.id_homemm_secrt_local);
            holder.idHomemmSecrtMessage = (TextView) convertView.findViewById(R.id.id_homemm_secrt_message);
            holder.idHomemmSecrtPhoto = (ImageView) convertView.findViewById(R.id.id_homemm_secrt_photo);
            holder.idHomemmSecrtMsg = (TextView) convertView.findViewById(R.id.id_homemm_secrt_msg);
            holder.idHomemmSecrtLinearMsg = (LinearLayout) convertView.findViewById(R.id.id_homemm_secrt_linear_msg);
            holder.picture = (SimpleDraweeView) convertView.findViewById(R.id.picture);
            holder.mScrollDisabledListView = (ListView) convertView.findViewById(R.id.id_homemm_secrt_msgs);
            holder.text = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);
        } else
        {
            holder = (VedioItemHolder) convertView.getTag();
        }

        Uri uri = Uri.parse(mList.get(position).getIcon());
        holder.idHomemmSecrtIcon.setImageURI(uri);

        System.out.println("------------------------------:" + mList.get(position).getNickname());

        holder.idHomemmSecrtName.setText(mList.get(position).getNickname());
        holder.idHomemmSecrtLocal.setText(mList.get(position).getAddr() + "." + mList.get(position).getAge());
        holder.idHomemmSecrtPhoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

//                Dialog_VIP dialogVip = new Dialog_VIP();
//                dialogVip.show(mContext);

//                Intent intent = new Intent();
//                intent.putExtra("videoPath","http://c.tv778.com//videos//1478593540957.mp4");
//                intent.setClass(mContext, PLVideoScretActivity.class);
//                mContext.startActivity(intent);

                JCFullScreenActivity.startActivity(mContext,
                        "http://c.tv778.com//videos//1478593540957.mp4",
                        JCVideoPlayerStandard.class, "嫂子真牛逼");


            }
        });
//        Glide.with(mContext).load(mList.get(position).getPic()).asBitmap().into(new SimpleTarget<Bitmap>()
//        {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
//            {
//                //从第三张照片开始模糊
//                mStackBlurManager = new StackBlurManager(resource);
//                mStackBlurManager.process(20);
//                holder.picture.setImageBitmap(mStackBlurManager.returnBlurredImage());
//            }
//        });

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(mList.get(position).getPic())).setProgressiveRenderingEnabled(true).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, mContext);
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(@Nullable Bitmap bitmap) {
                // You can use the bitmap in only limited ways
                // No need to do any cleanup.
                mStackBlurManager = new StackBlurManager(bitmap);
                mStackBlurManager.process(20);
                holder.picture.setImageBitmap(mStackBlurManager.returnBlurredImage());

            }
            @Override
            public void onFailureImpl(DataSource dataSource) {
                // No cleanup required here.
            }
        }, CallerThreadExecutor.getInstance());

        mCommentAdapter = new CommentAdapter(mContext,mList.get(position).getMsgs());
        holder.mScrollDisabledListView.setAdapter(mCommentAdapter);

        return convertView;
    }

    class VedioItemHolder
    {
        ListView mScrollDisabledListView;
        SimpleDraweeView idHomemmSecrtIcon;
        TextView idHomemmSecrtName;
        TextView idHomemmSecrtLocal;
        TextView idHomemmSecrtMessage;
        ImageView idHomemmSecrtPhoto;
        TextView idHomemmSecrtMsg;
        LinearLayout idHomemmSecrtLinearMsg;
        SimpleDraweeView picture;
        TextView text;
    }
}
