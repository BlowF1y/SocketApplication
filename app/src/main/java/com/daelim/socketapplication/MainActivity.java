package com.daelim.socketapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daelim.socketapplication.data.MySocket;

import org.java_websocket.client.WebSocketClient;

public class MainActivity extends AppCompatActivity {

    private TextView id_text;
    private Button chat_room_btn, logout_btn;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_text = findViewById(R.id.id_text);
        chat_room_btn = findViewById(R.id.chat_room_btn);
        chat_room_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logout_btn = findViewById(R.id.logout_btn);





    }
}