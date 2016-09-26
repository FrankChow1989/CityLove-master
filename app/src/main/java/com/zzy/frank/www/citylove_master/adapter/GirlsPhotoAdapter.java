package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zzy.frank.www.citylove_master.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/9/26.
 */
public class GirlsPhotoAdapter extends RecyclerView.Adapter
{

    private String[] mList;
    private Context context;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        GirlsPhotoViewHolder viewHolder = (GirlsPhotoViewHolder) holder;

//        Glide.with(context)
//                .load(mList[position])
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(viewHolder.idItemGirlspic);

        Uri uri = Uri.parse(mList[position]);
        viewHolder.idItemGirlspic.setImageURI(uri);


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
