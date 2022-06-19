package com.daelim.socketapplication.data;

import android.util.Log;

import com.daelim.socketapplication.ChatActivity;

import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketClient {
    public static org.java_websocket.client.WebSocketClient webSocketClient;
    ChatActivity chatActivity = new ChatActivity();

    public void wsConnect() {
        try {
            webSocketClient = new org.java_websocket.client.WebSocketClient(new URI("ws://61.83.168.88:4877")) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.e("!!!", "onOpen");
                }

                @Override
                public void onMessage(String s) {
                    Log.e("!!!", "onMessage s :" + s);
                    String[] strs = s.split("\\|");
                    if (strs.length>1){
                        String name = strs[0];
                        String content = strs[1];

                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.e("!!!", "onClose :" + s);
                }

                @Override
                public void onError(Exception e) {
                    Log.e("!!!", "onError");
                    e.printStackTrace();
                }
            };
            webSocketClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void wsDisconnect(){
        webSocketClient.close();
    }
}
