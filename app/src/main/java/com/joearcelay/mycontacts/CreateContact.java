package com.joearcelay.mycontacts;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class CreateContact extends AppCompatActivity {

    Intent intent;
    EditText nameEditText;
    EditText emailEditText;
    EditText phoneEditText;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);



        dbHandler = new DBHandler(this, null);

    }

    public void createContact (MenuItem menuItem){
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if(name.trim().equals("") || email.trim().equals("") || phone.trim().equals("")){
            Toast.makeText(this, "Please enter in a name, email, and phone number!", Toast.LENGTH_LONG).show();
        }
        else{
            dbHandler.addContactList(name, email, phone);
            Toast.makeText(this, "New Contact Created!", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    /**
     * Creates overflow menu for a activity
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_contact, menu);
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
