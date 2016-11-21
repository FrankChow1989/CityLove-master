package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.Grils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/9/18.
 */
public class HomeAdapter extends RecyclerView.Adapter
{

    private List<Grils> mList;
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

    public HomeAdapter(List<Grils> mList, Context context)
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

        Uri uri = Uri.parse(mList.get(position).getIcon());
        homemmViewHolder.idItemHomemmPic.setImageURI(uri);


        homemmViewHolder.idItemHomemmName.setText(mList.get(position).getNickname() + "|" + mList.get(position).getAge() + "岁");
        setUpItemEvent(homemmViewHolder);
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

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class HomemmViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_item_homemm_pic)
        SimpleDraweeView idItemHomemmPic;
        @Bind(R.id.id_item_homemm_name)
        TextView idItemHomemmName;

        public HomemmViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
