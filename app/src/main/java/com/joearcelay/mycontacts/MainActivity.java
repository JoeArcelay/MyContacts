package com.joearcelay.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    Intent intent;
    DBHandler dbHandler;
    ContactList contactListAdapter;
    ListView contactListView;

    @Override
    /**
     * This method is the setup method for a specified
     * activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DBHandler(this, null);

        contactListView = (ListView) this.findViewById(R.id.contactListView);

        contactListAdapter = new ContactList(this, dbHandler.getContactList(), 0);

        contactListView.setAdapter(contactListAdapter);


        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, ViewList.class);
                intent.putExtra("_id", id);
                startActivity(intent);
            }
        });

    }

    public void openAddContact(View view){
        intent = new Intent(this, CreateContact.class);
        startActivity(intent);

    }

    @Override
    /**
     * Creates overflow menu for a activity
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    /**
     * When an item is selected this method is called
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // gets the ids of an item and compares them
        switch (item.getItemId()) {
            case R.id.action_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_contact:
                intent = new Intent(this, CreateContact.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }
}
