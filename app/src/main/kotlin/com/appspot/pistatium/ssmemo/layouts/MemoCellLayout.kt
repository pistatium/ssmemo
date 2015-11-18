package com.appspot.pistatium.ssmemo.layouts

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.appspot.pistatium.ssmemo.EditActivity
import com.appspot.pistatium.ssmemo.R
import com.appspot.pistatium.ssmemo.SSMemoApplication
import com.appspot.pistatium.ssmemo.interfaces.MemoCellInterface
import com.appspot.pistatium.ssmemo.models.Memo
import com.appspot.pistatium.ssmemo.models.Priority
import kotlin.properties.Delegates


/**
 * Created by kimihiro on 15/11/17.
 */
class MemoCellLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    internal var tvMemo: TextView by Delegates.notNull()
    internal var vMemoTab: View by Delegates.notNull()
    internal var btnDelete: View by Delegates.notNull()
    internal var btnFav: View by Delegates.notNull()

    override fun onFinishInflate() {
        super.onFinishInflate()
        tvMemo = findViewById(R.id.memo_text) as TextView
        vMemoTab = findViewById(R.id.memo_tab)
        btnDelete = findViewById(R.id.btn_memo_delete)
        btnFav = findViewById(R.id.btn_memo_fav)
    }

    fun bindView(memo: Memo, cellInterface: MemoCellInterface) {
        val app = context.applicationContext as SSMemoApplication

        tvMemo.text = memo.memo
        app.setAppFont(tvMemo)

        if (memo.priority == Priority.HIGH.value) {
            vMemoTab.setBackgroundColor(ContextCompat.getColor(app, R.color.colorAccent))
        } else {
            vMemoTab.setBackgroundColor(ContextCompat.getColor(app, R.color.colorPrimary))
        }

        btnFav.setOnClickListener { cellInterface.onClickFav(memo) }

        btnDelete.setOnClickListener { cellInterface.onClickDelete(memo) }

        this.setOnClickListener {
            val i = EditActivity.createIntent(context, memo.id)
            context.startActivity(i)
        }
    }
}
