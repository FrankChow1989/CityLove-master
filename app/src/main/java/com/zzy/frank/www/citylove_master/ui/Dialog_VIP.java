package com.zzy.frank.www.citylove_master.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zzy.frank.www.citylove_master.R;

/**
 * Created by pc on 2016/11/4.
 */
public class Dialog_VIP
{
    public static void show(Activity activity)
    {

        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();

        int width = d.getWidth();
        int height = d.getHeight();

        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        LayoutInflater inflater = activity.getLayoutInflater();
        View view1 = inflater.inflate(R.layout.dialog_custem, null);
        dialog.setView(view1, 0, 0, 0, 0);

        LinearLayout button1 = (LinearLayout) view1.findViewById(R.id.id_dialog_year);
        LinearLayout button2 = (LinearLayout) view1.findViewById(R.id.id_dialog_month);
        Button button = (Button) view1.findViewById(R.id.id_dialog_button);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        dialog.show();
        dialog.getWindow().setLayout(width / 2 + 220, height / 2 + 50);
    }

}
