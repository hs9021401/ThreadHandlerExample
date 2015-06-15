package com.foxlinkimage.alex.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    Button btnStart;
    Button btnStop;
    TextView txtCount;
    Boolean start = true;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.start);
        btnStop = (Button)findViewById(R.id.stop);
        txtCount = (TextView) findViewById(R.id.count);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(start)
                        {
                            counter ++;
                            Message msg = new Message();
                            msg.what = counter;
                            uiMessageHandler.sendMessage(msg);

//                            Bundle bundle = new Bundle();
//                            bundle.putString("key1", "value1");
//                            bundle.putString("key2", "value2");
//                            msg.setData(bundle);
//                            uiMessageHandler.sendMessage(msg);


                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = false;
            }
        });
    }
    Handler uiMessageHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            txtCount.setText(String.valueOf(msg.what));
            super.handleMessage(msg);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
