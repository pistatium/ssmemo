package com.appspot.pistatium.ssmemo.models

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationManagerCompat

import com.appspot.pistatium.ssmemo.EditActivity
import com.appspot.pistatium.ssmemo.R

/**
 * Created by kimihiro on 2015/11/23.
 */
class MemoNotificationManager(context:Context) {

    val context = context

    fun notify(memo: Memo) {
        val builder = Notification.Builder(context)
        val manager = NotificationManagerCompat.from(context)
        val memoId = getNotificationId(memo)
        builder.setSmallIcon(R.mipmap.icon_notice)
        builder.setContentTitle(context.getString(R.string.app_name))
        builder.setContentText(memo.memo)
        val intent = EditActivity.createIntent(context, memo.id)
        val contentIntent = PendingIntent.getActivity(context, memoId, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(contentIntent)
        builder.setAutoCancel(true)
        builder.setStyle(Notification.BigTextStyle().bigText(memo.memo))
        manager.notify(memoId, builder.build())
    }

    private fun getNotificationId(memo: Memo): Int {
        return (memo.id % 100000).toInt()
    }

}