package com.appspot.pistatium.ssmemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    static public Intent getIntent(Context context) {
        Intent i = new Intent(context, EditActivity.class);
        return i;
    }

    public void onClickDone(View view) {
        finishAfterTransition();
    }
}
