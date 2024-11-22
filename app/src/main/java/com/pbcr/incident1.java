package com.pbcr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class incident1 extends AppCompatActivity {
    private String selectedDept;
    private String selectedInst;
    private String selectedDist;
    private String regDate;
    private String regNo;
    private String diagnosisDate;
    private EditText date, date1,text;
    DatePickerDialog datePickerDialog, datePickerDialog1;

    private AutoCompleteTextView autoCompleteTextView,autoCompleteTextView1,autoCompleteTextView2; // Using AutoCompleteTextView instead of Spinner

    final String[] status = {""};
    final String TAG = "incident1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident1);
        text = findViewById(R.id.regno);

        date = findViewById(R.id.Date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(incident1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        date.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);
                    }
                }, year, month, dayofmonth);
                datePickerDialog.show();
            }
        });

        date1 = findViewById(R.id.Date1);
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog1 = new DatePickerDialog(incident1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        date1.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);
                    }
                }, year, month, dayofmonth);
                datePickerDialog1.show();
            }
        });

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1); // Show suggestions after typing 1 character


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
                        JSONArray data = jsonResponse.getJSONArray("data");
                       // deptList.add("");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String department_name = row.getString("department_name");
                            deptList.add(department_name);
                        }

                        Log.d(TAG, "Data fetched: " + deptList);

                        // Update the adapter on the main thread
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
        // Show all items in dropdown when clicked
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.showDropDown(); // Show the dropdown
            }
        });


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDept = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + selectedDept);
            }
        });
        autoCompleteTextView1 = findViewById(R.id.autoCompleteTextView1); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        autoCompleteTextView1.setAdapter(adapter5);
        autoCompleteTextView1.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> instList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/institutionname.php");
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
                      //  instList.add("");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String institution_name = row.getString("institution_name");
                            instList.add(institution_name);
                        }

                        Log.d(TAG, "Data fetched: " + instList);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter5.clear();
                                adapter5.addAll(instList);
                                adapter5.notifyDataSetChanged();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error: " + e.getMessage());
                }
            }
        }).start();
        // Show all items in dropdown when clicked
        autoCompleteTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView1.showDropDown(); // Show the dropdown
            }
        });


        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedInst = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + selectedInst);
            }
        });
        autoCompleteTextView2 = findViewById(R.id.autoCompleteTextView2); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        autoCompleteTextView2.setAdapter(adapter2);
        autoCompleteTextView2.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> distList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/district.php");
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
                      //  distList.add("");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String district_name = row.getString("district_name");
                            distList.add(district_name);
                        }

                        Log.d(TAG, "Data fetched: " + distList);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter2.clear();
                                adapter2.addAll(distList);
                                adapter2.notifyDataSetChanged();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error: " + e.getMessage());
                }
            }
        }).start();
        // Show all items in dropdown when clicked
        autoCompleteTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView2.showDropDown(); // Show the dropdown
            }
        });


        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDist = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + selectedDist);
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

                selectedDept = autoCompleteTextView.getText().toString();
                selectedInst = autoCompleteTextView1.getText().toString();
                selectedDist = autoCompleteTextView2.getText().toString();
                regDate = date.getText().toString();
                regNo = text.getText().toString();
                diagnosisDate = date1.getText().toString();


                // Validate inputs (basic example, you might want to add more validation)
               //if (selectedDept.equals("Select department") || selectedInst.equals("Select institution Name") || selectedDist.equals("Select District") || regDate.isEmpty() || regNo.isEmpty() ||  diagnosisDate.isEmpty()) {
                  // Toast.makeText(incident1.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                   // return;
              //  }

                // Create an instance of YourClassName and set the data
                YourClassName yourClassInstance = new YourClassName();
                yourClassInstance.setInstitutionDetails(selectedInst, selectedDept, selectedDist, regDate,regNo, diagnosisDate);

                // Submit data
                yourClassInstance.submitData();

                // Start the next activity
                Intent intent = new Intent(incident1.this, incident2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
