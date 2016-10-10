package com.zzy.frank.www.citylove_master.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	public static boolean isNetConnected(Context context) {
		boolean isNetConnected;
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
//			String name = info.getTypeName();
			isNetConnected = true;
		} else {
			isNetConnected = false;
		}
		return isNetConnected;
	}
}
