package com.appspot.pistatium.ssmemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.appspot.pistatium.ssmemo.R;
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
        if (view == null) {
            view = inflater.inflate(R.layout.memo_cell, null);
        }
        //Memo memo = getItem(position);
        return view;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
