package com.github.abdalimran.realmandroid.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PersonModel extends RealmObject
{
    @PrimaryKey
    private String id;
    private String name;
    private String email;
    private String address;
    private int age;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return  "ID: "+getId()+"\n"+
                "Name: "+getName()+"\n"+
                "Age: "+getAge()+"\n"+
                "Email: "+getEmail()+"\n"+
                "Address: "+getAddress()+"\n";
    }
}