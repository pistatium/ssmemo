package com.appspot.pistatium.ssmemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView

import com.appspot.pistatium.ssmemo.interfaces.MemoCellInterface
import com.appspot.pistatium.ssmemo.adapters.MemoListAdapter
import com.appspot.pistatium.ssmemo.models.Memo
import com.appspot.pistatium.ssmemo.models.MemoModel

import kotlinx.android.synthetic.activity_main.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), MemoCellInterface {

    private var memoModel: MemoModel by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val application = applicationContext as SSMemoApplication
        application.setAppFont(button_input_text)

        memo_list.divider = null

        memoModel = MemoModel(applicationContext)
        reloadList()
    }

    fun onClickEdit(view: View) {
        val i = EditActivity.createIntent(applicationContext)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        memoModel.close()
    }

    override fun onClickFav(memo: Memo) {
        memoModel.toggleFav(memo)
        reloadList()
    }

    override fun onClickDelete(memo: Memo) {
        memoModel.tmpDelete(memo)
        reloadList()
    }

    private fun reloadList() {
        val memos = memoModel.list
        memo_list.adapter = MemoListAdapter(this, R.id.memo_text, memos, this)
    }
}
