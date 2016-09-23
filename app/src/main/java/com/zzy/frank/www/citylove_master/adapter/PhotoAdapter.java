package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.MyPhoto;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/9/22.
 */
public class PhotoAdapter extends RecyclerView.Adapter
{
    private List<MyPhoto> photos;
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


    public PhotoAdapter(List<MyPhoto> photos, Context context)
    {
        this.photos = photos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;

        if (position == 0)
        {
            photoViewHolder.idItemPhoto.setImageBitmap(photos.get(position).getPhoto());
        }

        System.out.println("------loacal------" + photos.get(position).getLocal_photo());

        Glide.with(context)
                .load(photos.get(position).getLocal_photo())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photoViewHolder.idItemPhoto);

        setUpItemEvent(photoViewHolder);
    }

    @Override
    public int getItemCount()
    {
        return photos.size();
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

    static class PhotoViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_item_photo)
        ImageView idItemPhoto;

        public PhotoViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
