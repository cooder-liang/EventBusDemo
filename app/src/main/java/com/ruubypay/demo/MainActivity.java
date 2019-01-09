package com.ruubypay.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView111;
    //是否需要停止事件的继续分发
    private boolean stopDelivery = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.bind);
        textView = findViewById(R.id.bind1);

        findViewById(R.id.btn_priority)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopDelivery=true;
                    }
                });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean registered = EventBus.getDefault().isRegistered(MainActivity.this);
                Log.e("onClick111", "onClick:" + registered);
                if (!registered) {
                    EventBus.getDefault().register(MainActivity.this);
                }
            }
        });

        findViewById(R.id.next_stick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean registered = EventBus.getDefault().isRegistered(MainActivity.this);
                Log.e("onClick222", "onClick:" + registered);
                if (!registered) {
                    EventBus.getDefault().register(MainActivity.this);
                }
            }
        });

        findViewById(R.id.next)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, PostMessageActivity.class);
                        startActivity(intent);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true,priority = 0)
    public void onGetMessage(MessageWrap messageWrap) {
        textView.setText(messageWrap.message);
    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true,priority = 1)
    public void onGetMessageSticky( MessageWrap messageWrap){
        textView111.setText(messageWrap.message);
        if(stopDelivery){
            EventBus.getDefault().cancelEventDelivery(messageWrap);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
