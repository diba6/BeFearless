package xyz.sudocoding.befearless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import xyz.sudocoding.befearless.handlers.AuthenticationHandler;
import xyz.sudocoding.befearless.listeners.AuthenticationListener;

public class LoginActivity extends AppCompatActivity implements AuthenticationListener {

    private boolean isLoggedIn;
    private AuthenticationHandler authHandler;

    private final EditText emailET = (EditText) findViewById(R.id.emailET);
    private final EditText passwordET = (EditText) findViewById(R.id.passET);
    private final ProgressBar pb = (ProgressBar) findViewById(R.id.loginProgressBar);
    private final Button loginBTN = (Button) findViewById(R.id.loginBTN);
    private final Button registerBtn = (Button) findViewById(R.id.registerBTN);

    //-------------------------------- DEFAULTS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        __init__();
    }

    @Override
    protected void onStart(){
        super.onStart();
        // Start firebase auth listener
        authHandler.startAuthListener();
    }

    @Override
    protected void onPause(){
        super.onPause();
        // Kill activity if logged in
        if(isLoggedIn) finish();
    }

    @Override
    protected void onStop(){
        super.onStop();
        // Stop firebase auth listener
        authHandler.stopAuthListener();
    }

    //-------------------------------- PUBLIC
    /*
    * AuthenticationListener onLogin success/failed method
    * @param: isSuccessful -> boolean
    * @return: isSuccessful -> boolean
    * */
    @Override
    public boolean logIn(boolean isSuccessful){
        isLoggedIn = isSuccessful;
        if(isLoggedIn) nextActivity(); else notifyUser("Incorrect email or password");
        userInteract(true);
        return isSuccessful;
    }

    /*
    * AuthenticationListener onSignUp success/failed method
    * @param: isSuccessful -> boolean
    * @return: isSuccessful -> boolean
    * */
    @Override
    public boolean signedUp(boolean isSuccessful) {
        if(isSuccessful) notifyUser("Successfully signed up"); else notifyUser("Something went wrong");
        userInteract(true);
        return isSuccessful;
    }

    /*
    * AuthenticationListener error method
    * @return: boolean
    * */
    @Override
    public boolean error() {
        notifyUser("Something went wrong");
        userInteract(true);
        return true;
    }

    //-------------------------------- PRIVATE
    /*
    * Method to load up initial setup
    * */
    private void __init__(){
        authHandler = new AuthenticationHandler(this);

        pb.setVisibility(View.GONE);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = emailET.getText().toString();
                String passText = passwordET.getText().toString();
                if(emailText!=null && !emailText.equals("") && passText!=null && !passText.equals("")){
                    userInteract(false);
                    attemptLogin(emailText, passText);
                } else {
                    Toast.makeText(LoginActivity.this, "Email or password field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = emailET.getText().toString();
                String passText = passwordET.getText().toString();
                if(emailText!=null && !emailText.equals("") && passText!=null && !passText.equals("")){
                    userInteract(false);
                    attemptSignUp(emailText, passText);
                } else {
                    Toast.makeText(LoginActivity.this, "Email or password field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    * Method to handler user interaction components
    * @param: isActive -> boolean
    * */
    private void userInteract(boolean isActive){
        emailET.setFocusable(isActive);
        passwordET.setFocusable(isActive);
        loginBTN.setVisibility(isActive ? View.VISIBLE : View.GONE);
        registerBtn.setVisibility(isActive ? View.VISIBLE : View.GONE);
        pb.setVisibility(isActive ? View.GONE : View.VISIBLE);
    }

    /*
    * Method to attempt login operation
    * @param: emailText -> String
    * @param: passText -> String
    * */
    private void attemptLogin(String emailText, String passText){
        authHandler.signIn(emailText, passText);
    }

    /*
    * Method to attempt login operation
    * @param: emailText -> String
    * @param: passText -> String
    * */
    private void attemptSignUp(String emailText, String passText){
        authHandler.signUp(emailText, passText);
    }

    /*
    * Method to move to the next activity
    * */
    private void nextActivity(){
        // TODO: Add the intent of the next activity
        Intent nextAct = new Intent(this, MainActivity.class);
        startActivity(nextAct);
    }

    /*
    * Method to notify user with a toast
    * @param: msg -> String
    * */
    private void notifyUser(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
