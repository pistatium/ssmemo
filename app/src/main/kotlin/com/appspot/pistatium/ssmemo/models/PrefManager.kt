package com.appspot.pistatium.ssmemo.models

import android.content.Context
import android.content.SharedPreferences


fun getPref(context: Context): SharedPreferences {
    return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
}

enum class BooleanPref(val default: Boolean) {
    IS_FIRST_LAUNCH(true)

    ;
    fun get(context: Context):Boolean {
        return getPref(context).getBoolean(this.name, this.default)
    }
    fun set(context: Context, value: Boolean): Boolean {
        return getPref(context).edit().putBoolean(this.name, value).commit()
    }
}