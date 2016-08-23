package com.github.abdalimran.realmandroid.RealmDatabase;

import android.content.Context;

import com.github.abdalimran.realmandroid.Models.PersonModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmDB{

    private Realm myRealm;

    public RealmDB(Context context) {
        myRealm = Realm.getInstance(new RealmConfiguration.Builder(context)
                .name("myRealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build());
    }

    public void addPerson(PersonModel p) {
        myRealm.beginTransaction();
        PersonModel person = myRealm.createObject(PersonModel.class);
        person.setId(p.getId());
        person.setName(p.getName());
        person.setAge(p.getAge());
        person.setEmail(p.getEmail());
        person.setAddress(p.getAddress());
        myRealm.commitTransaction();
    }

    public void updatePerson(PersonModel p) {
        PersonModel person = myRealm.where(PersonModel.class).equalTo("id",p.getId()).findFirst();

        myRealm.beginTransaction();
        person.setName(p.getName());
        person.setAge(p.getAge());
        person.setEmail(p.getEmail());
        person.setAddress(p.getAddress());
        myRealm.commitTransaction();
    }

    public void deletePerson(String id) {
        PersonModel result = myRealm.where(PersonModel.class).equalTo("id",id).findFirst();
        myRealm.beginTransaction();
        result.deleteFromRealm();
        myRealm.commitTransaction();
    }

    public PersonModel getSingleData(String id){
        PersonModel person = myRealm.where(PersonModel.class).equalTo("id",id).findFirst();
        return person;
    }

    public RealmResults<PersonModel> getAllData(){
        RealmResults<PersonModel> query = myRealm.where(PersonModel.class).findAll();
        return query;
    }

    public void closeRealm() {
        myRealm.close();
    }
}
