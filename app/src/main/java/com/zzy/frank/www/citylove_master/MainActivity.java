package com.zzy.frank.www.citylove_master;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jauker.widget.BadgeView;
import com.zzy.frank.www.citylove_master.fragment.FujinFragment;
import com.zzy.frank.www.citylove_master.fragment.HomeFragment;
import com.zzy.frank.www.citylove_master.fragment.MSGFragment;
import com.zzy.frank.www.citylove_master.fragment.PersonFragment;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MSGFragment.OnUnReadMessageUpdateListener
{
    @Bind(R.id.id_mian_timeup)
    LinearLayout idMianTimeup;
    @Bind(R.id.id_mian_timeup_pic)
    RoundImageView idMianTimeupPic;
    @Bind(R.id.activity_group_radioGroup)
    RadioGroup mRadioGroup;

    Handler handler;
    Runnable runnable;
    BadgeView badgeView;

    public static final String fragment1Tag = "fragment1";
    public static final String fragment2Tag = "fragment2";
    public static final String fragment3Tag = "fragment3";
    public static final String fragment4Tag = "fragment4";
    @Bind(R.id.bt)
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        badgeView = new BadgeView(this);
        badgeView.setTargetView(bt);
        badgeView.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        badgeView.setBadgeMargin(0, 0, 5, 0);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment1 = fm.findFragmentByTag(fragment1Tag);
                Fragment fragment2 = fm.findFragmentByTag(fragment2Tag);
                Fragment fragment3 = fm.findFragmentByTag(fragment3Tag);
                Fragment fragment4 = fm.findFragmentByTag(fragment4Tag);
                if (fragment1 != null)
                {
                    ft.hide(fragment1);
                }
                if (fragment2 != null)
                {
                    ft.hide(fragment2);
                }
                if (fragment3 != null)
                {
                    ft.hide(fragment3);
                }
                if (fragment4 != null)
                {
                    ft.hide(fragment4);
                }
                switch (checkedId)
                {
                    case R.id.order_process:
                        if (fragment1 == null)
                        {
                            fragment1 = new HomeFragment();
                            ft.add(R.id.layFrame, fragment1, fragment1Tag);
                        } else
                        {
                            ft.show(fragment1);
                        }
                        break;
                    case R.id.order_query:
                        if (fragment2 == null)
                        {
                            fragment2 = new MSGFragment();
                            ft.add(R.id.layFrame, fragment2, fragment2Tag);
                        } else
                        {
                            ft.show(fragment2);
                        }
                        break;
                    case R.id.merchant_manager:
                        if (fragment3 == null)
                        {
                            fragment3 = new FujinFragment();
                            ft.add(R.id.layFrame, fragment3, fragment3Tag);
                        } else
                        {
                            ft.show(fragment3);
                        }
                        break;
                    case R.id.setting:
                        if (fragment4 == null)
                        {
                            fragment4 = new PersonFragment();
                            ft.add(R.id.layFrame, fragment4, fragment4Tag);
                        } else
                        {
                            ft.show(fragment4);
                        }
                        break;
                    default:
                        break;
                }
                ft.commit();
            }
        });

        if (savedInstanceState == null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.layFrame, fragment, fragment1Tag).commit();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < mRadioGroup.getChildCount(); i++)
        {
            RadioButton mTab = (RadioButton) mRadioGroup.getChildAt(i);
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentByTag((String) mTab.getTag());
            FragmentTransaction ft = fm.beginTransaction();
            if (fragment != null)
            {
                if (!mTab.isChecked())
                {
                    ft.hide(fragment);
                }
            }
            ft.commit();
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        handler = new Handler();
        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                idMianTimeup.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        idMianTimeup.setVisibility(View.GONE);
                    }
                }, 5000);
                handler.postDelayed(runnable, 60000);
            }
        };
        handler.postDelayed(runnable, 60000);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN)
        {
//            if ((System.currentTimeMillis() - exitTime) > 2000)
//            {
//                Toast.makeText(getApplicationContext(), "再按一次退出程序",
//                        Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis();
//            } else
//            {
//                finish();
//                System.exit(0);
//            }

            new AlertDialog.Builder(this).setTitle("happy")
                    .setMessage("还有一大波朋友要约,确定要退出?")
                    .setPositiveButton("留下来看看", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    }).setNegativeButton("稍后再来", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    finish();
                    System.exit(0);
                }
            }).show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.id_mian_timeup_bt_whatever, R.id.id_mian_timeup_bt_sayhi, R.id.id_mian_timeup_bt_chat})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_mian_timeup_bt_whatever:
                break;
            case R.id.id_mian_timeup_bt_sayhi:
                break;
            case R.id.id_mian_timeup_bt_chat:
                break;
        }

        startActivity(intent);
    }

    @Override
    public void unReadMessageUpdate(int count)
    {
        if (count == 0 && badgeView.getBadgeCount() == 0)
            badgeView.setVisibility(View.GONE);
        badgeView.setVisibility(View.VISIBLE);
        badgeView.setBadgeCount(count);
    }
}
