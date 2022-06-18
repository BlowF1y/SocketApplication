package com.daelim.socketapplication.data;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;


public class MySocket extends WebSocketClient{
    socketVO vo = new socketVO();
    public MySocket(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        Log.e("connect","입장");
    }

    @Override
    public void onMessage(String s) {
        Log.e("message", s);
        String[] array = s.split("|");
        String id = array[1];
        String message = array[2];
        vo.setId(id);
        vo.setMessage(message);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        Log.e("close","close");
    }


    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }


}
