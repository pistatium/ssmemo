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
import com.appspot.pistatium.ssmemo.layouts.MemoCellLayout;
import com.appspot.pistatium.ssmemo.models.Memo;

import java.util.List;

/**
 * Created by kimihiro on 15/11/13.
 */
public class MemoListAdapter extends ArrayAdapter<Memo> {

    private MemoCellInterface cellInterface;
    private LayoutInflater inflater;

    public MemoListAdapter(Context context, int resource, List<Memo> objects, MemoCellInterface cellInterface) {
        super(context, resource, objects);
        this.cellInterface = cellInterface;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MemoCellLayout view;
        final Memo memo = getItem(position);
        if (convertView == null) {
            view = (MemoCellLayout)inflater.inflate(R.layout.memo_cell, null);
        } else {
            view = (MemoCellLayout) convertView;
        }
        view.bindView(memo, cellInterface);
        return view;
    }
}
