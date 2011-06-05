package org.rhok.foodmover;

import android.os.AsyncTask;

public class NWTasks extends AsyncTask<Object, Void, Object> {
									//<Params, Progress, Result>
	
	public static final int GET_PRODUCER = 9;
	public static final int POST_PRODUCER = 99;
	public static final int GET_CONSUMER = 999;
	public static final int POST_CONSUMER = 9999;
	
	@Override
	protected Object doInBackground(Object... params) {
		// This class will run network tasks on a background thread
		createConnection();
		final int function = (Integer) params[0];

		return null;
	}
	
	void createConnection() {
	
	        

	}
}
