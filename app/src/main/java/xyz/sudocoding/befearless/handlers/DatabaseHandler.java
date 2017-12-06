package xyz.sudocoding.befearless.handlers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import xyz.sudocoding.befearless.listeners.DatabaseListener;

/**
 * Created by HackRider on 02-12-2017.
 */

public class DatabaseHandler {

    private final FirebaseDatabase fdb = FirebaseDatabase.getInstance();

    private DatabaseReference rootDbRef, childDbRef;
    private String userRef;

    private DatabaseListener callingClass;

    //-------------------------------- DEFAULT
    /*
    * Constructor
    * */
    public DatabaseHandler(DatabaseListener activity){
        callingClass = activity;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userRef = "/users/"+user.getUid()+"/";
        rootDbRef = fdb.getReference(userRef);
    }

    //-------------------------------- PUBLIC
    /*
    * Method to set the reference of the database
    * @param: path = string (The node path in database)
    * @return: this
    * */
    public DatabaseHandler setDbRef(String path){
        childDbRef = rootDbRef.child(path);
        return this;
    }


    /*
    * Method to upload to the database
    * */
    public void addData(Map<String, String> dataPacket){
        try{
            // TODO finish later
        } catch (Exception e) {

        }
    }
}
