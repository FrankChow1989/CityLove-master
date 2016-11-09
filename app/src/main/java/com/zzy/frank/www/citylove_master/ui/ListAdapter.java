package com.zzy.frank.www.citylove_master.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.ScrollData;

import java.util.List;

/**
 * * 实现对listView的循环滚动 
 *
 */
public class ListAdapter extends BaseAdapter
{
	
	private List<ScrollData> list;
	private LayoutInflater mInflater;
	
	public ListAdapter(Context context, List<ScrollData> list){
		this.list = list;
		this.mInflater = LayoutInflater.from(context);
	}
	/**
	 * 将数据循环展示三遍
	 */
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int arg0) {
		
		return list.get(arg0 % list.size());
	}

	@Override
	public long getItemId(int arg0) {
		return arg0 % list.size();
	}
	@Override
	public View getView(int postition, View convertView, ViewGroup arg2) {
		ViewHoler viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHoler();
			convertView = mInflater.inflate(R.layout.adapter_list_layout, null);
			viewHolder.tvText = (TextView) convertView.findViewById(R.id.adapter_list_layout_tv);
			viewHolder.msgText = (TextView) convertView.findViewById(R.id.adapter_list_layout_msg);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHoler) convertView.getTag();
		}
		viewHolder.tvText.setText(list.get(postition % list.size()).getName());//取余展示数据
		viewHolder.msgText.setText(list.get(postition % list.size()).getMessage());//取余展示数据
		return convertView;
	}
	
	static class ViewHoler{
		TextView tvText;
		TextView msgText;
	}

}
