package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.zzy.frank.www.citylove_master.util.StackBlurManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/9/26.
 */
public class GirlsPhotoAdapter extends RecyclerView.Adapter
{

    private String[] mList;
    private Context context;

    private StackBlurManager mStackBlurManager;

    //Onclik接口
    public interface OnItemClickListener
    {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener = listener;
    }

    public GirlsPhotoAdapter(String[] mList, Context context)
    {
        this.mList = mList;
        this.context = context;

        System.out.println("---------size---------" + mList.length);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_girlphoto, parent, false);
        GirlsPhotoViewHolder holder = new GirlsPhotoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        final GirlsPhotoViewHolder viewHolder = (GirlsPhotoViewHolder) holder;

//        Glide.with(context)
//                .load(mList[position])
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(viewHolder.idItemGirlspic);

//        Glide.with(context).load(mList[position]).asBitmap().into(new SimpleTarget<Bitmap>()
//        {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
//            {
//                //从第三张照片开始模糊
//                if (position > 1)
//                {
//                    mStackBlurManager = new StackBlurManager(resource);
//                    mStackBlurManager.process(20);
//                    viewHolder.idItemGirlspic.setImageBitmap(mStackBlurManager.returnBlurredImage());
//                }else {
//                    viewHolder.idItemGirlspic.setImageBitmap(resource);
//                }
//            }
//        });

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(mList[position])).setProgressiveRenderingEnabled(true).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(@Nullable Bitmap bitmap) {
                // You can use the bitmap in only limited ways
                // No need to do any cleanup.
                if (position > 1)
                {
                    mStackBlurManager = new StackBlurManager(bitmap);
                    mStackBlurManager.process(20);
                    viewHolder.idItemGirlspic.setImageBitmap(mStackBlurManager.returnBlurredImage());
                }else {
                    viewHolder.idItemGirlspic.setImageBitmap(bitmap);
                }

            }
            @Override
            public void onFailureImpl(DataSource dataSource) {
                // No cleanup required here.
            }
        }, CallerThreadExecutor.getInstance());

//        Uri uri = Uri.parse(mList[position]);
//        viewHolder.idItemGirlspic.setImageURI(uri);

        setUpItemEvent(viewHolder);
    }

    @Override
    public int getItemCount()
    {
        return mList.length;
    }

    protected void setUpItemEvent(final RecyclerView.ViewHolder holder)
    {
        if (mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    int layoutpostion = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutpostion);
                }
            });

            //longclick
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {

                    int layoutpostion = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutpostion);

                    return false;
                }
            });
        }
    }

    static class GirlsPhotoViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_item_girlspic)
        SimpleDraweeView idItemGirlspic;

        public GirlsPhotoViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
