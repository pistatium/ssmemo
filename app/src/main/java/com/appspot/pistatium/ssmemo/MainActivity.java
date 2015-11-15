package com.appspot.pistatium.ssmemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

        SSMemoApplication application = (SSMemoApplication)getApplicationContext();

        setContentView(R.layout.activity_main);
        //RealmResults memos = MemoModel.getList(getApplicationContext());
        application.setAppFont((TextView) findViewById(R.id.button_input_text));
        ListView lvMemo = (ListView) findViewById(R.id.memo_list);
        List<Memo> memos = new ArrayList<>();
        lvMemo.setDivider(null);
        lvMemo.setAdapter(new MemoListAdapter(this, R.id.memo_text, memos));

    }

    public void onClickEdit(View view) {
        Intent i = EditActivity.getIntent(getApplicationContext());
        startActivity(i);
    }
}
