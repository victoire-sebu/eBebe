package com.mysebu.ebebe;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.mysebu.ebebe.Utils.ListDeclarerationClass;
import com.mysebu.ebebe.Utils.Outils_class;
import com.mysebu.ebebe.ui.home.HomeFragment;
import com.mysebu.ebebe.ui.listdeclaration.ListdeclarationFragment;
import com.mysebu.ebebe.ui.naissance.NaissanceFragment;
import com.mysebu.ebebe.ui.send.SendFragment;
import com.mysebu.ebebe.ui.share.ShareFragment;
import com.mysebu.ebebe.ui.tools.ToolsFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    MenuItem menuItem;
    String username;
    TextView emailinfo;

    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        menuItem=(MenuItem) findViewById(R.id.action_settings);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Fragment fragmentN=new ShareFragment();
                show(fragmentN);

                return false;
            }
        });

 */


        username=getIntent().getStringExtra("myemail");
        emailinfo=(TextView) findViewById(R.id.textViewS);
       // emailinfo.setText(username);
        //Toast.makeText(MainActivity.this,username,Toast.LENGTH_LONG).show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragmentN=new NaissanceFragment();
                show(fragmentN);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

/*hide for test
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_naissance, R.id.nav_listdeclaration,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
*/

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem=menu.findItem(R.id.action_apropos);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Fragment fragment=new ToolsFragment();
                show(fragment);


                return false;
            }
        });

        return true;
    }

    /*hid for test
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
*/
//new methods
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();

        if(id==R.id.nav_home){
            Fragment homeFragment=new HomeFragment();
            show(homeFragment);
        }else if(id==R.id.nav_naissance){
            Fragment naissanceFragment=new NaissanceFragment();
            show(naissanceFragment);
        }else if(id==R.id.nav_listdeclaration){
            Fragment listdeclarationFragment=new ListdeclarationFragment();
            show(listdeclarationFragment);
        }else if(id==R.id.nav_tools){

            Fragment toolsFragment=new ToolsFragment();
            show(toolsFragment);
        }else if(id==R.id.nav_share){
           // Fragment shareFragment=new ShareFragment();
            //show(shareFragment);
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            //this link may be changed by the link app when it will be in production
            String shareBody="Téléchargé cette application:-https://developer.android.com/guide/navigation/navigation-migrate#java";
            String sharesub="eBebe app";

            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);

            startActivity(Intent.createChooser(shareIntent,"Partager via"));

          //  Toast.makeText(MainActivity.this,"sevic",Toast.LENGTH_LONG).show();
        }else if(id==R.id.nav_send){
            Fragment sendFragment=new SendFragment();
            show(sendFragment);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void show(Fragment fragment) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();

        drawerLayout.closeDrawer(GravityCompat.START);
    }

}
