package com.example.bp_chatt;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class Chatdata {
    private String nick;
    private String msg;

    public Chatdata(){
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Chatdata (String nick, String msg){
        this.nick=nick;
        this.msg=msg;
    }
}