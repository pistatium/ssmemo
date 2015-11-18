package com.appspot.pistatium.ssmemo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import com.appspot.pistatium.ssmemo.EditActivity
import com.appspot.pistatium.ssmemo.R
import com.appspot.pistatium.ssmemo.SSMemoApplication
import com.appspot.pistatium.ssmemo.interfaces.MemoCellInterface
import com.appspot.pistatium.ssmemo.layouts.MemoCellLayout
import com.appspot.pistatium.ssmemo.models.Memo

/**
 * Created by kimihiro on 15/11/13.
 */
class MemoListAdapter(context: Context, resource: Int, objects: List<Memo>, private val cellInterface: MemoCellInterface): ArrayAdapter<Memo>(context, resource, objects) {
    private val inflater: LayoutInflater

    init {
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: MemoCellLayout
        val memo = getItem(position)
        if (convertView == null) {
            view = inflater.inflate(R.layout.memo_cell, null) as MemoCellLayout
        } else {
            view = convertView as MemoCellLayout
        }
        view.bindView(memo, cellInterface)
        return view
    }
}
