package com.shareapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
//import com.google.analytics.tracking.android.EasyTracker;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class MainActivity extends Activity {

	Button shareButton;
	Tracker t;
	//EasyTracker easyTracker = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
        Tracker t = ((WhatsappShare) getApplication()).getTracker(WhatsappShare.TrackerName.APP_TRACKER);
        t.setScreenName("MainActivity");
        t.send(new HitBuilders.ScreenViewBuilder().build());
        t.send(new HitBuilders.EventBuilder()
        .setCategory("Button")
        .setAction("Click")
        .setLabel("share Button")
        .build());
        }
        catch(Exception  e)
        {
        Toast.makeText(getApplicationContext(), "Error"+e.getMessage(), 1).show();
        }
        shareButton= (Button) findViewById(R.id.button1);
        shareButton.setOnClickListener(new OnClickListener(){   	

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shareit();
				
			}
		});
        
    }

    protected void shareit() {
		// TODO Auto-generated method stub
    	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
    	sharingIntent.setPackage("com.whatsapp");
       sharingIntent.setType("text/plain");
    	
    	String shareBody = "Get Vizzicall-I just installed vizzicall on my android. http://vizzical.com";
    	sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Vizzical-");
    	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
    	 // for sharing through all apps 	 	startActivity(Intent.createChooser(sharingIntent, "Share via"));
    	
    	// for only through whatsapp 
    	startActivity(sharingIntent);
		
	}
 
    @Override
	public void onStart() {
		super.onStart();
		GoogleAnalytics.getInstance(this).reportActivityStart(this);
	
    }

	@Override
	public void onStop() {
		super.onStop();
		//Stop the analytics tracking
		GoogleAnalytics.getInstance(this).reportActivityStop(this);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
