package com.github.abdalimran.realmandroid.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.github.abdalimran.realmandroid.Models.PersonModel;
import com.github.abdalimran.realmandroid.R;
import com.github.abdalimran.realmandroid.RealmDatabase.RealmDB;

public class UpdateDeleteActivity extends AppCompatActivity {

    private EditText updtname;
    private EditText updtage;
    private EditText updtemail;
    private EditText updtaddress;

    private RealmDB realmDB;
    private PersonModel personDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        realmDB =new RealmDB(this);

        updtname= (EditText) findViewById(R.id.updt_name);
        updtage= (EditText) findViewById(R.id.updt_age);
        updtemail= (EditText) findViewById(R.id.updt_email);
        updtaddress= (EditText) findViewById(R.id.updt_address);

        Bundle data = getIntent().getExtras();
        String pid = data.getString("PersonID");
        personDetail = realmDB.getSingleData(pid);

        ShowPersonData();
    }

    private void ShowPersonData() {
        updtname.setText(personDetail.getName());
        updtage.setText(String.valueOf(personDetail.getAge()));
        updtemail.setText(personDetail.getEmail());
        updtaddress.setText(personDetail.getAddress());
    }

    public void DeletePerson(View view) {
        realmDB.deletePerson(personDetail.getId());
        realmDB.closeRealm();
        this.finish();
    }

    public void UpdatePerson(View view) {
        PersonModel person=new PersonModel();
        person.setId(personDetail.getId());
        person.setName(updtname.getText().toString());
        person.setAge(Integer.parseInt(updtage.getText().toString()));
        person.setEmail(updtemail.getText().toString());
        person.setAddress(updtaddress.getText().toString());

        realmDB.updatePerson(person);
        realmDB.closeRealm();

        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmDB.closeRealm();
    }
}
