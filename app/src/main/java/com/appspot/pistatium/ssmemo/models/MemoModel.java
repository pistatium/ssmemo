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

    public void toggleFav(Memo memo) {
        int priority = Priority.HIGH.value;
        if (memo.getPriority() == Priority.HIGH.value) {
            priority = Priority.LOW.value;
        }
        realm.beginTransaction();
        memo.setPriority(priority);
        realm.commitTransaction();
    }

    public void tmpDelete(Memo memo) {
        realm.beginTransaction();
        memo.setStatus(Status.TMP_DELETED.value);
        realm.commitTransaction();
    }

    public void delete(Memo memo) {
        realm.beginTransaction();
        memo.removeFromRealm();
        realm.commitTransaction();
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
        query.equalTo("status", Status.ACTIVE.value);
        RealmResults<Memo>result = query.findAll();
        String[] sort_keys = {"priority", "id"};
        boolean[] sort_orders = {RealmResults.SORT_ORDER_DESCENDING, RealmResults.SORT_ORDER_DESCENDING};
        result.sort(sort_keys, sort_orders);
        return result;
    }

}