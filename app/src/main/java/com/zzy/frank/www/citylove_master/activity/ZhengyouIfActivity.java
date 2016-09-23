package com.zzy.frank.www.citylove_master.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.ui.WheelView;
import com.zzy.frank.www.citylove_master.util.T;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhengyouIfActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;

    private static final String[] age = new String[43];
    private static final String[] height = new String[41];
    private static final String[] xueli = new String[]{"初中及以下", "高中及中专", "大专", "本科", "硕士及以上"};
    private static final String[] money = new String[]{"0", "2000-5000", "5000-8000", "8000-10000", "10000-15000", "15000-20000", "20000以上"};

    //城市选择
    private OptionsPickerView<String> pvOptions;
    private ArrayList<String> optionsOne = new ArrayList<String>();
    private ArrayList<ArrayList<String>> optionsTwo = new ArrayList<ArrayList<String>>();

    String age_txt, height_txt, xueli_txt, money_txt;

    @Bind(R.id.id_zhengyouif_local)
    TextView idZhengyouifLocal;
    @Bind(R.id.id_zhengyouif_height)
    TextView idZhengyouifHeight;
    @Bind(R.id.id_zhengyouif_xueli)
    TextView idZhengyouifXueli;
    @Bind(R.id.id_zhengyouif_money)
    TextView idZhengyouifMoney;
    @Bind(R.id.id_zhengyouif_age)
    TextView idZhengyouifAge;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhengyou_if);
        ButterKnife.bind(this);

        toolBar.setTitle("征友条件");
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        initData();

    }

    private void initData()
    {
        for (int i = 0; i < age.length; i++)
        {
            age[i] = 18 + i + "岁";
        }

        for (int i = 0; i < height.length; i++)
        {
            height[i] = 150 + i + "cm";
        }

        //国家
        optionsOne.add("北京");
        optionsOne.add("天津");
        optionsOne.add("上海");
        optionsOne.add("重庆");
        optionsOne.add("河北省");
        optionsOne.add("山西省");
        optionsOne.add("内蒙古自治区");
        optionsOne.add("辽宁省");
        optionsOne.add("吉林省");
        optionsOne.add("黑龙江省");
        optionsOne.add("江苏省");
        optionsOne.add("浙江省");
        optionsOne.add("安徽省");
        optionsOne.add("福建省");
        optionsOne.add("江西省");
        optionsOne.add("山东省");
        optionsOne.add("河南省");
        optionsOne.add("湖北省");
        optionsOne.add("湖南省");
        optionsOne.add("广东省");
        optionsOne.add("广西壮族自治区");
        optionsOne.add("海南省");
        optionsOne.add("四川省");
        optionsOne.add("贵州省");
        optionsOne.add("云南省");
        optionsOne.add("西藏自治区");
        optionsOne.add("陕西省");
        optionsOne.add("甘肃省");
        optionsOne.add("青海省");
        optionsOne.add("宁夏回族自治区");
        optionsOne.add("新疆维吾尔自治区");
        optionsOne.add("香港特别行政区");
        optionsOne.add("澳门特别行政区");
        optionsOne.add("台湾省");

        //省
        ArrayList<String> optionsTwo_1 = new ArrayList<String>();
        optionsTwo_1.add("东城区");
        optionsTwo_1.add("西城区");
        optionsTwo_1.add("朝阳区");
        optionsTwo_1.add("丰台区");
        optionsTwo_1.add("石景山区");
        optionsTwo_1.add("海淀区");
        optionsTwo_1.add("门头沟区");
        optionsTwo_1.add("房山区");
        optionsTwo_1.add("通州区");
        optionsTwo_1.add("顺义区");
        optionsTwo_1.add("昌平区");
        optionsTwo_1.add("大兴区");
        optionsTwo_1.add("怀柔区");
        optionsTwo_1.add("平谷区");
        optionsTwo_1.add("密云区");
        optionsTwo_1.add("延庆区");
        ArrayList<String> optionsTwo_2 = new ArrayList<String>();
        optionsTwo_2.add("和平区");
        optionsTwo_2.add("河东区");
        optionsTwo_2.add("河西区");
        optionsTwo_2.add("南开区");
        optionsTwo_2.add("河北区");
        optionsTwo_2.add("红桥区");
        optionsTwo_2.add("东丽区");
        optionsTwo_2.add("西青区");
        optionsTwo_2.add("津南区");
        optionsTwo_2.add("北辰区");
        optionsTwo_2.add("武清区");
        optionsTwo_2.add("宝坻区");
        optionsTwo_2.add("宁河区");
        optionsTwo_2.add("静海区");
        ArrayList<String> optionsTwo_3 = new ArrayList<String>();
        optionsTwo_3.add("黄浦区");
        optionsTwo_3.add("徐汇区");
        optionsTwo_3.add("长宁区");
        optionsTwo_3.add("静安区");
        optionsTwo_3.add("普陀区");
        optionsTwo_3.add("虹口区");
        optionsTwo_3.add("杨浦区");
        optionsTwo_3.add("闵行区");
        optionsTwo_3.add("宝山区");
        optionsTwo_3.add("嘉定区");
        optionsTwo_3.add("浦东新区");
        optionsTwo_3.add("金山区");
        optionsTwo_3.add("松江区");
        optionsTwo_3.add("青浦区");
        optionsTwo_3.add("奉贤区");
        optionsTwo.add(optionsTwo_1);
        optionsTwo.add(optionsTwo_2);
        optionsTwo.add(optionsTwo_3);

//        //市
//        ArrayList<ArrayList<String>> optionsThree_1 = new ArrayList<ArrayList<String>>();
//        ArrayList<String> optionsThree_1_1 = new ArrayList<String>();
//        optionsThree_1_1.add("北京市");
//        optionsThree_1_1.add("朝阳区");
//        optionsThree_1_1.add("玄武区");
//        optionsThree_1.add(optionsThree_1_1);
//        ArrayList<String> optionsThree_1_2 = new ArrayList<String>();
//        optionsThree_1_2.add("上海市");
//        optionsThree_1_2.add("静安区");
//        optionsThree_1_2.add("闵行区");
//        optionsThree_1.add(optionsThree_1_2);
//        ArrayList<String> optionsThree_1_3 = new ArrayList<String>();
//        optionsThree_1_3.add("武汉市");
//        optionsThree_1_3.add("孝感市");
//        optionsThree_1_3.add("安陆市");
//        optionsThree_1.add(optionsThree_1_3);
//        optionsThree.add(optionsThree_1);
//        ArrayList<ArrayList<String>> optionsThree_2 = new ArrayList<ArrayList<String>>();
//        ArrayList<String> optionsThree_2_1 = new ArrayList<String>();
//        optionsThree_2_1.add("阳川区");
//        optionsThree_2_1.add("道峰区");
//        optionsThree_2_1.add("永登浦区");
//        optionsThree_2.add(optionsThree_2_1);
//        ArrayList<String> optionsThree_2_2 = new ArrayList<String>();
//        optionsThree_2_2.add("釜山镇区");
//        optionsThree_2_2.add("海云台区");
//        optionsThree_2_2.add("莲堤区");
//        optionsThree_2.add(optionsThree_2_2);
//        ArrayList<String> optionsThree_2_3 = new ArrayList<String>();
//        optionsThree_2_3.add("达城郡");
//        optionsThree_2_3.add("寿城区");
//        optionsThree_2_3.add("达西区");
//        optionsThree_2.add(optionsThree_2_3);
//        optionsThree.add(optionsThree_2);

    }

    @OnClick({R.id.id_zhengyou_linear_age, R.id.id_zhengyou_linear_live, R.id.id_zhengyou_linear_heiht, R.id.id_zhengyou_linear_xueli, R.id.id_zhengyou_linear_shouru, R.id.bt_zhengyou_sure})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_zhengyou_linear_age:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener()
                {
                    @Override
                    public void onSelected(int selectedIndex, String item)
                    {
                        //  Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        age_txt = item;
                    }
                });
                wv.setOffset(2);
                wv.setItems(Arrays.asList(age));
                wv.setSeletion(3);

                new AlertDialog.Builder(this)
                        .setTitle("年龄")
                        .setView(outerView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                idZhengyouifAge.setText(age_txt);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .show();

                break;
            case R.id.id_zhengyou_linear_live:

                //选项选择器
                pvOptions = new OptionsPickerView<String>(this);
                //三级联动
                pvOptions.setPicker(optionsOne, optionsTwo, true);

                //设置标题
                pvOptions.setTitle("居住地");
                //设置是否可循环
                pvOptions.setCyclic(true, true, true);
                //设置标题
                // pvOptions.setLabels("省/直辖市", "市/区");
                //设置确定监听
                pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener()
                {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3)
                    {
//                        Log.e("MainActivity", "选择的是："
//                                + optionsOne.get(options1)
//                                + optionsTwo.get(options1).get(option2));
//                        // + optionsThree.get(options1).get(option2).get(options3));
//
//                        T.showShort(ZhengyouIfActivity.this, "选择的是："
//                                + optionsOne.get(options1)
//                                + optionsTwo.get(options1).get(option2));


                        idZhengyouifLocal.setText(optionsOne.get(options1) + "市," + optionsTwo.get(options1).get(option2));


                    }
                });
                //设置消失监听
                pvOptions.setOnDismissListener(new OnDismissListener()
                {
                    @Override
                    public void onDismiss(Object o)
                    {
                        if (o instanceof OptionsPickerView)
                        {
                            //T.showShort(ZhengyouIfActivity.this, "消失了是OptionsPickerView");
                        } else
                        {
                            //T.showShort(ZhengyouIfActivity.this, "消失了不是OptionsPickerView");
                        }
                    }
                });
                //显示
                pvOptions.show();

                break;
            case R.id.id_zhengyou_linear_heiht:
                View outerView1 = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv1 = (WheelView) outerView1.findViewById(R.id.wheel_view_wv);
                wv1.setOnWheelViewListener(new WheelView.OnWheelViewListener()
                {
                    @Override
                    public void onSelected(int selectedIndex, String item)
                    {
                        //  Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        height_txt = item;
                    }
                });
                wv1.setOffset(2);
                wv1.setItems(Arrays.asList(height));
                wv1.setSeletion(3);

                new AlertDialog.Builder(this)
                        .setTitle("身高")
                        .setView(outerView1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                idZhengyouifHeight.setText(height_txt);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .show();

                break;
            case R.id.id_zhengyou_linear_xueli:
                View outerView2 = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv2 = (WheelView) outerView2.findViewById(R.id.wheel_view_wv);
                wv2.setOnWheelViewListener(new WheelView.OnWheelViewListener()
                {
                    @Override
                    public void onSelected(int selectedIndex, String item)
                    {
                        //  Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        xueli_txt = item;
                    }
                });
                wv2.setOffset(2);
                wv2.setItems(Arrays.asList(xueli));
                wv2.setSeletion(3);

                new AlertDialog.Builder(this)
                        .setTitle("学历")
                        .setView(outerView2)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                idZhengyouifXueli.setText(xueli_txt);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .show();

                break;
            case R.id.id_zhengyou_linear_shouru:
                View outerView3 = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv3 = (WheelView) outerView3.findViewById(R.id.wheel_view_wv);
                wv3.setOnWheelViewListener(new WheelView.OnWheelViewListener()
                {
                    @Override
                    public void onSelected(int selectedIndex, String item)
                    {
                        //  Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        money_txt = item;
                    }
                });
                wv3.setOffset(2);
                wv3.setItems(Arrays.asList(money));
                wv3.setSeletion(3);

                new AlertDialog.Builder(this)
                        .setTitle("月收入")
                        .setView(outerView3)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                idZhengyouifMoney.setText(money_txt);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.bt_zhengyou_sure:
                break;
        }
    }
}
