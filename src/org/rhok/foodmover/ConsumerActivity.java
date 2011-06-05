package org.rhok.foodmover;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ConsumerActivity extends Activity {
	public static final String TAG = "ConsumerActivity";
	public static final String ACCOUNT_KEY = "acctkey";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer);
    }
    
    @Override
	protected void onResume() {
		// This method will display the correct components on the screen
		super.onResume();
        /*if((PreferenceManager.getDefaultSharedPreferences(this)).contains(ACCOUNT_KEY)) {
        	transition2Main();
    		return;
        } */
	}

    void transition2Main() {
		((LinearLayout)findViewById(R.id.c_loginSetup)).setVisibility(View.GONE);
		((LinearLayout)findViewById(R.id.c_mainButtons)).setVisibility(View.VISIBLE);
    }
    
    public void fakeAccount(View target) {
    	//this method is defined as a button listener using the onClick tag in the xml layout
		final SharedPreferences.Editor ed = (PreferenceManager.getDefaultSharedPreferences(this)).edit();
		ed.putString(ACCOUNT_KEY, ((EditText)findViewById(R.id.c_username)).getText().toString());
		ed.commit();
		transition2Main();
    }
}
