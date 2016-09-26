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
        ArrayList<String> optionsTwo_4 = new ArrayList<String>();
        optionsTwo_4.add("万州区");
        optionsTwo_4.add("涪陵区");
        optionsTwo_4.add("渝中区");
        optionsTwo_4.add("大渡口区");
        optionsTwo_4.add("江北区");
        optionsTwo_4.add("沙坪坝区");
        optionsTwo_4.add("九龙坡区");
        optionsTwo_4.add("南岸区");
        optionsTwo_4.add("北培区");
        optionsTwo_4.add("綦江区");
        optionsTwo_4.add("大足区");
        optionsTwo_4.add("渝北区");
        optionsTwo_4.add("巴南区");
        optionsTwo_4.add("綦江区");
        optionsTwo_4.add("长寿区");
        optionsTwo_4.add("江津区");
        optionsTwo_4.add("合川区");
        optionsTwo_4.add("永川区");
        optionsTwo_4.add("南川区");
        optionsTwo_4.add("璧山区");
        optionsTwo_4.add("铜梁区");
        optionsTwo_4.add("潼南区");
        optionsTwo_4.add("荣昌区");
        ArrayList<String> optionsTwo_5 = new ArrayList<String>();
        optionsTwo_5.add("石家庄市");
        optionsTwo_5.add("唐山市");
        optionsTwo_5.add("秦皇岛市");
        optionsTwo_5.add("邯郸市");
        optionsTwo_5.add("邢台市");
        optionsTwo_5.add("石家庄市");
        optionsTwo_5.add("保定市");
        optionsTwo_5.add("张家口市");
        optionsTwo_5.add("承德市");
        optionsTwo_5.add("沧州市");
        optionsTwo_5.add("廊坊市");
        optionsTwo_5.add("衡水市");
        ArrayList<String> optionsTwo_6 = new ArrayList<String>();
        optionsTwo_6.add("太原市");
        optionsTwo_6.add("大同市");
        optionsTwo_6.add("朔州市");
        optionsTwo_6.add("阳泉市");
        optionsTwo_6.add("长治市");
        optionsTwo_6.add("忻州市");
        optionsTwo_6.add("吕梁市");
        optionsTwo_6.add("晋中市");
        optionsTwo_6.add("临汾市");
        optionsTwo_6.add("运城市");
        optionsTwo_6.add("晋城市");
        ArrayList<String> optionsTwo_7 = new ArrayList<>();
        optionsTwo_7.add("呼和浩特市");
        optionsTwo_7.add("包头市");
        optionsTwo_7.add("乌海市");
        optionsTwo_7.add("赤峰市");
        optionsTwo_7.add("通辽市");
        optionsTwo_7.add("鄂尔多斯市");
        optionsTwo_7.add("呼伦贝尔市");
        optionsTwo_7.add("巴彦淖尔市");
        optionsTwo_7.add("乌兰察布市");
        optionsTwo_7.add("兴安盟");
        optionsTwo_7.add("锡林郭勒盟");
        optionsTwo_7.add("阿拉善盟");
        ArrayList<String> optionsTwo_8 = new ArrayList<>();
        optionsTwo_8.add("沈阳市");
        optionsTwo_8.add("大连市");
        optionsTwo_8.add("鞍山市");
        optionsTwo_8.add("抚顺市");
        optionsTwo_8.add("本溪市");
        optionsTwo_8.add("丹东市");
        optionsTwo_8.add("锦州市");
        optionsTwo_8.add("营口市");
        optionsTwo_8.add("辽阳市");
        optionsTwo_8.add("盘锦市");
        optionsTwo_8.add("铁岭市");
        optionsTwo_8.add("朝阳市");
        optionsTwo_8.add("葫芦岛市");
        ArrayList<String> optionsTwo_9 = new ArrayList<>();
        optionsTwo_9.add("长春市");
        optionsTwo_9.add("吉林市");
        optionsTwo_9.add("四平市");
        optionsTwo_9.add("辽源市");
        optionsTwo_9.add("通化市");
        ArrayList<String> optionsTwo_10 = new ArrayList<>();
        optionsTwo_10.add("哈尔滨市");
        optionsTwo_10.add("齐齐哈尔市");
        optionsTwo_10.add("牡丹江市");
        optionsTwo_10.add("佳木斯市");
        optionsTwo_10.add("大庆市");
        optionsTwo_10.add("鸡西市");
        optionsTwo_10.add("双鸭山市");
        optionsTwo_10.add("伊春市");
        optionsTwo_10.add("七台河市");
        optionsTwo_10.add("鹤岗市");
        optionsTwo_10.add("黑河市");
        optionsTwo_10.add("绥化市");
        ArrayList<String> optionsTwo_11 = new ArrayList<>();
        optionsTwo_11.add("南京市");
        optionsTwo_11.add("无锡市");
        optionsTwo_11.add("徐州市");
        optionsTwo_11.add("常州市");
        optionsTwo_11.add("苏州市");
        optionsTwo_11.add("南通市");
        optionsTwo_11.add("连云港市");
        optionsTwo_11.add("淮安市");
        optionsTwo_11.add("盐城市");
        optionsTwo_11.add("扬州市");
        optionsTwo_11.add("镇江市");
        optionsTwo_11.add("泰州市");
        optionsTwo_11.add("宿迁市");
        ArrayList<String> optionsTwo_12 = new ArrayList<>();
        optionsTwo_12.add("杭州市");
        optionsTwo_12.add("宁波市");
        optionsTwo_12.add("温州市");
        optionsTwo_12.add("嘉兴市");
        optionsTwo_12.add("湖州市");
        optionsTwo_12.add("绍兴市");
        optionsTwo_12.add("金华市");
        optionsTwo_12.add("衢州市");
        optionsTwo_12.add("舟山市");
        optionsTwo_12.add("台州市");
        optionsTwo_12.add("丽水市");
        ArrayList<String> optionsTwo_13 = new ArrayList<>();
        optionsTwo_13.add("合肥市");
        optionsTwo_13.add("淮北市");
        optionsTwo_13.add("亳州市");
        optionsTwo_13.add("宿州市");
        optionsTwo_13.add("阜阳市");
        optionsTwo_13.add("蚌埠市");
        optionsTwo_13.add("淮南市");
        optionsTwo_13.add("滁州市");
        optionsTwo_13.add("六安市");
        optionsTwo_13.add("芜湖市");
        optionsTwo_13.add("马鞍山市");
        optionsTwo_13.add("铜陵市");
        optionsTwo_13.add("安庆市");
        optionsTwo_13.add("池州市");
        optionsTwo_13.add("宣城市");
        optionsTwo_13.add("黄山市");
        ArrayList<String> optionsTwo_14 = new ArrayList<>();
        optionsTwo_14.add("福州市");
        optionsTwo_14.add("厦门市");
        optionsTwo_14.add("泉州市");
        optionsTwo_14.add("三明市");
        optionsTwo_14.add("南平市");
        optionsTwo_14.add("龙岩市");
        optionsTwo_14.add("漳州市");
        optionsTwo_14.add("宁德市");
        optionsTwo_14.add("莆田市");
        ArrayList<String> optionsTwo_15 = new ArrayList<>();
        optionsTwo_15.add("南昌市");
        optionsTwo_15.add("九江市");
        optionsTwo_15.add("吉安市");
        optionsTwo_15.add("赣州市");
        optionsTwo_15.add("萍乡市");
        optionsTwo_15.add("新余市");
        optionsTwo_15.add("宜春市");
        optionsTwo_15.add("景德镇市");
        optionsTwo_15.add("上饶市");
        optionsTwo_15.add("鹰潭市");
        optionsTwo_15.add("抚州市");
        ArrayList<String> optionsTwo_16 = new ArrayList<>();
        optionsTwo_16.add("济南市");
        optionsTwo_16.add("青岛市");
        optionsTwo_16.add("淄博市");
        optionsTwo_16.add("枣庄市");
        optionsTwo_16.add("东营市");
        optionsTwo_16.add("烟台市");
        optionsTwo_16.add("潍坊市");
        optionsTwo_16.add("济宁市");
        optionsTwo_16.add("泰安市");
        optionsTwo_16.add("威海市");
        optionsTwo_16.add("日照市");
        optionsTwo_16.add("滨州市");
        optionsTwo_16.add("德州市");
        optionsTwo_16.add("聊城市");
        optionsTwo_16.add("临沂市");
        optionsTwo_16.add("菏泽市");
        optionsTwo_16.add("莱芜市");
        ArrayList<String> optionsTwo_17 = new ArrayList<>();
        optionsTwo_17.add("郑州市");
        optionsTwo_17.add("新乡市");
        optionsTwo_17.add("洛阳市");
        optionsTwo_17.add("安阳市");
        optionsTwo_17.add("焦作市");
        optionsTwo_17.add("许昌市");
        optionsTwo_17.add("平顶山");
        optionsTwo_17.add("漯河市");
        optionsTwo_17.add("开封市");
        optionsTwo_17.add("濮阳市");
        optionsTwo_17.add("鹤壁市");
        optionsTwo_17.add("南阳市");
        optionsTwo_17.add("三门峡市");
        optionsTwo_17.add("驻马店市");
        optionsTwo_17.add("商丘市");
        optionsTwo_17.add("信阳市");
        optionsTwo_17.add("周口市");
        ArrayList<String> optionsTwo_18 = new ArrayList<>();
        optionsTwo_18.add("武汉市");
        optionsTwo_18.add("十堰市");
        optionsTwo_18.add("襄樊市");
        optionsTwo_18.add("随州市");
        optionsTwo_18.add("荆门市");
        optionsTwo_18.add("孝感市");
        optionsTwo_18.add("宜昌市");
        optionsTwo_18.add("黄冈市");
        optionsTwo_18.add("鄂州市");
        optionsTwo_18.add("荆州市");
        optionsTwo_18.add("黄石市");
        optionsTwo_18.add("咸宁市");
        ArrayList<String> optionsTwo_19 = new ArrayList<>();
        optionsTwo_19.add("长沙市");
        optionsTwo_19.add("株洲市");
        optionsTwo_19.add("湘潭市");
        optionsTwo_19.add("衡阳市");
        optionsTwo_19.add("邵阳市");
        optionsTwo_19.add("岳阳市");
        optionsTwo_19.add("常德市");
        optionsTwo_19.add("张家界市");
        optionsTwo_19.add("益阳市");
        optionsTwo_19.add("郴州市");
        optionsTwo_19.add("永州市");
        optionsTwo_19.add("怀化市");
        optionsTwo_19.add("娄底市");
        optionsTwo_19.add("湘西土家族苗族自治州");
        ArrayList<String> optionsTwo_20 = new ArrayList<>();
        optionsTwo_20.add("广州市");
        optionsTwo_20.add("深圳市");
        optionsTwo_20.add("珠海市");
        optionsTwo_20.add("汕头市");
        optionsTwo_20.add("韶关市");
        optionsTwo_20.add("佛山市");
        optionsTwo_20.add("江门市");
        optionsTwo_20.add("湛江市");
        optionsTwo_20.add("茂名市");
        optionsTwo_20.add("肇庆市");
        optionsTwo_20.add("惠州市");
        optionsTwo_20.add("梅州市");
        optionsTwo_20.add("汕尾市");
        optionsTwo_20.add("河源市");
        optionsTwo_20.add("阳江市");
        optionsTwo_20.add("清远市");
        optionsTwo_20.add("东莞市");
        optionsTwo_20.add("中山市");
        optionsTwo_20.add("潮州市");
        optionsTwo_20.add("揭阳市");
        optionsTwo_20.add("云浮市");
        ArrayList<String> optionsTwo_21 = new ArrayList<>();
        optionsTwo_21.add("南宁市");
        optionsTwo_21.add("防城港市");
        optionsTwo_21.add("崇左市");
        optionsTwo_21.add("柳州市");
        optionsTwo_21.add("来宾市");
        optionsTwo_21.add("桂林市");
        optionsTwo_21.add("梧州市");
        optionsTwo_21.add("贺州市");
        optionsTwo_21.add("玉林市");
        optionsTwo_21.add("贵港市");
        optionsTwo_21.add("百色市");
        optionsTwo_21.add("钦州市");
        optionsTwo_21.add("河池市");
        optionsTwo_21.add("北海市");
        ArrayList<String> optionsTwo_22 = new ArrayList<>();
        optionsTwo_22.add("海口市");
        optionsTwo_22.add("三亚市");
        ArrayList<String> optionsTwo_23 = new ArrayList<>();
        optionsTwo_23.add("成都市");
        optionsTwo_23.add("自贡市");
        optionsTwo_23.add("攀枝花市");
        optionsTwo_23.add("泸州市");
        optionsTwo_23.add("德阳市");
        optionsTwo_23.add("绵竹市");
        optionsTwo_23.add("绵阳市");
        optionsTwo_23.add("广元市");
        optionsTwo_23.add("遂宁市");
        optionsTwo_23.add("内江市");
        optionsTwo_23.add("乐山市");
        optionsTwo_23.add("南充市");
        optionsTwo_23.add("眉山市");
        optionsTwo_23.add("宜宾市");
        optionsTwo_23.add("广安市");
        optionsTwo_23.add("达州市 ");
        optionsTwo_23.add("雅安市");
        optionsTwo_23.add("巴中市");
        optionsTwo_23.add("资阳市");
        optionsTwo_23.add("阿坝藏族羌族自治州");
        optionsTwo_23.add("甘孜藏族自治州");
        optionsTwo_23.add("凉山彝族自治州");
        ArrayList<String> optionsTwo_24 = new ArrayList<>();
        optionsTwo_24.add("贵阳市");
        optionsTwo_24.add("遵义市");
        optionsTwo_24.add("安顺市");
        optionsTwo_24.add("凯里市");
        optionsTwo_24.add("铜仁市");
        optionsTwo_24.add("毕节市");
        optionsTwo_24.add("都匀市");
        optionsTwo_24.add("六盘水市");
        optionsTwo_24.add("兴义市");
        ArrayList<String> optionsTwo_25 = new ArrayList<>();
        optionsTwo_25.add("昆明市");
        optionsTwo_25.add("曲靖市");
        optionsTwo_25.add("昭通市");
        optionsTwo_25.add("玉溪市");
        optionsTwo_25.add("楚雄州");
        optionsTwo_25.add("红河州");
        optionsTwo_25.add("文山州");
        optionsTwo_25.add("普洱市");
        optionsTwo_25.add("版纳州");
        optionsTwo_25.add("大理州");
        optionsTwo_25.add("保山市");
        optionsTwo_25.add("德宏州");
        optionsTwo_25.add("丽江市");
        optionsTwo_25.add("怒江州");
        optionsTwo_25.add("迪庆州");
        optionsTwo_25.add("临沧市");
        ArrayList<String> optionsTwo_26 = new ArrayList<>();
        optionsTwo_26.add("拉萨市");
        optionsTwo_26.add("日喀则市");
        ArrayList<String> optionsTwo_27 = new ArrayList<>();
        optionsTwo_27.add("西安市");
        optionsTwo_27.add("宝鸡市");
        optionsTwo_27.add("咸阳市");
        optionsTwo_27.add("延安市");
        optionsTwo_27.add("渭南市");
        optionsTwo_27.add("榆林市");
        optionsTwo_27.add("铜川市");
        optionsTwo_27.add("汉中市");
        optionsTwo_27.add("安康市");
        optionsTwo_27.add("商洛市");
        ArrayList<String> optionsTwo_28 = new ArrayList<>();
        optionsTwo_28.add("兰州市");
        optionsTwo_28.add("嘉峪关市");
        optionsTwo_28.add("金昌市");
        optionsTwo_28.add("白银市");
        optionsTwo_28.add("天水市");
        optionsTwo_28.add("武威市");
        optionsTwo_28.add("张掖市");
        optionsTwo_28.add("酒泉市");
        optionsTwo_28.add("平凉市");
        optionsTwo_28.add("庆阳市");
        optionsTwo_28.add("定西市");
        optionsTwo_28.add("陇南市");
        optionsTwo_28.add("临夏回族自治州");
        optionsTwo_28.add("甘南藏族自治州");
        ArrayList<String> optionsTwo_29 = new ArrayList<>();
        optionsTwo_29.add("西宁市");
        optionsTwo_29.add("海东市");
        ArrayList<String> optionsTwo_30 = new ArrayList<>();
        optionsTwo_30.add("银川市");
        optionsTwo_30.add("石嘴山市");
        optionsTwo_30.add("吴忠市");
        optionsTwo_30.add("中卫市");
        optionsTwo_30.add("固原市");
        ArrayList<String> optionsTwo_31 = new ArrayList<>();
        optionsTwo_31.add("乌鲁木齐市");
        optionsTwo_31.add("克拉玛依市");
        optionsTwo_31.add("吐鲁番市");
        optionsTwo_31.add("哈密市");
        optionsTwo_31.add("和田市");
        optionsTwo_31.add("阿克苏市");
        optionsTwo_31.add("喀什市");
        optionsTwo_31.add("阿图什市");
        optionsTwo_31.add("库尔勒市");
        ArrayList<String> optionsTwo_32 = new ArrayList<>();
        optionsTwo_32.add("香港市");
        ArrayList<String> optionsTwo_33 = new ArrayList<>();
        optionsTwo_33.add("澳门市");
        ArrayList<String> optionsTwo_34 = new ArrayList<>();
        optionsTwo_34.add("台北市");
        optionsTwo_34.add("新北市");
        optionsTwo_34.add("台中市");
        optionsTwo_34.add("基隆市");
        optionsTwo_34.add("桃园市");
        optionsTwo_34.add("嘉义市");
        optionsTwo_34.add("新竹市");
        optionsTwo_34.add("高雄市");
        optionsTwo_34.add("台南市");
        optionsTwo_34.add("台北市");
        optionsTwo_34.add("台北市");
        optionsTwo.add(optionsTwo_1);
        optionsTwo.add(optionsTwo_2);
        optionsTwo.add(optionsTwo_3);
        optionsTwo.add(optionsTwo_4);
        optionsTwo.add(optionsTwo_5);
        optionsTwo.add(optionsTwo_6);
        optionsTwo.add(optionsTwo_7);
        optionsTwo.add(optionsTwo_8);
        optionsTwo.add(optionsTwo_9);
        optionsTwo.add(optionsTwo_10);
        optionsTwo.add(optionsTwo_11);
        optionsTwo.add(optionsTwo_12);
        optionsTwo.add(optionsTwo_13);
        optionsTwo.add(optionsTwo_14);
        optionsTwo.add(optionsTwo_15);
        optionsTwo.add(optionsTwo_16);
        optionsTwo.add(optionsTwo_17);
        optionsTwo.add(optionsTwo_18);
        optionsTwo.add(optionsTwo_19);
        optionsTwo.add(optionsTwo_20);
        optionsTwo.add(optionsTwo_21);
        optionsTwo.add(optionsTwo_22);
        optionsTwo.add(optionsTwo_23);
        optionsTwo.add(optionsTwo_24);
        optionsTwo.add(optionsTwo_25);
        optionsTwo.add(optionsTwo_26);
        optionsTwo.add(optionsTwo_27);
        optionsTwo.add(optionsTwo_28);
        optionsTwo.add(optionsTwo_29);
        optionsTwo.add(optionsTwo_30);
        optionsTwo.add(optionsTwo_31);
        optionsTwo.add(optionsTwo_32);
        optionsTwo.add(optionsTwo_33);
        optionsTwo.add(optionsTwo_34);

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
                pvOptions.setCyclic(false, false, true);
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


                        idZhengyouifLocal.setText(optionsOne.get(options1) + "," + optionsTwo.get(options1).get(option2));


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
