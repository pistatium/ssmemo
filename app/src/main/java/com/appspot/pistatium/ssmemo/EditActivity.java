package com.appspot.pistatium.ssmemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.appspot.pistatium.ssmemo.models.Memo;
import com.appspot.pistatium.ssmemo.models.MemoModel;

public class EditActivity extends AppCompatActivity {

    static final String EXTRA_MEMO_KEY = "extra_memo_key";

    private Memo memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        memo = (Memo)getIntent().getSerializableExtra(EXTRA_MEMO_KEY);
        if (memo == null) {
            memo = new Memo();
        }

        Log.d("memo", memo.getTitle());
    }

    static public Intent createIntent(Context context) {
        Intent i = new Intent(context, EditActivity.class);
        return i;
    }

    static public Intent createIntent(Context context, Memo memo) {
        Intent i = new Intent(context, EditActivity.class);
        i.putExtra(EXTRA_MEMO_KEY, memo);
        return i;
    }

    public void onClickDone(View view) {
        MemoModel.create(getApplicationContext(), memo);
        finishAfterTransition();
    }
}
