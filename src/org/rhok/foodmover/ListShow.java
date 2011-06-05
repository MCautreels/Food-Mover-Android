package org.rhok.foodmover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.ContentValues;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class ListShow extends ListActivity {
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create an array of Strings, that will be put to our ListActivity
        String[] names = new String[] { "Sandwiches    info@rhok.org", "Pasta        merko@gmail.com", "Pizza         getpizza@gmail.com", "Chinese    zacks@yahoo.com",
                "Cookies     givea@gmail.com"};
        // Create an ArrayAdapter, that will actually make the Strings above
        // appear in the ListView
        
        // Todo uncomment these 2 lines
        this.setListAdapter(new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1, names));
   
       
		/*Toast.makeText(getApplicationContext(),
				"list activity ",
				Toast.LENGTH_SHORT).show();
		
        final ContentValues initialValues = new ContentValues();
		String[] cret = null;
		String[] descarr = null;
		
        initialValues.put("url", "http://foodmovr.appspot.com/api/v1/listings?lat=37.3861111&lng=-122.0827778");
		
		try {
			cret = (String[]) new NWTasks().execute(NWTasks.GET_LISTING,initialValues).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cret));
		
		for (int i = 0; i < cret.length; i++) {					

		System.out.print("Description " + cret[i]);
		}
*/        
    }
   
    /**
     * @return
     * @throws Exception
     */
    public static String[] getListings() throws Exception {
        String request = "http://foodmovr.appspot.com/api/v1/listings?lat=37&lng=-122";

        String[] values = null;
       
        HttpClient httpclient = new DefaultHttpClient();

        // Prepare a request object
        HttpGet httpget = new HttpGet(request);

        // Execute the request
        HttpResponse response = httpclient.execute(httpget);

        // Examine the response status
        System.out.println("Login form get: " + response.getStatusLine());

        // Get hold of the response entity
        HttpEntity entity = response.getEntity();

        // If the response does not enclose an entity, there is no need
        // to worry about connection release
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {

                // Process the response from Yahoo! Web Services
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(instream));
                String jsonString = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString += line;
                }
                reader.close();

                // A JSONArray is an ordered sequence of values. Its external form is
                // a string wrapped in square brackets with commas between the values.
                JSONArray ja = new JSONArray(jsonString);
                 
                // Get the number of search results in this set
                int resultCount = ja.length();
                values = new String[resultCount];

                // Loop over each result and print the title, summary, and URL
                for (int i = 0; i < resultCount; i++) {
                    JSONObject resultObject = ja.getJSONObject(i);
                    values[i] = (String) resultObject.get("description");
                   
                    /*System.out.println(resultObject.get("description"));
                    System.out.println(resultObject.get("quantity"));
                    System.out.println(resultObject.get("lng"));
                    System.out.println(resultObject.get("lat"));
                    System.out.println("--");*/
                }

            } catch (IOException ex) {

                // In case of an IOException the connection will be released
                // back to the connection manager automatically
                throw ex;

            } catch (RuntimeException ex) {

                // In case of an unexpected exception you may want to abort
                // the HTTP request in order to shut down the underlying
                // connection and release it back to the connection manager.
                httpget.abort();
                throw ex;

            } finally {

                // Closing the input stream will trigger connection release
                instream.close();

            }

            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
                       
        } // End if
        return values;

    }
}