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


import io.realm.RealmResults


class MainActivity : AppCompatActivity(), MemoCellInterface {

    private var memoModel: MemoModel? = null
    private var lvMemo: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = applicationContext as SSMemoApplication

        setContentView(R.layout.activity_main)
        application.setAppFont(findViewById(R.id.button_input_text) as TextView)
        lvMemo = findViewById(R.id.memo_list) as ListView
        lvMemo!!.divider = null

        memoModel = MemoModel(applicationContext)

        reloadList()
    }

    fun onClickEdit(view: View) {
        val i = EditActivity.createIntent(applicationContext)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        memoModel!!.close()
    }

    override fun onClickFav(memo: Memo) {
        memoModel!!.toggleFav(memo)
        reloadList()
    }

    override fun onClickDelete(memo: Memo) {
        memoModel!!.tmpDelete(memo)
        reloadList()
    }


    private fun reloadList() {
        val memos = memoModel!!.list
        lvMemo!!.adapter = MemoListAdapter(this, R.id.memo_text, memos, this)
    }
}
