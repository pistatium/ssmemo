package com.appspot.pistatium.ssmemo.models;

import android.content.Context;
import android.support.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class MemoModel {

    @Nullable
    public static Memo create(Context context, Memo memo) {
        try (Realm realm = Realm.getInstance(context)) {
            realm.beginTransaction();
            memo = realm.copyToRealm(memo);
            realm.commitTransaction();
            return memo;
        } catch (Exception e) {
            return null;
        }
    }

    public static void update(Context context, Memo memo) {
        try (Realm realm = Realm.getInstance(context)) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(memo);
            realm.commitTransaction();
        }
    }

    public static RealmResults<Memo> getList(Context context) {
        try (Realm realm = Realm.getInstance(context)) {
            RealmQuery<Memo> query = realm.where(Memo.class);
            query.equalTo("status", 1);
            return query.findAll();
        }
    }

}