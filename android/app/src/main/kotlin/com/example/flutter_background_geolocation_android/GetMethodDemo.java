package com.example.flutter_background_geolocation_android;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;

public class GetMethodDemo extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        System.out.println("GetMethodDemo");
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
            urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();

            if(code==200){
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                if (in != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                in.close();
            }
            System.out.println("Resultado: "+result);
            return result;
        } catch (MalformedURLException e) {
            System.out.println("Post error:"+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Post error IO:"+e.getMessage());
            e.printStackTrace();
        }

        finally {
            urlConnection.disconnect();
        }
        return result;

    }

    @Override
        protected void onPostExecute(String result) {
            //mHttpString = result;
            super.onPostExecute(result);
        }
}