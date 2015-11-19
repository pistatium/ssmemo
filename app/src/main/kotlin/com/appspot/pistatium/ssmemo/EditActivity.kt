package com.appspot.pistatium.ssmemo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

import com.appspot.pistatium.ssmemo.models.Memo
import com.appspot.pistatium.ssmemo.models.MemoModel

import java.util.Date

import kotlinx.android.synthetic.activity_edit.*
import kotlin.properties.Delegates

class EditActivity : AppCompatActivity() {

    private var memo: Memo by Delegates.notNull()
    private var memoModel: MemoModel by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        memoModel = MemoModel(applicationContext)

        val memo_id = intent.getLongExtra(EXTRA_MEMO_KEY, ID_NOT_SET)
        memo = memoModel.findById(memo_id) ?: memoModel.create()

        et_input_memo.setText(memo.memo)
        (applicationContext as SSMemoApplication).setAppFont(et_input_memo as TextView)
    }

    override fun onDestroy() {
        super.onDestroy()
        memoModel.close()
    }

    fun onClickDone(view: View) {
        memoModel.beginTransaction()
        memo.memo = et_input_memo.text.toString()
        memo.updatedAt = Date()
        memoModel.commitTransaction()
        finishAfterTransition()
    }

    companion object {

        internal val EXTRA_MEMO_KEY = "extra_memo_key"
        internal val ID_NOT_SET: Long = -1

        fun createIntent(context: Context): Intent {
            return Intent(context, EditActivity::class.java)
        }

        fun createIntent(context: Context, memoId: Long): Intent {
            val i = Intent(context, EditActivity::class.java)
            i.putExtra(EXTRA_MEMO_KEY, memoId)
            return i
        }
    }
}
