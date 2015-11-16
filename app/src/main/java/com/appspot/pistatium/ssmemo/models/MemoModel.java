package com.appspot.pistatium.ssmemo.models;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class MemoModel {

    private Realm realm;

    public MemoModel(Context context) {
        realm = Realm.getInstance(context);
    }

    public void close() {
        if (realm != null) {
            realm.close();
        }
    }

    @Nullable
    public Memo create() {
        Memo memo = new Memo();
        long uniqueId = System.currentTimeMillis();
        memo.setId(uniqueId);
        realm.beginTransaction();
        memo = realm.copyToRealm(memo);
        realm.commitTransaction();
        return memo;
    }

    public void beginTransaction() {
        realm.beginTransaction();
    }
    public void commitTransaction() {
        realm.commitTransaction();
    }

    @Nullable
    public Memo findById(long id) {
        return realm.where(Memo.class).equalTo("id", id).findFirst();
    }

    public RealmResults<Memo> getList() {
        RealmQuery<Memo> query = realm.where(Memo.class);
        query.equalTo("status", 1);
        return query.findAll();
    }

}