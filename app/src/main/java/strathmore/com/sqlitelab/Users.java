package strathmore.com.sqlitelab;

/**
 * Created by Wilson on 01/11/2017.
 */

class Users {
    int _user_id;
    String _first_name;
    String _last_name;
    String _residence;
    //Constructor1
    public Users(int user,String fname,String lname,String residence){
        this._user_id= user;
        this._first_name = fname;
        this._last_name =lname;
        this._residence = residence;


    }
    //constructor2
    public Users(String f_name,String l_name,String res){
        this._first_name = f_name;
        this._last_name = l_name;
        this._residence=res;
    }

    public Users() {

    }

    //set methods
    public void setUSER(int user){this._user_id = user;}
    public void setFirstName(String fname){this._first_name = fname;}
    public void setLastName(String lname){this._last_name = lname;}
    public void setResidence (String residence){this._residence = residence;}

    //getMethods
    public int getUSER(){return this._user_id;}
    public String getFirstName(){return this._first_name;}
    public String getLastName(){return this._last_name;}
    public String getResidence(){return this._residence;}




}
