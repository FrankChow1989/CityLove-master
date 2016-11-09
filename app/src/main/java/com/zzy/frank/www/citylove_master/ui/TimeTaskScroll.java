package com.zzy.frank.www.citylove_master.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.zzy.frank.www.citylove_master.bean.ScrollData;

import java.util.List;
import java.util.TimerTask;

public class TimeTaskScroll extends TimerTask
{
	
	private ListView listView;
	
	public TimeTaskScroll(Context context, ListView listView, List<ScrollData> list){
		this.listView = listView;
		listView.setAdapter(new ListAdapter(context, list));
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			listView.smoothScrollBy(1, 0);
		};
	};

	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		handler.sendMessage(msg);
	}

}
