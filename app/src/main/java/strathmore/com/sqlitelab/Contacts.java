package strathmore.com.sqlitelab;

/**
 * Created by Wilson on 17/10/2017.
 */

public class Contacts {

    int _id;
    String _name;
    String _phone_number;
    //Empty Constructor
    public Contacts(){

    }//Constructor

    public Contacts (int _id,String _name,String _phone_number){
        this._id =_id;
        this._name=_name;
        this._phone_number= _phone_number;

    }//Constructor
    public Contacts(String name, String _phone_number){
        this._name =name;
        this._phone_number = _phone_number;
    }
    //getting
    public int getID() {
        return this._id;
    }
    //setting id
    public void setID(int id){
        this._id = id;
    }//getting name
    public String getName(){
        return this._name;
    }//set name
    public void setName(String name){
        this._name = name;
    }//getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }//setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}
