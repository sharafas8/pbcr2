package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class incident2 extends AppCompatActivity {
    private EditText text1, text3, text4, text5, text6, text7, text8, text9;
    private RadioGroup r1;
    private EditText editTextOtherReligion;
    private Spinner spinner, spinner1, spinner2, spinner3, spinner4;
    private final String[] id = { "Aadhaar Card ", "ABHA Card", "Ration Card", "PAN Card", "Driving License", "Passport Number", "Voter ID"};
    private final String[] martial = {"Select status", "Un Married", "Married", "Widow/Widower", "Seperated", "Divorced", "Unknown"};
    private final String[] education = {"Select education", "Illiterate", "Literate", "LP", "UP", "High School", "Plus Two/PDC", "Diploma", "Degree", "PG", "Professional", "Others", "Unknown"};
    private final String[] religion = {"Select religion", "Hindu", "Christian", "Muslim", "Others", "Unknown"};

    private final String TAG = "incident2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident2);

        text1 = findViewById(R.id.name1);
        r1=findViewById(R.id.sex);
        text3 = findViewById(R.id.idn);
        text4 = findViewById(R.id.age);
        text5 = findViewById(R.id.father);
        text6 = findViewById(R.id.mother);
        text7 = findViewById(R.id.wife);
        text8 = findViewById(R.id.son);
        text9 = findViewById(R.id.daughter);

        spinner = findViewById(R.id.spnid);
        setupSpinner(spinner, id);
        spinner1 = findViewById(R.id.spnmart);
        setupSpinner(spinner1, martial);
        spinner2 = findViewById(R.id.spnedu);
        setupSpinner(spinner2, education);
        spinner3 = findViewById(R.id.spnrel);
        editTextOtherReligion = findViewById(R.id.editTextOtherReligion); // Bind EditText
        setupSpinner(spinner3, religion);
        spinner4 = findViewById(R.id.spnmthr);
        final ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter5);

        final List<String> motherTongue = new ArrayList<>();
        fetchMotherTongue(adapter5, motherTongue);

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "Mother Tongue selected: " + value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "Nothing selected");
            }
        });


        Button btnBack1 = findViewById(R.id.btnback1);
        btnBack1.setOnClickListener(v -> {
            Intent intent = new Intent(incident2.this, incident1.class);
            startActivity(intent);
            finish();
        });

        Button btnNext1 = findViewById(R.id.btnnext1);
        btnNext1.setOnClickListener(v -> {
            String patientName = text1.getText().toString();
            String patientIdType = spinner.getSelectedItem().toString();
            String patientIdNo = text3.getText().toString();
            String patientAge = text4.getText().toString();

            // Get the selected RadioButton from the RadioGroup
            int selectedId = r1.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);

            String patientSex = selectedRadioButton != null ? selectedRadioButton.getText().toString() : ""; // Ensure it's not null

            String patientFather = text5.getText().toString();
            String patientMother = text6.getText().toString();
            String patientSpouse = text7.getText().toString();
            String patientChild = text8.getText().toString();
            String patientDaughter = text9.getText().toString();
            String patientMaritalStatus = spinner1.getSelectedItem().toString();
            String patientMotherTongue = spinner4.getSelectedItem().toString();
            String patientReligion = spinner3.getSelectedItem().toString();
            if (patientReligion.equals("Others")) {
                patientReligion = editTextOtherReligion.getText().toString().trim();
            }
            String patientEducation = spinner2.getSelectedItem().toString();

            // Use sendPatintData instance to send data
            sendPatintData sender = new sendPatintData();
            sender.sendPatientData(patientName, patientIdType, patientIdNo, patientAge, patientSex, patientFather, patientMother, patientSpouse, patientChild, patientDaughter, patientMaritalStatus, patientMotherTongue, patientReligion, patientEducation);

            Intent intent = new Intent(incident2.this, incident3.class);
            startActivity(intent);
            finish();
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Check if "Others" is selected
                if (religion[position].equals("Others")) {
                    editTextOtherReligion.setVisibility(View.VISIBLE); // Show the EditText
                } else {
                    editTextOtherReligion.setVisibility(View.GONE); // Hide the EditText
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

    }

    private void setupSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void fetchMotherTongue(final ArrayAdapter<String> adapter5, final List<String> motherTongue) {
        new Thread(() -> {
            String result;
            try {
                URL url = new URL("http://10.0.2.2/tongue.php");
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
                    result = null;
                }
                connection.disconnect();

                if (result != null) {
                    JSONObject jsonResponse = new JSONObject(result);
                    JSONArray data = jsonResponse.getJSONArray("data");
                    //motherTongue.add("Select Item");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject row = data.getJSONObject(i);
                        String motherTongueName = row.getString("mothertongue_name");
                        motherTongue.add(motherTongueName);
                    }

                    Log.d(TAG, "Data fetched: " + motherTongue);

                    runOnUiThread(() -> {
                        adapter5.clear();
                        adapter5.addAll(motherTongue);
                        adapter5.notifyDataSetChanged();
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Error: " + e.getMessage());
            }
        }).start();
    }
}
