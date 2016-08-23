package com.github.abdalimran.realmandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.abdalimran.realmandroid.Models.PersonModel;
import com.github.abdalimran.realmandroid.R;
import com.github.abdalimran.realmandroid.RealmDatabase.RealmDB;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private ListView personNameList;
    private RealmResults<PersonModel> realmResults;
    private RealmDB realmDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        personNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                PersonModel personDetail = (PersonModel) personNameList.getAdapter().getItem(position);
                String pid = personDetail.getId();

                Intent intent=new Intent(getApplicationContext(),UpdateDeleteActivity.class);
                intent.putExtra("PersonID", pid);
                startActivity(intent);
            }
        });
    }

    void initView(){
        personNameList = (ListView) findViewById(R.id.personNameList);

        realmDB =new RealmDB(this);
        realmResults= realmDB.getAllData();

        ArrayAdapter<PersonModel> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,realmResults);
        personNameList.setAdapter(adapter);

        registerForContextMenu(personNameList);
    }

    public void AddPerson(View view) {
        Intent intent=new Intent(this,AddPersonActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        PersonModel personDetail = (PersonModel) personNameList.getAdapter().getItem(info.position);
        String pid = personDetail.getId();

        switch (item.getItemId()) {
            case R.id.menu_edit:
                Intent intent=new Intent(getApplicationContext(),UpdateDeleteActivity.class);
                intent.putExtra("PersonID", pid);
                startActivity(intent);
                return true;
            case R.id.menu_delete:
                realmDB.deletePerson(pid);
                realmDB.closeRealm();
                initView();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmDB.closeRealm();
    }
}
