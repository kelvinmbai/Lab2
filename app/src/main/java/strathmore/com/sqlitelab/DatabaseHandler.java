package strathmore.com.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilson on 17/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
//All Static variables
    //Database Version
    public static final int DATABASE_VERSION =1;

    //Databasse name
    private static final String DATABASE_NAME ="contactsManager";

    //Contact table name
    private static final String TABLE_CONTACTS ="contacts";
    //*******Table2*****
    public static final String TABLE_USERS ="users";

    //Contact Table Column names

    private static final String KEY_ID ="id";
    private static final String KEY_NAME ="name";
    private static final String KEY_PH_NO ="phone_number";

    //users table column names
    private static final String KEY_USER="user_id";
    private static final String KEY_FNAME="firstname";
    private static final String KEY_LNAME="lastname";
    private static final String KEY_RESIDENCE="residence";


    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_CONTACTS_TABLE ="CREATE TABLE " +TABLE_CONTACTS+ "(" +KEY_ID+ " INTEGER PRIMARY KEY," +KEY_NAME+" TEXT,"+KEY_PH_NO+" TEXT"+")";
        String CREATE_USERS_TABLE ="CREATE TABLE " +TABLE_USERS+ "(" +KEY_USER+ " INTEGER PRIMARY KEY," +KEY_FNAME+" TEXT," +KEY_LNAME+" TEXT,"+KEY_RESIDENCE+" TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    //Drop older table if exixted
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);

        //Create table again
        onCreate(db);
    }
    //adding new contact
    public void addContact(Contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());//Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber());//Contact Phone Number
        //Inserting row
        db.insert(TABLE_CONTACTS,null, values);
        db.close();
    }
     //add a new user

    public void addUser(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, user.getFirstName());//Contact Name
        values.put(KEY_LNAME, user.getLastName());//Contact Phone Number
        values.put(KEY_RESIDENCE, user.getResidence());//Contact Phone Number
        //Inserting row
        db.insert(TABLE_USERS,null, values);
        db.close();
    }



    //Getting single contact
    public Contacts getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,KEY_NAME,KEY_PH_NO },KEY_ID +"=?",new String[]{ String.valueOf(id) },null,null,null,null);
        if (cursor !=null)
            cursor.moveToFirst();
        Contacts contact = new Contacts (Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        //return contract
        return contact;
    }
    //Getting a user
    public Users getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_USER,KEY_FNAME,KEY_LNAME,KEY_RESIDENCE },KEY_USER +"=?",new String[]{ String.valueOf(id) },null,null,null,null);
        if (cursor !=null)
            cursor.moveToFirst();
        Users user = new Users(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        //return contract
        return user;
    }




    //Getting all Contacts
    public List<Contacts> getAllContacts(){
        List<Contacts> contactList = new ArrayList<Contacts>();
        //Select all querry
        String selectQuery = "SELECT * FROM "+ TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Contacts contact = new Contacts();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //Adding contact to list
                contactList.add(contact);

            }while(cursor.moveToNext());
        }
        return contactList;
    }
//Get all users
    public List<Users> getAllUsers(){
        List<Users> userList = new ArrayList<Users>();
        //Select all querry
        String selectQuery = "SELECT * FROM "+ TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Users user = new Users();
                user.setUSER(Integer.parseInt(cursor.getString(0)));
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                user.setResidence(cursor.getString(3));

                //Adding contact to list
                userList.add(user);

            }while(cursor.moveToNext());
        }
        return userList;
    }



    //getting contacts count
    public int getContactsCount(){
        String countQuery = "SELECT * FROM" + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();

    }
    //getting users count
    public int getUsersCount(){
        String countQuery = "SELECT * FROM" + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();

    }


    //Updating single contact
    public int updateContact(Contacts contact){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PH_NO,contact.getPhoneNumber());




        //updating row
        return db.update(TABLE_CONTACTS, values,KEY_ID +"=?",new String[]{ String.valueOf(contact.getID())} );
    }
//
public int updateUser(Users user){

    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();

        values.put(KEY_FNAME,user.getFirstName());
        values.put(KEY_LNAME,user.getLastName());
        values.put(KEY_RESIDENCE,user.getResidence());




    //updating row
        return db.update(TABLE_USERS, values,KEY_USER +"=?",new String[]{ String.valueOf(user.getUSER())} );
}
    //Deleting a single contact
    public void deleteContact(Contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID +"=?", new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
    //Deleting a single user
    public void deleteUser(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_USER +"=?", new String[] { String.valueOf(user.getUSER()) });
        db.close();
    }
}
