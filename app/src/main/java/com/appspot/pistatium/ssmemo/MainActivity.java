package com.appspot.pistatium.ssmemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.appspot.pistatium.ssmemo.adapters.MemoListAdapter;
import com.appspot.pistatium.ssmemo.models.Memo;
import com.appspot.pistatium.ssmemo.models.MemoModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RealmResults memos = MemoModel.getList(getApplicationContext());
        ListView lvMemo = (ListView)findViewById(R.id.memo_list);
        List<Memo> memos = new ArrayList<>();
        lvMemo.setDivider(null);
        lvMemo.setAdapter(new MemoListAdapter(getApplicationContext(), R.id.memo_text , memos));

    }
}
