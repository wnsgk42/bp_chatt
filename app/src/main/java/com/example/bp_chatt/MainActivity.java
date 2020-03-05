package com.example.bp_chatt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends Activity {
    Button Button_send;
    EditText EditText_msg;

    private RecyclerView recyclerView; // RecyclerView 클래스를 사용키 위한 참조변수
    private RecyclerView.Adapter mAdapter; // RecyclerView 클래스를 사용키 위한 참조변수
    private RecyclerView.LayoutManager layoutManager;// RecyclerView 클래스를 사용키 위한 참조변수
    private ArrayList<Chatdata> myDataset;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String nick="data_nick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_chat);//RecyclerView 참조

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        Button_send = findViewById(R.id.Button_send);
        EditText_msg = findViewById(R.id.EditText_msg);

        Button_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Chatdata chatdata= new Chatdata();
                chatdata.setMsg(EditText_msg.getText().toString());
                chatdata.setNick(nick);
                myRef.push().setValue(chatdata);
            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Chatdata chat =dataSnapshot.getValue(Chatdata.class);
                ((MyAdapter)mAdapter).addChat(chat);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);//크기 고정

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);//layout manager 인스턴스생성 및 context 생성자 호춯
        recyclerView.setLayoutManager(layoutManager);

        myDataset = new ArrayList<>();
        myDataset.add(new Chatdata("wrp","hi"));//list에 임의의 값 지정

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

    }
    // ...
}