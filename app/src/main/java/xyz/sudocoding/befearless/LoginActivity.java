package xyz.sudocoding.befearless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private boolean isLoggedIn;

    //-------------------------------- DEFAULTS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        __init__();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        // Kill activity if logged in
        if(isLoggedIn) finish();
    }

    //-------------------------------- PRIVATES
    /*
    * Method to load up initial setup
    * */
    private void __init__(){
        final EditText phoneET = (EditText) findViewById(R.id.phoneET);
        final EditText passwordET = (EditText) findViewById(R.id.passET);

        Button loginBTN = (Button) findViewById(R.id.loginBTN);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneText = phoneET.getText().toString();
                String passText = passwordET.getText().toString();
                if(phoneText!=null && !phoneText.equals("") && passText!=null && !passText.equals("")){
                    attemptLogin(phoneText, passText);
                } else {
                    Toast.makeText(LoginActivity.this, "Phone or password field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    * Method to attempt login operation
    * */
    private boolean attemptLogin(String phoneText, String passText){
        return false;
    }
}
