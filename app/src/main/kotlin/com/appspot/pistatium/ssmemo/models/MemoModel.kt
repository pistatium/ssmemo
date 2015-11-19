package com.appspot.pistatium.ssmemo.models

import android.content.Context

import java.util.Random

import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults


class MemoModel(context: Context) {

    private val realm: Realm?

    init {
        realm = Realm.getInstance(context)
    }

    fun close() {
        realm?.close()
    }

    fun create(): Memo {
        var memo = Memo()
        val uniqueId = System.currentTimeMillis()
        memo.id = uniqueId
        realm!!.beginTransaction()
        memo = realm.copyToRealm(memo)
        realm.commitTransaction()
        return memo
    }

    fun toggleFav(memo: Memo) {
        var priority = Priority.HIGH.value
        if (memo.priority == Priority.HIGH.value) {
            priority = Priority.LOW.value
        }
        realm!!.beginTransaction()
        memo.priority = priority
        realm.commitTransaction()
    }

    fun tmpDelete(memo: Memo) {
        realm!!.beginTransaction()
        memo.status = Status.TMP_DELETED.value
        realm.commitTransaction()
    }

    fun delete(memo: Memo) {
        realm!!.beginTransaction()
        memo.removeFromRealm()
        realm.commitTransaction()
    }

    fun beginTransaction() {
        realm!!.beginTransaction()
    }

    fun commitTransaction() {
        realm!!.commitTransaction()
    }

    fun findById(id: Long): Memo? {
        return realm!!.where(Memo::class.java).equalTo("id", id).findFirst()
    }

    val list: RealmResults<Memo>
        get() {
            val query = realm!!.where(Memo::class.java)
            query.equalTo("status", Status.ACTIVE.value)
            val result = query.findAll()
            val sort_keys = arrayOf("priority", "id")
            val sort_orders = booleanArrayOf(RealmResults.SORT_ORDER_DESCENDING, RealmResults.SORT_ORDER_DESCENDING)
            result.sort(sort_keys, sort_orders)
            return result
        }
}