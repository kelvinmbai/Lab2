package strathmore.com.sqlitelab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });DatabaseHandler db = new DatabaseHandler(this);
        /**
         * CRUD OPERATIONS
         */
        //Inserting Contacts
        Log.d("Insert:","Inserting..");
        db.addContact(new Contacts("Rave","9100000000"));
        db.addContact(new Contacts("Asoso","7889900000"));
        db.addContact(new Contacts("Dunkirk","78899000000"));
        db.addContact(new Contacts("Akaayki","563700000"));

        Log.d("Reading:","Reading all contacts...");
        List<Contacts> contacts = db.getAllContacts();
        for (Contacts cn:contacts){
            String log = "Id:" +cn.getID()+" , Name "+cn.getName()+",Phone "+cn.getPhoneNumber();
            Log.d("Name: ", log);
        }
        //insert Users
        Log.d("Insert:","Inserting..");
        db.addUser(new Users("Wilson","Ojal","Kisumu"));
        db.addUser(new Users("Kelvin","Mbai","Kiambu"));



        Log.d("Reading:","Reading all contacts...");
        List<Users> users = db.getAllUsers();
        for (Users cn:users){
            String log = "Id:" +cn.getUSER()+" , fisrtname: "+cn.getFirstName()+",LastName: "+cn.getLastName()+",Residence: "+cn.getResidence();
            Log.d("fisrtname: ", log);


        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
