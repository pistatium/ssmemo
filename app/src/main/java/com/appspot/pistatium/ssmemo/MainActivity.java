package com.appspot.pistatium.ssmemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.appspot.pistatium.ssmemo.adapters.MemoListAdapter;
import com.appspot.pistatium.ssmemo.models.MemoModel;



import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    private MemoModel memoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SSMemoApplication application = (SSMemoApplication)getApplicationContext();

        setContentView(R.layout.activity_main);
        application.setAppFont((TextView) findViewById(R.id.button_input_text));
        ListView lvMemo = (ListView) findViewById(R.id.memo_list);

        memoModel = new MemoModel(getApplicationContext());

        RealmResults memos = memoModel.getList();

        lvMemo.setDivider(null);
        lvMemo.setAdapter(new MemoListAdapter(this, R.id.memo_text, memos));
    }

    public void onClickEdit(View view) {
        Intent i = EditActivity.createIntent(getApplicationContext());
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        memoModel.close();
    }
}
