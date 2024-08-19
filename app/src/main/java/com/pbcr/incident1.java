package com.pbcr;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Intent;
import android.net.http.UrlRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class incident1 extends AppCompatActivity {
    Spinner spinner;
    final String[] status = {""};

    final String TAG = "incident1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident1);

        spinner = (Spinner) findViewById(R.id.spn1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(incident1.this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final List<String> deptList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/exm.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    Log.d(TAG, "Response Code: " + responseCode);

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder resultBuilder = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            resultBuilder.append(line);
                        }

                        reader.close();
                        result = resultBuilder.toString();
                    } else {
                        Log.e(TAG, "Server returned non-OK response code: " + responseCode);
                    }
                    connection.disconnect();

                    if (result != null) {
                        JSONObject jsonResponse = new JSONObject(result);
                        status[0] = jsonResponse.getString("status");
                        JSONArray data = jsonResponse.getJSONArray("data");
                        deptList.add("Select department");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String dept = row.getString("Dept");
                            deptList.add(dept);
                        }

                        Log.d(TAG, "Data fetched: " + deptList);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.clear();
                                adapter.addAll(deptList);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error: " + e.getMessage());
                }
            }
        }).start();

        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.d(TAG, String.valueOf(deptList));
                Log.d(TAG, value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "nothing selected");
            }
        });

        Button btnback = findViewById(R.id.btnback3);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident1.this, homepage.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnnext = findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident1.this, incident2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}