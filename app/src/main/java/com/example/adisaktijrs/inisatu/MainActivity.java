package com.example.adisaktijrs.inisatu;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Button cari;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;


    List<dataModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);
        cari = (Button)findViewById(R.id.caridisini);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = new ArrayList<dataModel>();
                // String url = "https://owlbot.info/api/v2/dictionary/owl?format=json";

                String kata = null;
                kata = input.getText().toString().trim();
                if (kata.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Teks tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else {
                    String url = "https://owlbot.info/api/v2/dictionary/"+kata+"?format=json";
                    new requestData().execute(url);
                    mAdapter = new customAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(mAdapter);
                }

            }
        });

    }

    public class requestData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            String returnValue = null;
            StringBuilder sb = null;
            URL obj = null;

            try {
                obj = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent","Mozila/5.0");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine = "";
                sb = new StringBuilder();
                while ((inputLine=in.readLine())!=null){
                    sb.append(inputLine);
                }
                in.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String x,y,z;
            try {
                JSONArray arr = new JSONArray(s);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject ob = (JSONObject) arr.get(i);
                    x = ob.getString("type");
                    y = ob.getString("definition");
                    z = ob.getString("example");

                    list.add(new dataModel(x,y,z));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
