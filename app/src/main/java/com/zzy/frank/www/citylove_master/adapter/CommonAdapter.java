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
import com.zzy.frank.www.citylove_master.bean.VisitCommon;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/9/20.
 */
public class CommonAdapter extends RecyclerView.Adapter
{
    private List<VisitCommon> mList;
    private Context context;

    public CommonAdapter(List<VisitCommon> mList, Context context)
    {
        this.mList = mList;
        this.context = context;

        System.out.println("---------mList---------:" + mList.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_visit_common, parent, false);

        VisitViewHolder holder = new VisitViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        VisitViewHolder viewHolder = (VisitViewHolder) holder;

        Uri uri = Uri.parse("res://com.zzy.frank.www.citylove_master/" + mList.get(position).getPic());
        viewHolder.idVisitPic.setImageURI(uri);

        viewHolder.idVisitName.setText(mList.get(position).getName());
        viewHolder.idVisitContent.setText(mList.get(position).getContent());
        viewHolder.idVisitFrom.setText(mList.get(position).getFrom());
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class VisitViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.id_visit_pic)
        SimpleDraweeView idVisitPic;
        @Bind(R.id.id_visit_name)
        TextView idVisitName;
        @Bind(R.id.id_visit_content)
        TextView idVisitContent;
        @Bind(R.id.id_visit_from)
        TextView idVisitFrom;

        public VisitViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
