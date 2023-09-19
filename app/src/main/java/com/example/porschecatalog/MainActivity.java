package com.example.porschecatalog;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {
    private ListView porscheListView;
//    private ArrayAdapter<ImageView> adapter;
    private ArrayList<String> porsheList;
    private ArrayList<String> images;
    private ArrayList<String> AllInfoPorsche;
    PorscheAdapter porscheAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new InsecureTrustManager()}, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        porscheListView = findViewById(R.id.porschelist);
        porscheListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,com.example.porschecatalog.AllInfoPorsche.class);
                i.putExtra("FullInfo",AllInfoPorsche.get(position));
                MainActivity.this.startActivity(i);
            }
        });
        porsheList = new ArrayList<>();
        images= new ArrayList<>();
        AllInfoPorsche = new ArrayList<>();
        porscheAdapter = new PorscheAdapter(this,porsheList,images);
        porscheListView.setAdapter(porscheAdapter);

        new HttpGetRequest().execute("https://10.0.2.2:7067/porsche/GetPorsche","GET");
    }
    private class HttpGetRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String apiUrl = params[0];

            try {
                URL url = new URL(apiUrl);
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if(params[1] == "GET"){
                    connection.setRequestMethod("GET");
                }
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();
                    return response.toString();
                } else {
                    Log.e("HTTP GET Request", "HTTP Response Code: " + responseCode);
                    return null;
                }
            } catch (IOException e) {
                Log.e("HTTP GET Request", "IOException: " + e.getMessage());
                return null;
            }
        }
        @SuppressLint("WrongThread")
        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    porsheList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String model = jsonObject.getString("model");
                        String year = jsonObject.getString("years");
                        String url = jsonObject.getString("image");
                        String desc = jsonObject.getString("description");

                        porsheList.add(model);
                        images.add(url);
                        AllInfoPorsche.add(model + "%" + year + "%" + desc + "%" + url);
                    }
                    porscheAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Произошла ошибка при получении заметок.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static class InsecureTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}