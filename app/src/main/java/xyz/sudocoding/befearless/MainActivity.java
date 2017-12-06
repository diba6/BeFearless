package xyz.sudocoding.befearless;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import xyz.sudocoding.befearless.fragments.ContactFragment;
import xyz.sudocoding.befearless.fragments.HomeFragment;
import xyz.sudocoding.befearless.fragments.MapsActivity;
import xyz.sudocoding.befearless.fragments.ProfileFragment;
import xyz.sudocoding.befearless.handlers.AuthenticationHandler;
import xyz.sudocoding.befearless.listeners.AuthenticationListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AuthenticationListener{

    private AuthenticationHandler authHandler;

    private boolean isSignedOut;

    //------------------------------------ DEFAULT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("BeFearless");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toast.makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show();

        //authHandler = new AuthenticationHandler(this);
        isSignedOut = false;

        setFragment(new HomeFragment());

        __init__();
    }

    @Override
    public void onStart(){
        super.onStart();
        //authHandler.startAuthListener();
    }

    @Override
    public void onPause(){
        super.onPause();
        if(isSignedOut)
            finish();
    }

    @Override
    public void onStop(){
        super.onStop();
        //authHandler.stopAuthListener();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            nextAct(SettingsActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setFragment(new HomeFragment());
        } else if (id == R.id.nav_map) {
            nextAct(MapsActivity.class);
        } else if (id == R.id.nav_contact) {
            setFragment(new ContactFragment());
        } else if (id == R.id.nav_profile) {
            setFragment(new ProfileFragment());
        } else if (id == R.id.nav_sign_out) {
            //authHandler.signOut();
            isSignedOut = true;
            nextAct(MainActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean signedUp(boolean isSuccessful){
        return true;
    }

    @Override
    public boolean logIn(boolean isSuccessful){
        return true;
    }

    @Override
    public boolean error(){
        return true;
    }

    //------------------------------------ PUBLIC

    //------------------------------------ PRIVATE
    /*
    * Method to initialize activity components
    * */
    private void __init__(){
        // Load default fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout, new HomeFragment());
    }

    /*
    * Method to set fragments
    * @param: fragment -> Fragment
    * */
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /*
    * Method to open next activity
    * param: nextAct -> Class
    * */
    private void nextAct(Class nextAct){
        Intent next = new Intent(this, nextAct);
        startActivity(next);
    }
}
