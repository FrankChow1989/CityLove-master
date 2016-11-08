package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.ScretItem;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/11/8.
 */
public class ScretAdapter extends RecyclerView.Adapter
{
    private List<ScretItem> mList;
    private Context mContext;

    public ScretAdapter(List<ScretItem> mList, Context mContext)
    {
        this.mList = mList;
        this.mContext = mContext;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_homemm_vedio, parent, false);
        VedioItemHolder holder = new VedioItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        VedioItemHolder viewHolder = (VedioItemHolder) holder;

        Glide.with(mContext)
                .load(mList.get(position).getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.idHomemmSecrtIcon);

        System.out.println("------------------------------:" + mList.get(position).getNickname());

        viewHolder.idHomemmSecrtName.setText(mList.get(position).getNickname());
        viewHolder.idHomemmSecrtLocal.setText(mList.get(position).getAddr() + "." + mList.get(position).getAge());

    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class VedioItemHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_homemm_secrt_icon)
        RoundImageView idHomemmSecrtIcon;
        @Bind(R.id.id_homemm_secrt_name)
        TextView idHomemmSecrtName;
        @Bind(R.id.id_homemm_secrt_local)
        TextView idHomemmSecrtLocal;
        @Bind(R.id.id_homemm_secrt_message)
        TextView idHomemmSecrtMessage;
        @Bind(R.id.id_homemm_secrt_photo)
        ImageView idHomemmSecrtPhoto;
        @Bind(R.id.id_homemm_secrt_msg)
        TextView idHomemmSecrtMsg;
        @Bind(R.id.id_homemm_secrt_msgs)
        TextView idHomemmSecrtMsgs;
        @Bind(R.id.id_homemm_secrt_linear_msg)
        LinearLayout idHomemmSecrtLinearMsg;

        public VedioItemHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
