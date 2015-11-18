package com.appspot.pistatium.ssmemo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

import com.appspot.pistatium.ssmemo.models.Memo
import com.appspot.pistatium.ssmemo.models.MemoModel

import java.util.Date

class EditActivity : AppCompatActivity() {

    private var memo: Memo? = null
    private var memoModel: MemoModel? = null
    private var etInputMemo: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        etInputMemo = findViewById(R.id.et_input_memo) as EditText

        memoModel = MemoModel(applicationContext)

        val memo_id = intent.getLongExtra(EXTRA_MEMO_KEY, ID_NOT_SET)
        memo = memoModel!!.findById(memo_id)

        if (memo == null) {
            memo = memoModel!!.create()
        }
        etInputMemo!!.setText(memo!!.memo)
        (applicationContext as SSMemoApplication).setAppFont(etInputMemo as TextView)
    }

    override fun onDestroy() {
        super.onDestroy()
        memoModel!!.close()
    }

    fun onClickDone(view: View) {
        memoModel!!.beginTransaction()
        memo!!.memo = etInputMemo!!.text.toString()
        memo!!.updatedAt = Date()
        memoModel!!.commitTransaction()
        finishAfterTransition()
    }

    companion object {

        internal val EXTRA_MEMO_KEY = "extra_memo_key"
        internal val ID_NOT_SET: Long = -1

        fun createIntent(context: Context): Intent {
            val i = Intent(context, EditActivity::class.java)
            return i
        }

        fun createIntent(context: Context, memoId: Long): Intent {
            val i = Intent(context, EditActivity::class.java)
            i.putExtra(EXTRA_MEMO_KEY, memoId)
            return i
        }
    }
}
