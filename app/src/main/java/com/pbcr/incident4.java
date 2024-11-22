package com.pbcr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class incident4 extends AppCompatActivity {

    Spinner spinnerDiet, spinnerSmoking, spinnerAlcohol, spinnerChewing, spinnerSnuff, spinnerDrugs;
    RadioGroup radioGroupPassive;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11;
    Button btnNext;
    EditText duration1,duration2,duration3,duration4,duration5;

    String[] Diet = {"Select Item", "Vegetarian", "Non-Vegetarian", "Eggetarian", "Unknown"};
    String[] Smoking = {"Select Item", "Occasional", "Daily", "Chronic", "Ex.", "No", "Unknown"};
    String[] Alcohol = {"Select Item", "Occasional", "Daily", "Chronic", "Ex.", "No", "Unknown"};
    String[] Chewing = {"Select Item", "Occasional", "Daily", "Chronic", "Ex.", "No", "Unknown"};
    String[] Snuff = {"Select Item", "Occasional", "Daily", "Chronic", "Ex.", "No", "Unknown"};
    String[] Drugs = {"Select Item", "Occasional", "Daily", "Chronic", "Ex.", "No", "Unknown"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident4);

        // Initialize the views
        duration1 = findViewById(R.id.d1);
        duration2 = findViewById(R.id.d2);
        duration3 = findViewById(R.id.d3);
        duration4 = findViewById(R.id.d4);
        duration5 = findViewById(R.id.d5);

        spinnerDiet = findViewById(R.id.spndiet);
        spinnerSmoking = findViewById(R.id.spnsmoke);
        spinnerAlcohol = findViewById(R.id.spnalc);
        spinnerChewing = findViewById(R.id.spnchew);
        spinnerSnuff = findViewById(R.id.spnsnuff);
        spinnerDrugs = findViewById(R.id.spndrug);
        radioGroupPassive = findViewById(R.id.passive);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        checkBox10 = findViewById(R.id.checkBox10);
        checkBox11 = findViewById(R.id.checkBox11);

        // Setup Spinner adapters
        setSpinnerAdapter(spinnerDiet, Diet);
        setSpinnerAdapter(spinnerSmoking, Smoking);
        setSpinnerAdapter(spinnerAlcohol, Alcohol);
        setSpinnerAdapter(spinnerChewing, Chewing);
        setSpinnerAdapter(spinnerSnuff, Snuff);
        setSpinnerAdapter(spinnerDrugs, Drugs);

        Button btnBack3 = findViewById(R.id.btnback3);
        btnBack3.setOnClickListener(v -> {
            Intent intent = new Intent(incident4.this, incident3.class);
            startActivity(intent);
            finish();
        });

        // Next button click listener
        btnNext = findViewById(R.id.btnnext3);
        btnNext.setOnClickListener(v -> {
            saveDataToServer();
            Intent intent = new Intent(incident4.this, inc.class);
            startActivity(intent);
            finish();
        });
    }

    // Helper function to set Spinner adapters
    private void setSpinnerAdapter(Spinner spinner, String[] data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    // Method to collect data and send to server
    private void saveDataToServer() {
        try {
            JSONObject jsonObject = new JSONObject();

            // Map spinner values to their respective IDs and values
            jsonObject.put("diet", createLifestyleObject(1, getSpinnerValue(spinnerDiet)));
            jsonObject.put("smoking", createLifestyleObject(2, getLifestyleValue(spinnerSmoking)));
            jsonObject.put("alcohol", createLifestyleObject(3, getLifestyleValue(spinnerAlcohol)));
            jsonObject.put("chewing", createLifestyleObject(4, getLifestyleValue(spinnerChewing)));
            jsonObject.put("snuff", createLifestyleObject(5, getLifestyleValue(spinnerSnuff)));
            jsonObject.put("drugs", createLifestyleObject(6, getLifestyleValue(spinnerDrugs)));

            // Get selected radio group value
            int selectedRadioId = radioGroupPassive.getCheckedRadioButtonId();
            if (selectedRadioId != -1) {
                jsonObject.put("passive", createLifestyleObject(8, getPassiveValue(selectedRadioId)));
            }

            // Collect checked checkbox values
            JSONArray checkBoxValues = new JSONArray();
            collectCheckBoxValues(checkBoxValues);
            jsonObject.put("checkboxes", checkBoxValues);

            // Collect lifestyle duration data only from specific spinners and map them
            JSONArray durations = new JSONArray();
            durations.put(duration1.getText().toString());
            durations.put(duration2.getText().toString());
            durations.put(duration3.getText().toString());
            durations.put(duration4.getText().toString());
            durations.put(duration5.getText().toString());
            jsonObject.put("lifestyle_duration", durations);

            // Send data to server
            new InsertDataTask().execute(jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating JSON data", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to create lifestyle object with ID and value
    private JSONObject createLifestyleObject(int lifestyleNameId, int lifestyleValueId) throws JSONException {
        JSONObject lifestyleObject = new JSONObject();
        lifestyleObject.put("lifestyle_name_id", lifestyleNameId);
        lifestyleObject.put("lifestyle_value_id", lifestyleValueId);


        return lifestyleObject;
    }


    // Helper method to map spinner selections to IDs
    private int getSpinnerValue(Spinner spinner) {
        switch (spinner.getSelectedItemPosition()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 19;
            case 4:
                return 21;
            default:
                return 0;
        }
    }

    // Helper method to map spinner selections to their respective values
    private int getLifestyleValue(Spinner spinner1) {
        switch (spinner1.getSelectedItemPosition()) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 6;
            case 5:
                return 7;
            case 6:
                return 8;
            default:
                return 0;
        }
    }

    // Helper method to map RadioGroup selections to values
    private int getPassiveValue(int radioId) {
        if (radioId == R.id.radioButton1) {
            return 24;
        } else if (radioId == R.id.radioButton2) {
            return 22;
        } else if (radioId == R.id.radioButton3) {
            return 23;
        } else {
            return 0; // Ensure this is handled properly
        }
    }

    // Helper method to collect checked CheckBox values
    private void collectCheckBoxValues(JSONArray checkBoxValues) throws JSONException {
        if (checkBox1.isChecked()) checkBoxValues.put(createLifestyleObject(7, 9));
        if (checkBox2.isChecked()) checkBoxValues.put(createLifestyleObject(7, 10));
        if (checkBox3.isChecked()) checkBoxValues.put(createLifestyleObject(7, 11));
        if (checkBox4.isChecked()) checkBoxValues.put(createLifestyleObject(7, 12));
        if (checkBox5.isChecked()) checkBoxValues.put(createLifestyleObject(7, 13));
        if (checkBox6.isChecked()) checkBoxValues.put(createLifestyleObject(7, 14));
        if (checkBox7.isChecked()) checkBoxValues.put(createLifestyleObject(7, 15));
        if (checkBox8.isChecked()) checkBoxValues.put(createLifestyleObject(7, 16));
        if (checkBox9.isChecked()) checkBoxValues.put(createLifestyleObject(7, 17));
        if (checkBox10.isChecked()) checkBoxValues.put(createLifestyleObject(7, 18));
        if (checkBox11.isChecked()) checkBoxValues.put(createLifestyleObject(7, 25));
    }

    // AsyncTask to insert data into the server
    private class InsertDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String jsonInputString = params[0];
            try {
                URL url = new URL("http://10.0.2.2/lifestyle.php"); // Replace with your server URL
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // Write JSON data to the output stream
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Read the response from the server
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Handle the response from the server here
            Toast.makeText(incident4.this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
