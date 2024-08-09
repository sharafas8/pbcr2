package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] status = {"esc"};
        Log.d(TAG, "working");

        try {
            URL url = new URL("http://10.0.2.2/exm.php"); // Use 10.0.2.2 for local server access on emulator
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            Log.d(TAG, "Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();
            connection.disconnect();

            JSONObject jsonResponse = new JSONObject(result.toString());
            status[0] = jsonResponse.getString("status");
            String message = jsonResponse.getString("message");

            // Log the raw JSON response
            Log.d(TAG, "Raw JSON response: " + message);

        } catch (Exception e) {

            e.printStackTrace();
            Log.e(TAG, "Error: " + e.getMessage());}

        EditText username =(EditText) findViewById(R.id.username);
        EditText password =(EditText) findViewById(R.id.password);
        Button sign =(Button) findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    //correct
                    Intent intent = new Intent(MainActivity.this, homepage.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();


                }else
                    //incorrect
                    Toast.makeText(MainActivity.this,"LOGIN FAILED",Toast.LENGTH_SHORT).show();




            }
        });

    }
}