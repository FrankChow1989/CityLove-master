package com.zzy.frank.www.citylove_master.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by pc on 2016/10/11.
 */
public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent i = new Intent(context, SendMsgORAddFriends.class);
        context.startService(i);
    }
}
