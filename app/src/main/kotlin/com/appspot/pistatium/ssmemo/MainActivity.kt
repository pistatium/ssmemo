package com.appspot.pistatium.ssmemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ListView
import android.widget.TextView

import com.appspot.pistatium.ssmemo.interfaces.MemoCellInterface
import com.appspot.pistatium.ssmemo.adapters.MemoListAdapter
import com.appspot.pistatium.ssmemo.models.Memo
import com.appspot.pistatium.ssmemo.models.MemoModel
import com.appspot.pistatium.ssmemo.models.MemoNotificationManager
import com.appspot.pistatium.ssmemo.models.Priority
import com.google.android.gms.analytics.HitBuilders
import jp.maru.mrd.IconLoader

import kotlinx.android.synthetic.activity_main.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), MemoCellInterface {

    private var memoModel: MemoModel by Delegates.notNull()
    private var iconLoader: IconLoader<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val application = applicationContext as SSMemoApplication
        application.setAppFont(button_input_text)

        memo_list.divider = null

        memoModel = MemoModel(applicationContext)
        initAds()
        reloadList()
        trackActivity()
    }

    override fun onResume() {
        super.onResume()
        iconLoader?.startLoading()
        reloadList()
    }

    override fun onPause() {
        iconLoader?.stopLoading()
        super.onPause()
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
        if (memo.priority == Priority.HIGH.value) {
            val manager = MemoNotificationManager(applicationContext)
            manager.notify(memo)
        }
        reloadList()
    }

    override fun onClickDelete(memo: Memo) {
        memoModel.tmpDelete(memo)
        reloadList()
    }

    private fun reloadList(): Unit {
        val memos = memoModel.list
        memo_list.adapter = MemoListAdapter(this, R.id.memo_text, memos, this)
    }

    private fun initAds(): Unit {
        iconLoader = IconLoader<Int>(BuildConfig.ASUTA_ICON_AD, this)
        iconLoader?.let {
            asuta_icon_cell.addToIconLoader(iconLoader)
            asuta_icon_cell.titleColor = ContextCompat.getColor(applicationContext,R.color.baseBackground)
        }
    }

    private fun trackActivity() {
        val tracker = (application as SSMemoApplication).getDefaultTracker()
        tracker?.setScreenName(this.localClassName)
        tracker?.send(HitBuilders.ScreenViewBuilder().build())
    }
}
