package xyz.sudocoding.befearless.handlers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

import xyz.sudocoding.befearless.listeners.AuthenticationListener;

/**
 * Firebase authentication handler class
 */

public class AuthenticationHandler {
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthListener;

    private AuthenticationListener activity;

    public AuthenticationHandler(final AuthenticationListener activity){
        this.activity = activity;
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    activity.logIn(true);
                } else {
                    activity.logIn(false);
                }
            }
        };
    }

    public void startAuthListener(){
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void stopAuthListener(){
        mAuth.removeAuthStateListener(mAuthListener);
    }

    public void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {activity.signedUp(task.isSuccessful());    }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                activity.error();
            }
        });
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                activity.logIn(task.isSuccessful());
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                activity.error();
            }
        });
    }

    public Map<String, String> getUserInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final Map<String, String> userData = new HashMap<String, String>();
        if(user != null){
            userData.put("name", user.getDisplayName());
            userData.put("email", user.getEmail());
            userData.put("photoURL", user.getPhotoUrl().toString());
            userData.put("UID", user.getUid());
        }
        return userData;
    }
}
