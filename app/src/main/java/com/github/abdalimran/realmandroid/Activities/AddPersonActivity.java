package com.github.abdalimran.realmandroid.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.abdalimran.realmandroid.Models.PersonModel;
import com.github.abdalimran.realmandroid.R;
import com.github.abdalimran.realmandroid.RealmDatabase.RealmDB;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPersonActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText email;
    private EditText address;

    private RealmDB realmDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        name= (EditText) findViewById(R.id.name);
        age= (EditText) findViewById(R.id.age);
        email= (EditText) findViewById(R.id.email);
        address= (EditText) findViewById(R.id.address);
    }

    public void AddPerson(View view) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String uniqueid = ft.format(dNow);

        Log.i("uniqueid",uniqueid);

        String sname=name.getText().toString();
        String sage=age.getText().toString();
        String semail=email.getText().toString();
        String saddress=address.getText().toString();

        PersonModel person=new PersonModel();
        person.setId(uniqueid);
        person.setName(sname);
        person.setAge(Integer.parseInt(sage));
        person.setEmail(semail);
        person.setAddress(saddress);

        realmDB =new RealmDB(getApplicationContext());
        realmDB.addPerson(person);
        realmDB.closeRealm();

        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmDB.closeRealm();
    }
}
