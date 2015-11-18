package com.appspot.pistatium.ssmemo.models

import java.io.Serializable
import java.util.Date

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlin.properties.Delegates

/**
 * Created by kimihiro on 2015/11/08.
 */
class Memo : RealmObject() {

    @PrimaryKey
    var id: Long by Delegates.notNull()
    var title = ""
    var memo = ""
    var createdAt = Date()
    var updatedAt = Date()
    var dueAt: Date? = null
    var priority: Int = Priority.LOW.value
    var status: Int = Status.ACTIVE.value
}
