package com.daelim.socketapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.daelim.socketapplication.data.MySocket;
import com.daelim.socketapplication.data.socketVO;

import java.net.URI;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity{
    private Button chat_btn;
    private EditText chat_Text;
    private MySocket sc;
    private ListView chat_list;
    private ArrayList<socketVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        try {
            chat_Text = findViewById(R.id.editText1);
            chat_btn = findViewById(R.id.btn_send1);
            chat_list = findViewById(R.id.chat_listview);
            URI uri = new URI("ws://61.83.168.88:4877");
            sc = new MySocket(uri);
            sc.connect();
            socketVO vo = new socketVO();

            sc.onMessage();
            list = new ArrayList<socketVO>();


                   list.add(vo);

                   chat_list.setAdapter(new BaseAdapter() {
                       @Override
                       public int getCount() { //사용할 데이터의 크기
                           return list.size();
                       }

                       @Override
                       public Object getItem(int i) {
                           return null;
                       }

                       @Override
                       public long getItemId(int i) {
                           return 0;
                       }

                       @Override
                       public View getView(int i, View view, ViewGroup viewGroup) {
                           view = getLayoutInflater().inflate(R.layout.chat_layout_left,viewGroup,false);


                           TextView tx_view_1 = view.findViewById(R.id.tx_1);

                           TextView tx_view_2 = view.findViewById(R.id.tx_2);
                           tx_view_1.setText(list.get(i).getId());
                           tx_view_2.setText(list.get(i).getMessage());

                            //runnabluitread
                           return view;
                       }


            });
            chat_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sc.send(chat_Text.getText().toString());
                    chat_Text.setText("");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        try {
            URI uri = new URI("ws://61.83.168.88:4877");
            sc = new MySocket(uri);
            sc.close();
        }catch (Exception ee){
            ee.printStackTrace();
        }
    }


}