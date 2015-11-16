package com.appspot.pistatium.ssmemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appspot.pistatium.ssmemo.EditActivity;
import com.appspot.pistatium.ssmemo.R;
import com.appspot.pistatium.ssmemo.SSMemoApplication;
import com.appspot.pistatium.ssmemo.models.Memo;

import java.util.List;

/**
 * Created by kimihiro on 15/11/13.
 */
public class MemoListAdapter extends ArrayAdapter<Memo> {

    private LayoutInflater inflater;

    public MemoListAdapter(Context context, int resource, List<Memo> objects) {
        super(context, resource, objects);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Memo memo = getItem(position);
        SSMemoApplication app = (SSMemoApplication)getContext().getApplicationContext();
        if (view == null) {
            view = inflater.inflate(R.layout.memo_cell, null);
        }
        TextView tvMemo = (TextView)view.findViewById(R.id.memo_text);
        tvMemo.setText(memo.getMemo());
        app.setAppFont(tvMemo);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent i = EditActivity.createIntent(context, memo.getId());
                context.startActivity(i);
            }
        });
        //Memo memo = getItem(position);
        return view;
    }

}
