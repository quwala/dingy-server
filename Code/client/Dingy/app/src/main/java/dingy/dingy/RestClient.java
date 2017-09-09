package dingy.dingy;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RestClient extends AsyncTask<String, Void, Void> {




    @Override
    protected Void doInBackground(String... strings) {
        try

        {


            URL restServiceURL = new URL("http://10.0.2.2:8080/dingyserver/webapi" + strings[0]);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "text/plain");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));

            String output;
            String response="";

            while ((output = responseBuffer.readLine()) != null) {
                response+=output;
            }

            httpConnection.disconnect();

        } catch (
                MalformedURLException e)

        {

            e.printStackTrace();

        }  catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}