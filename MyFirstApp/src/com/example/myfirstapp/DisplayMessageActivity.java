package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class DisplayMessageActivity extends Activity implements TabListener {

	List<Fragment> fragList = new ArrayList<Fragment>();
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		// Show the Up button in the action bar.
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
            
            ActionBar bar = getActionBar();
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            
            for (int i=1; i <= 3; i++) {
	            Tab tab = bar.newTab();
	            tab.setText("Tab " + i);
	            tab.setTabListener(this);
	            bar.addTab(tab);
            }
        }
        else {
        	//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        
        
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
     
        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // Set the text view as the activity layout
        setContentView(textView);
    }
	

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
		
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_search:
            openSearch();
            return true;
        case R.id.action_settings:
            openSettings();
            return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
		getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
	}
    
    private void openSearch() {
        Toast.makeText(this, "Search button pressed", Toast.LENGTH_SHORT).show();
    }
    
    private void openSettings() {
        Toast.makeText(this, "Settings button pressed", Toast.LENGTH_SHORT).show();
    }
	
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Fragment f = null;
        TabFragment tf = null;

        if (fragList.size() > tab.getPosition())
                fragList.get(tab.getPosition());

        if (f == null) {
            tf = new TabFragment();
            Bundle data = new Bundle();
            data.putInt("idx",  tab.getPosition());
            tf.setArguments(data);
            fragList.add(tf);
        }
        else
            tf = (TabFragment) f;

        ft.replace(android.R.id.content, tf);

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        if (fragList.size() > tab.getPosition()) {
            ft.remove(fragList.get(tab.getPosition()));
        }

    }


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
}
