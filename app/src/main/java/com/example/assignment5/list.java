package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.ArrayList;

public class list extends AppCompatActivity {


    private ArrayList<Pier> PierArrayList=new ArrayList<>();
    private ArrayAdapter<Pier> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter=new ArrayAdapter<Pier>(this,R.layout.textviewextra,R.id.Actualtextviewextra,PierArrayList);
        ListView PierList=findViewById(R.id.PierListView);
        PierList.setAdapter(adapter);
        PierList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), PierArrayList.get(position).info(), Toast.LENGTH_LONG).show();

            }
        });



        Button button = findViewById(R.id.Getinfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=c20adael");


            }
        });

    }


    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                PierArrayList.clear();
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String name = jsonObject.getString("name");
                    String location = jsonObject.getString("location");
                    int Length = jsonObject.getInt("size");
                    int cost = jsonObject.getInt("cost");

                    JSONObject auxdata = jsonObject.getJSONObject("auxdata");
                    String licence = auxdata.getString("licence");
                    String img = auxdata.getString("img");

                    Pier pier = new Pier(name, location, Length, cost, licence, img);


                    PierArrayList.add(pier);
                }
                adapter.notifyDataSetChanged();

            }

            catch (JSONException e) {
                Log.d("JSON", "Could not parse: " + json + "\n due to expectation:" + e);
            }
        }
    }
}