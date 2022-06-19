package com.daelim.socketapplication.data;

import static androidx.recyclerview.widget.RecyclerView.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daelim.socketapplication.R;

import java.util.ArrayList;

public class listAdapter extends BaseAdapter {
    private final ArrayList<socketVO> list;
    Context context1 = null;
    LayoutInflater layoutInflater = null;
    String id;

    public listAdapter(Context context, ArrayList<socketVO> list) {
        this.list = list;
        context1 = context;
        layoutInflater = LayoutInflater.from(context1);
        id = "";
    }

    @Override
    public int getCount() {
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
        View view2 = null;
        ViewHolder viewHolder = new ViewHolder();
            if(view2 == null){
                switch (list.get(i).getType()){
                    case "Login":
                        view2 = layoutInflater.inflate(R.layout.chat_layout_center, null);
                        viewHolder.center_text = view2.findViewById(R.id.center_text);
                        viewHolder.center_text.setText(list.get(i).getId()+"님이 입갤");
                        break;
                    case "Chat":
                        if (id.equals("id")){
                            view2 = layoutInflater.inflate(R.layout.chat_layout_right,null);
                            viewHolder.right_text = view2.findViewById(R.id.right_text);
                            viewHolder.right_text.setText(list.get(i).getMessage());
                        }else{
                            view2 = layoutInflater.inflate(R.layout.chat_layout_left,null);
                            viewHolder.left_text = view2.findViewById(R.id.left_text);
                            viewHolder.left_id = view2.findViewById(R.id.left_id);

                            viewHolder.left_id.setText(list.get(i).getId());
                            viewHolder.left_text.setText(list.get(i).getMessage());

                        }
                        break;
                    case "Logout":
                        view2 = layoutInflater.inflate(R.layout.chat_layout_center, null);
                        viewHolder.center_text = view2.findViewById(R.id.center_text);
                        viewHolder.center_text.setText(list.get(i).getId()+"님이 퇴갤");
                        break;
                }
                view2.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view2.getTag();
            }
        return view2;
    }

    public void Login(String id){
        socketVO socketVO = new socketVO();
        socketVO.setId(id);
        list.add(socketVO);
    }

    public void chat(String Type,String id, String msg){
        socketVO socketVO = new socketVO();
        socketVO.setType(Type);
        socketVO.setId(id);
        socketVO.setMessage(msg);
        list.add(socketVO);
    }


    static class ViewHolder{
        TextView left_text, left_id, center_text, right_text;
    }
}
