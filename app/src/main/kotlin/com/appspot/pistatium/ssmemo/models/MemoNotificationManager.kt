package com.appspot.pistatium.ssmemo.models

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.NotificationCompat
import com.appspot.pistatium.ssmemo.R

/**
 * Created by kimihiro on 2015/11/23.
 */
class MemoNotificationManager(context:Context) {

    val context = context

    fun notify(memo: Memo) {
        val builder = NotificationCompat.Builder(context)
        val manager = NotificationManagerCompat.from(context);
        builder.setSmallIcon(R.mipmap.icon512)
        builder.setContentTitle(context.getString(R.string.app_name))
        builder.setContentText(memo.memo)
        manager.notify(getNotificationId(memo), builder.build())
    }

    private fun getNotificationId(memo: Memo): Int {
        return (memo.id % 100000) as Int
    }
}