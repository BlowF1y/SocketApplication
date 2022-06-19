package com.daelim.socketapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.daelim.socketapplication.data.listAdapter;
import com.daelim.socketapplication.data.socketVO;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity{
    private Button chat_btn;
    private EditText chat_Text;
    private ListView chat_list;
    private ArrayList<socketVO> list;
    private listAdapter listAdapter;
    public WebSocketClient sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        try {


            URI uri = new URI("ws://61.83.168.88:4877");
            list = new ArrayList<socketVO>();
            listAdapter = new listAdapter(this,list);

            chat_Text = (EditText) findViewById(R.id.btn_text);
            chat_btn = (Button) findViewById(R.id.btn_send1);

            chat_list = findViewById(R.id.chat_listview);
            chat_list.setAdapter(listAdapter);

            sc = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.e("!!!", "onOpen");
                }

                @Override
                public void onMessage(String s) {
                    Log.e("message",s);
                    String[] str = s.split("|");
                    if(str.length>1){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String type = str[0];
                                String id = str[1];
                                String msg = str[2];
                                list.add(new socketVO(type, id, msg));
                                String nowTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                                switch (type){
                                    case "Login":
                                        listAdapter.Login(id);
                                        break;
                                    case "Chat":
                                        listAdapter.chat(type, id, msg);
                                        break;
                                }
                                listAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.e("close","close");
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }


            };
            sc.connect();
            sc.send("Login|"+"id");

            chat_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String msg = chat_Text.getText().toString();
                    String str = "Chat|" + "id" + "|" + msg;
                    sc.send(str);
                    chat_Text.setText("");
                }
            });

        } catch (Exception e2) {
            e2.printStackTrace();
        }


    }

    public void serverDisconnect() {
        sc.close();
    }

}