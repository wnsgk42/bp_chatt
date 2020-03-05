package com.example.bp_chatt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Chatdata> mDataset;
    private String nick;

    private ArrayList<Chatdata> myDataset; //각 클래스 참조변수 생성
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {//내부 클래스, ViewHolder 상속
        // each data item is just a string in this case
        public TextView TextView_nick;
        public TextView TextView_msg; //TextView 클래스 참조변수 생성

        public MyViewHolder(View v) { //생성자, v는 onCreateViewHolder에서 받아온 값들
            super(v); //TextView의 생성자를 가져옴, V로 초기화
            TextView_nick = v.findViewById(R.id.TextView_nick);//chat_row의 닉네임 연결
            TextView_msg = v.findViewById(R.id.TextView_msg);////chat_row의 닉네임 연결
        }//row의 요소들을 찾는 기능
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Chatdata> myDataset) {
        mDataset = myDataset;
    }
    //MainActivity의 data(myDataset)를 받아서 mDataset에 저장

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        myDataset = new ArrayList<>();
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v); // v라는 view를 넣어준 것
        return vh;
    }//row를 찾는 기능, inflate: xml을 개체로 변환시켜 준 것

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Chatdata chat = mDataset.get(position);
        holder.TextView_nick.setText(chat.getNick());
        holder.TextView_msg.setText(chat.getMsg());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset != null ? mDataset.size():0 ;
    }

    public void addChat(Chatdata chatData){
        mDataset.add(chatData);
        notifyItemInserted(mDataset.size()-1); //데이터 추가
    }

}