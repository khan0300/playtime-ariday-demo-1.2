package com.algonquincollege.ari.playtimearidaydemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.algonquincollege.ari.playtimearidaydemo.data.DemoModel;

public class MainActivity extends AppCompatActivity {

    // Main Toolbar (top)
    private Toolbar toolbar;

    /*
        Android still does not support a global font manager. Sad. :'(
     */
    public Typeface FONT_HOUSE_SLANT;
    public Typeface FONT_ROBOTO_BOLD_ITALIC;
    public Typeface FONT_ROBOTO_ITALIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FONT_HOUSE_SLANT = Typeface.createFromAsset(getAssets(), "fonts/HouseSlant-Regular.otf");
        FONT_ROBOTO_BOLD_ITALIC = Typeface.createFromAsset(getAssets(), "fonts/Roboto-BoldItalic.ttf");
        FONT_ROBOTO_ITALIC = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Italic.ttf");

        // Initialize the Demo's data model and capture reference to its singleton instance
        DemoModel model = DemoModel.getInstance().init(this);
        setContentView(R.layout.activity_main);

        /// Toolbar (top)
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        TextView usernameTextView = (TextView) findViewById(R.id.main_username_text_view);
        usernameTextView.setText(model.getUsers().get(0).getAccount().getUsername().toUpperCase());
        usernameTextView.setTypeface(FONT_HOUSE_SLANT);

        /////////////////////
        // being FRAGMENTS //
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;

        /// NavigationBar Fragment
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment navBarFragment = new NavigationBar();
        fragmentTransaction.add(R.id.nav_bar_container, navBarFragment, "fragment_NAV_BAR");
        fragmentTransaction.commit();

        /// Home Page Fragment
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment homePageFragment = new HomePage();
        fragmentTransaction.add(R.id.home_page_container, homePageFragment, "fragment_HOME_PAGE");
        fragmentTransaction.commit();

    }//end onCreate

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

        switch (id) {
            case R.id.main_options_menu_item_play_tutorial:
            case R.id.main_options_menu_item_settings:
            case R.id.main_options_menu_item_send_feedback:
            case R.id.main_options_menu_item_help:
                toastOptionsMenuItem(item.getTitle().toString());
                return true;
            case R.id.main_options_menu_demo_restart_application:
                demoRestartApplication(this);
        }

        return super.onOptionsItemSelected(item);
    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void toastOptionsMenuItem(String optionsMenuItemTitle) {
        toast(String.format("[options menu] %s", optionsMenuItemTitle));
    }

    public void toastNavButtons(String navPageTitle) {
        toast(String.format("[navigate to] %s page", navPageTitle));
    }

    private void demoRestartApplication(Activity context) {
        Intent mainActivity = new Intent(context, MainActivity.class);
        int pendingIntentId = 123456;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, pendingIntentId, mainActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);
        System.exit(0);
    }

}//end class MainActivity
