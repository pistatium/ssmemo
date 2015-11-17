package com.appspot.pistatium.ssmemo.layouts;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appspot.pistatium.ssmemo.EditActivity;
import com.appspot.pistatium.ssmemo.R;
import com.appspot.pistatium.ssmemo.SSMemoApplication;
import com.appspot.pistatium.ssmemo.adapters.MemoCellInterface;
import com.appspot.pistatium.ssmemo.models.Memo;
import com.appspot.pistatium.ssmemo.models.Priority;

/**
 * Created by kimihiro on 15/11/17.
 */
public class MemoCellLayout extends LinearLayout {
    TextView tvMemo;
    View vMemoTab;
    View btnDelete;
    View btnFav;

    public MemoCellLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvMemo = (TextView)findViewById(R.id.memo_text);
        vMemoTab = findViewById(R.id.memo_tab);
        btnDelete = findViewById(R.id.btn_memo_delete);
        btnFav = findViewById(R.id.btn_memo_fav);
    }

    public void bindView(final Memo memo, final MemoCellInterface cellInterface) {
        SSMemoApplication app = (SSMemoApplication)getContext().getApplicationContext();
        tvMemo.setText(memo.getMemo());
        app.setAppFont(tvMemo);

        if (memo.getPriority() == Priority.HIGH.value) {
            vMemoTab.setBackgroundColor(ContextCompat.getColor(app, R.color.colorAccent));
        } else {
            vMemoTab.setBackgroundColor(ContextCompat.getColor(app, R.color.colorPrimary));
        }
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellInterface.onClickFav(memo);

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellInterface.onClickDelete(memo);
            }
        });
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent i = EditActivity.createIntent(context, memo.getId());
                context.startActivity(i);
            }
        });
    }
}
