package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.adapter.HomeAdapter;
import com.zzy.frank.www.citylove_master.adapter.KefuChattingAdpter;
import com.zzy.frank.www.citylove_master.api.RxService;
import com.zzy.frank.www.citylove_master.bean.Grils;
import com.zzy.frank.www.citylove_master.bean.KeFuBean;
import com.zzy.frank.www.citylove_master.bean.KeFuCode;
import com.zzy.frank.www.citylove_master.face.FaceRelativeLayout;
import com.zzy.frank.www.citylove_master.face.FaceRelativeLayoutKeFu;
import com.zzy.frank.www.citylove_master.util.T;
import com.zzy.frank.www.citylove_master.util.VolleyInterface;
import com.zzy.frank.www.citylove_master.util.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class KefuChattingActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.id_kefuchatting_listview)
    ListView mListView;
    List<KeFuBean> mList;
    private KefuChattingAdpter adpter;
    private Button sendButton;
    private EditText editText;
    private static final String API = "http://www.tuling123.com/openapi/api";//api地址

    //应用唯一key，请到官网注册账号后，可以获取，注册地址：
    //http://www.tuling123.com/openapi/record.do?channel=43035
    private static final String KEY_STRING = "b637efbbba1449a48d100f8645c2940e";


    private String sendMessage;   //你自己发送的单条信息
    private String welcome;  //放置欢迎信息

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kefu_chatting);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        initView();
        welcome = getResources().getString(R.string.welcome); //获取我们内置的欢迎信息
        setListener(); //绑定监听事件
        initData(); // 进入界面，随机显示机器人的欢迎信息
    }

    private void initData()
    {
        //int pos = (int) (Math.random() * welcome.length - 1);  //获取一个随机数
        showData(welcome); //用随机数获取我们内置的信息，用户进入界面，机器人的首次聊天信息
    }

    private void initView()
    {
        toolBar.setTitle("同城送爱红娘");
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

        adpter = new KefuChattingAdpter(mList, this);
        editText = (EditText) findViewById(R.id.et_sendmessage);
        editText.addTextChangedListener(textWatcher);
        sendButton = (Button) findViewById(R.id.btn_send);

        mListView.setAdapter(adpter);

        System.out.println(editText.getText() + "--------------");

        if (editText.isFocused())
        {
            sendButton.setVisibility(View.VISIBLE);
        }
    }

    public void setListener()
    {
        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                sendData(); //点击发送按钮，触发
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            sendButton.setVisibility(View.VISIBLE);
            if ("".equals(editText.getText().toString()))
            {
                sendButton.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s)
        {
        }
    };


    @Override
    protected void onResume()
    {
        super.onResume();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && ((FaceRelativeLayoutKeFu) findViewById(R.id.face_kefu_chat_bottom))
                .hideFaceView())
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void sendData()
    {
        sendMessage = editText.getText().toString(); //获取你输入的信息

        if (TextUtils.isEmpty(sendMessage))
        {
            T.showShort(getApplicationContext(), "您还未填写消息呢!");
            return;
        }
        editText.setText("");

        sendButton.setVisibility(View.GONE);

        sendMessage = sendMessage.replaceAll(" ", "").replaceAll("\n", "")
                .trim(); //替换空格和换行
        //LiaoTianBean是一个实体类，
        //封装我们发送的信息，然后加入集合，更新listview
        KeFuBean liaoTianBean = new KeFuBean();
        liaoTianBean.setMessage(sendMessage);
        liaoTianBean.setState(1); //1标示自己发送的信息
        liaoTianBean.setDate(new Date());
        mList.add(liaoTianBean); //把自己发送的信息，加入集合
        adpter.notifyDataSetChanged(); //通知listview更新
        mListView.setSelection(mList.size() - 1);

        getDataFromServer(); //从服务器获取返回到额数据，机器人的信息
    }

    /**
     * 二次封装的回调方法Post
     */
    public void getDataFromServer()
    {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("key", KEY_STRING);
//        params.put("info", sendMessage);
//
//        VolleyRequest.RequestPost(this, API, "abcPost", params, new VolleyInterface(this,
//                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
//        {
//            @Override
//            public void onMySuccess(String result)
//            {
//                paresData(result);
//            }
//
//            @Override
//            public void onMyError(VolleyError error)
//            {
//                showData("主人，您的网络有问题哦");
//            }
//        });

        RxService.getKefuApi().getKefu(KEY_STRING, sendMessage)
                .subscribeOn(Schedulers.io())//在非UI线程中获取数据
                .observeOn(AndroidSchedulers.mainThread())//在UI线程中执行更新UI
                .subscribe(new Observer<KeFuCode>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        showData("主人，你的网络不好哦");
                    }

                    @Override
                    public void onNext(KeFuCode keFuCode)
                    {
                        updateView(keFuCode.getCode(), keFuCode.getText());
                    }
                });
    }

    private void showData(String message)
    {
        //和上面一样，创建一个LiaoTianBean对象，传入数据，在把它放进集合
        KeFuBean liaoTianBean = new KeFuBean();
        liaoTianBean.setMessage(message);
        liaoTianBean.setState(2);
        liaoTianBean.setDate(new Date());
        //这里2标示机器人的信息，用于listview的adpter，显示不同的界面
        mList.add(liaoTianBean);
        adpter.notifyDataSetChanged();
        mListView.setSelection(mList.size() - 1);
    }

    private void updateView(int code, String content)
    {
        //code有很多种状态，具体看官网
        //http://www.tuling123.com/openapi/record.do?channel=43035
        switch (code)
        {
            case 4004:
                showData("主人今天我累了，我要休息了，明天再来找我耍吧");
                break;
            case 40005:
                showData("主人，我听不懂你在说什么哦");
                break;
            case 40006:
                showData("主人，我今天闭关修炼哦，占不接客啦");
                break;
            case 40007:
                showData("主人，明天再和你耍啦，我吃坏肚子了，呜呜。。。");
                break;
            default:
                showData(content);
                break;
        }
    }
}
