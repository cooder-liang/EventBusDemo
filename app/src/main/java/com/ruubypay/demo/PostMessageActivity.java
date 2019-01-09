package com.ruubypay.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * @author LiangYang
 * @time 2019/1/9 下午5:36
 * @class describe
 */
public class PostMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_message);
//        EventBus.getDefault().post(MessageWrap.getInstance("我是一条测试数据，啦啦啦！！！"));
        EventBus.getDefault().postSticky(MessageWrap.getInstance("我是一条测试数据，啦啦啦！！！"));
    }
}
