package com.zzy.frank.www.citylove_master.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.PLVideoTextureActivity;
import com.zzy.frank.www.citylove_master.bean.ScretItem;
import com.zzy.frank.www.citylove_master.bean.ScretMsg;
import com.zzy.frank.www.citylove_master.ui.Dialog_VIP;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;
import com.zzy.frank.www.citylove_master.util.StackBlurManager;

import java.util.List;

import retrofit2.http.POST;

/**
 * Created by pc on 2016/11/8.
 */
public class ScretAdapter extends BaseAdapter
{
    private List<ScretItem> mList;
    private Activity mContext;
    private LayoutInflater mInflater;
    private StackBlurManager mStackBlurManager;

    public ScretAdapter(List<ScretItem> mList, Activity mContext)
    {
        this.mList = mList;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
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

            holder.idHomemmSecrtIcon = (RoundImageView) convertView.findViewById(R.id.id_homemm_secrt_icon);
            holder.idHomemmSecrtName = (TextView) convertView.findViewById(R.id.id_homemm_secrt_name);
            holder.idHomemmSecrtLocal = (TextView) convertView.findViewById(R.id.id_homemm_secrt_local);
            holder.idHomemmSecrtMessage = (TextView) convertView.findViewById(R.id.id_homemm_secrt_message);
            holder.idHomemmSecrtPhoto = (ImageView) convertView.findViewById(R.id.id_homemm_secrt_photo);
            holder.idHomemmSecrtMsg = (TextView) convertView.findViewById(R.id.id_homemm_secrt_msg);
            holder.idHomemmSecrtMsgs = (RecyclerView) convertView.findViewById(R.id.id_homemm_secrt_msgs);
            holder.idHomemmSecrtLinearMsg = (LinearLayout) convertView.findViewById(R.id.id_homemm_secrt_linear_msg);
            holder.picture = (ImageView) convertView.findViewById(R.id.picture);
            holder.text = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);
        } else
        {
            holder = (VedioItemHolder) convertView.getTag();
        }

        Glide.with(mContext)
                .load(mList.get(position).getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.idHomemmSecrtIcon);

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

                Intent intent = new Intent();
                intent.putExtra("videoPath","http://c.tv778.com//videos//1478593540957.mp4");
                intent.putExtra("mediaCodec", 0);
                intent.setClass(mContext, PLVideoTextureActivity.class);
                mContext.startActivity(intent);
            }
        });

        Glide.with(mContext).load(mList.get(position).getPic()).asBitmap().into(new SimpleTarget<Bitmap>()
        {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
            {
                //从第三张照片开始模糊
                mStackBlurManager = new StackBlurManager(resource);
                mStackBlurManager.process(20);
                holder.picture.setImageBitmap(mStackBlurManager.returnBlurredImage());
            }
        });


        return convertView;
    }

    class VedioItemHolder
    {
        RoundImageView idHomemmSecrtIcon;
        TextView idHomemmSecrtName;
        TextView idHomemmSecrtLocal;
        TextView idHomemmSecrtMessage;
        ImageView idHomemmSecrtPhoto;
        TextView idHomemmSecrtMsg;
        RecyclerView idHomemmSecrtMsgs;
        LinearLayout idHomemmSecrtLinearMsg;
        ImageView picture;
        TextView text;
    }
}
