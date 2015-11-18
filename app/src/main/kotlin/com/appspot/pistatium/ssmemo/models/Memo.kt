package com.appspot.pistatium.ssmemo.models

import java.io.Serializable
import java.util.Date

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlin.properties.Delegates

/**
 * Created by kimihiro on 2015/11/08.
 */

@RealmClass
public open class Memo: RealmObject() {

    @PrimaryKey
    public open var id: Long = -1
    public open var title = ""
    public open var memo = ""
    public open var createdAt = Date()
    public open var updatedAt = Date()
    public open var dueAt: Date? = null
    public open var priority: Int = Priority.LOW.value
    public open var status: Int = Status.ACTIVE.value
}
