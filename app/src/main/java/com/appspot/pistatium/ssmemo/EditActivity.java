package com.appspot.pistatium.ssmemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.appspot.pistatium.ssmemo.models.Memo;
import com.appspot.pistatium.ssmemo.models.MemoModel;

import java.util.Date;

public class EditActivity extends AppCompatActivity {

    static final String EXTRA_MEMO_KEY = "extra_memo_key";
    static final long ID_NOT_SET = -1;

    private Memo memo;
    private MemoModel memoModel;
    private EditText tvInputMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvInputMemo = (EditText)findViewById(R.id.et_input_memo);

        memoModel = new MemoModel(getApplicationContext());

        long memo_id = getIntent().getLongExtra(EXTRA_MEMO_KEY, ID_NOT_SET);
        memo = memoModel.findById(memo_id);

        if (memo == null) {
            memo = memoModel.create();
        }
        Log.d("memo", "id:" + memo.getId());
        Log.d("memo", "memo:" + memo.getMemo());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        memoModel.close();
    }

    static public Intent createIntent(Context context) {
        Intent i = new Intent(context, EditActivity.class);
        return i;
    }

    static public Intent createIntent(Context context, long memoId) {
        Intent i = new Intent(context, EditActivity.class);
        i.putExtra(EXTRA_MEMO_KEY, memoId);
        return i;
    }

    public void onClickDone(View view) {
        memoModel.beginTransaction();
        memo.setMemo(tvInputMemo.getText().toString());
        memo.setUpdatedAt(new Date());
        memoModel.commitTransaction();
        finishAfterTransition();
    }
}
