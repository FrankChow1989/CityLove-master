package com.zzy.frank.www.citylove_master.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zzy.frank.www.citylove_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SreachActivity extends AppCompatActivity
{

    @Bind(R.id.etSearch)
    EditText etSearch;
    @Bind(R.id.ivDeleteText)
    ImageView ivDeleteText;
    @Bind(R.id.id_sreach_listview)
    RecyclerView idSreachListview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sreach);
        ButterKnife.bind(this);

        ivDeleteText.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                etSearch.setText("");
            }
        });

        etSearch.addTextChangedListener(new TextWatcher()
        {

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after)
            {
                // TODO Auto-generated method stub
            }

            public void afterTextChanged(Editable s)
            {
                if (s.length() == 0)
                {
                    ivDeleteText.setVisibility(View.INVISIBLE);
                } else
                {
                    ivDeleteText.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @OnClick(R.id.id_sreach_start)
    public void onClick()
    {
        // TODO: 2016/9/22 开始搜索
    }
}
