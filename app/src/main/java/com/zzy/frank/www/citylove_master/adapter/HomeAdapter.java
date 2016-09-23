package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.Homemm;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/9/18.
 */
public class HomeAdapter extends RecyclerView.Adapter
{

    private List<Homemm> mList;
    private Context context;

    //Onclik接口
    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public HomeAdapter(List<Homemm> mList, Context context)
    {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_homemm, parent, false);
        HomemmViewHolder holder = new HomemmViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        HomemmViewHolder homemmViewHolder = (HomemmViewHolder) holder;

        Glide.with(context)
                .load(mList.get(position).getPic())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(homemmViewHolder.idItemHomemmPic);
        homemmViewHolder.idItemHomemmName.setText(mList.get(position).getName());

        setUpItemEvent(homemmViewHolder);
    }

    protected void setUpItemEvent(final RecyclerView.ViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int layoutpostion = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutpostion);
                }
            });

            //longclick

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int layoutpostion = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutpostion);

                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class HomemmViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_item_homemm_pic)
        ImageView idItemHomemmPic;
        @Bind(R.id.id_item_homemm_name)
        TextView idItemHomemmName;

        public HomemmViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
