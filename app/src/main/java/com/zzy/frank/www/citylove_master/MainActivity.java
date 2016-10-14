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
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zzy.frank.www.citylove_master.fragment.FujinFragment;
import com.zzy.frank.www.citylove_master.fragment.HomeFragment;
import com.zzy.frank.www.citylove_master.fragment.MSGFragment;
import com.zzy.frank.www.citylove_master.fragment.PersonFragment;
import com.zzy.frank.www.citylove_master.server.SendMsgORAddFriends;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, MSGFragment.OnUnReadMessageUpdateListener
{
    @Bind(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @Bind(R.id.id_mian_timeup)
    LinearLayout idMianTimeup;
    @Bind(R.id.id_mian_timeup_pic)
    RoundImageView idMianTimeupPic;
    private ArrayList<Fragment> fragments;
    int lastSelectedPosition = 0;
    public BadgeItem numberBadgeItem;

    FragmentManager fm;
    FragmentTransaction ft;

    private PushApplication mApplication;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApplication = (PushApplication) this.getApplication();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorPrimary)
                .setHideOnSelect(false);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "主页").setActiveColorResource(R.color.colorPrimaryDark))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "消息").setActiveColorResource(R.color.colorPrimaryDark).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "附近").setActiveColorResource(R.color.colorPrimaryDark))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "个人中心").setActiveColorResource(R.color.colorPrimaryDark))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
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

    /**
     * 设置默认的
     */
    private void setDefaultFragment()
    {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.layFrame, new HomeFragment());
        ft.commit();
    }

    private ArrayList<Fragment> getFragments()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MSGFragment());
        fragments.add(new FujinFragment());
        fragments.add(new PersonFragment());
        return fragments;
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

    @Override
    public void onTabSelected(int position)
    {
        if (fragments != null)
        {
            if (position < fragments.size())
            {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded())
                {
                    ft.replace(R.id.layFrame, fragment);
                } else
                {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position)
    {
        if (fragments != null)
        {
            if (position < fragments.size())
            {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position)
    {
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
        if (count == 0)
            numberBadgeItem.setText("" + 0);
        int i = count + 1;
        numberBadgeItem.setText("" + i);
    }
}
