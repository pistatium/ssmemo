package com.appspot.pistatium.ssmemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appspot.pistatium.ssmemo.models.MemoModel;

import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmResults memos = MemoModel.getList(getApplicationContext());
    }
}
