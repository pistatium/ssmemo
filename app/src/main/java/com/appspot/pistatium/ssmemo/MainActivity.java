package com.appspot.pistatium.ssmemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.appspot.pistatium.ssmemo.adapters.MemoCellInterface;
import com.appspot.pistatium.ssmemo.adapters.MemoListAdapter;
import com.appspot.pistatium.ssmemo.models.Memo;
import com.appspot.pistatium.ssmemo.models.MemoModel;



import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements MemoCellInterface {

    private MemoModel memoModel;
    private ListView lvMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SSMemoApplication application = (SSMemoApplication)getApplicationContext();

        setContentView(R.layout.activity_main);
        application.setAppFont((TextView) findViewById(R.id.button_input_text));
        lvMemo = (ListView) findViewById(R.id.memo_list);
        lvMemo.setDivider(null);

        memoModel = new MemoModel(getApplicationContext());

        reloadList();
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

    @Override
    public void onClickFav(Memo memo) {
        memoModel.setFav(memo);
        reloadList();
    }

    @Override
    public void onClickDelete(Memo memo) {
        memoModel.tmpDelete(memo);
        reloadList();
    }


    private void reloadList() {
        RealmResults memos = memoModel.getList();
        lvMemo.setAdapter(new MemoListAdapter(this, R.id.memo_text, memos, this));
    }
}
