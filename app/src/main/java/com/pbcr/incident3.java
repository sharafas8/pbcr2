package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
public class incident3 extends AppCompatActivity {
    private String district, panchayath, postoffice, ward, taluk, village, phc, subcenter, residency;
    EditText t1,t2,t3,t4,t5;
    private AutoCompleteTextView auto1,auto2,auto3,auto4,auto5,auto6,auto7,auto8,auto9;
    final String[] status = {""};
    final String TAG = "incident3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident3);
        t1 = findViewById(R.id.hn);
        t2 = findViewById(R.id.al);
        t3 = findViewById(R.id.plc);
        t4 = findViewById(R.id.dsy);
        t5 = findViewById(R.id.pn);
        auto1 = findViewById(R.id.auto1); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto1.setAdapter(adapter);
        auto1.setThreshold(1); // Show suggestions after typing 1 character


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
                                adapter.clear();
                                adapter.addAll(distList);
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
        auto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto1.showDropDown(); // Show the dropdown
            }
        });


        auto1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                district = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + district);
            }
        });

        auto2 = findViewById(R.id.auto2); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto2.setAdapter(adapter1);
        auto2.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> panchayathList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/panchayath.php");
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
                      //  panchayathList.add("Select Panchayath/Municipality/Corporation");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String localbody_name = row.getString("localbody_name");
                            panchayathList.add(localbody_name);
                        }
                        Log.d(TAG, "Data fetched: " + panchayathList);
                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter1.clear();
                                adapter1.addAll(panchayathList);
                                adapter1.notifyDataSetChanged();
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
        auto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto2.showDropDown(); // Show the dropdown
            }
        });


        auto2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                panchayath = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + panchayath);
            }
        });
        auto3 = findViewById(R.id.auto3); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto3.setAdapter(adapter2);
        auto3.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> postofficeList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    URL url = new URL("http://10.0.2.2/postoffice.php");
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
                       // postofficeList.add("Select postoffice");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String post_offices_name = row.getString("post_offices_name");
                            postofficeList.add(post_offices_name);
                        }
                        Log.d(TAG, "Data fetched: " + postofficeList);
                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter2.clear();
                                adapter2.addAll(postofficeList);
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
        auto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto3.showDropDown(); // Show the dropdown
            }
        });


        auto3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                postoffice = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + postoffice);
            }
        });
        auto4 = findViewById(R.id.auto4); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto4.setAdapter(adapter3);
        auto4.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> wardList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    URL url = new URL("http://10.0.2.2/ward.php");
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
                      //  wardList.add("Select ward/Division");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String ward_name = row.getString("ward_name");
                            wardList.add(ward_name);
                        }
                        Log.d(TAG, "Data fetched: " + wardList);
                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter3.clear();
                                adapter3.addAll(wardList);
                                adapter3.notifyDataSetChanged();
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
        auto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto4.showDropDown(); // Show the dropdown
            }
        });


        auto4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ward = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + ward);
            }
        });
        auto5 = findViewById(R.id.auto5); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto5.setAdapter(adapter4);
        auto5.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> talukList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    URL url = new URL("http://10.0.2.2/taluk.php");
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
                       // talukList.add("Select Taluk");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String taluk_name = row.getString("taluk_name");
                            talukList.add(taluk_name);
                        }
                        Log.d(TAG, "Data fetched: " + talukList);
                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter4.clear();
                                adapter4.addAll(talukList);
                                adapter4.notifyDataSetChanged();
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
        auto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto5.showDropDown(); // Show the dropdown
            }
        });


        auto5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                taluk = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + taluk);
            }
        });

        auto6 = findViewById(R.id.auto6); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto6.setAdapter(adapter5);
        auto6.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> villageList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    URL url = new URL("http://10.0.2.2/village.php");
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
                     //   villageList.add("Select village");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String village_name = row.getString("village_name");
                            villageList.add(village_name);
                        }
                        Log.d(TAG, "Data fetched: " + villageList);
                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter5.clear();
                                adapter5.addAll(villageList);
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
        auto6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto6.showDropDown(); // Show the dropdown
            }
        });


        auto6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                village = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + village);
            }
        });
        auto7 = findViewById(R.id.auto7); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter6 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto7.setAdapter(adapter6);
        auto7.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> phcList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    URL url = new URL("http://10.0.2.2/phc.php");
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
                     //   phcList.add("Select PHC");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String phc_name = row.getString("phc_name");
                            phcList.add(phc_name);
                        }
                        Log.d(TAG, "Data fetched: " + phcList);
                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter6.clear();
                                adapter6.addAll(phcList);
                                adapter6.notifyDataSetChanged();
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
        auto7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto7.showDropDown(); // Show the dropdown
            }
        });


        auto7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                phc = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + phc);
            }
        });
        auto8 = findViewById(R.id.auto8); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto8.setAdapter(adapter7);
        auto8.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> subcenterList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    URL url = new URL("http://10.0.2.2/subcenter.php");
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
                      //  subcenterList.add("Select Subcenter");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String vchLBName = row.getString("vchLBName");
                            subcenterList.add(vchLBName);
                        }
                        Log.d(TAG, "Data fetched: " + subcenterList);
                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter7.clear();
                                adapter7.addAll(subcenterList);
                                adapter7.notifyDataSetChanged();
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
        auto8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto8.showDropDown(); // Show the dropdown
            }
        });


        auto8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subcenter = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + subcenter);
            }
        });
        auto9 = findViewById(R.id.auto9); // Bind to AutoCompleteTextView
        ArrayAdapter<String> adapter8 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        auto9.setAdapter(adapter8);
        auto9.setThreshold(1); // Show suggestions after typing 1 character


        final List<String> deptList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    URL url = new URL("http://10.0.2.2/placeofresidency.php");
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
                       // deptList.add("Select Residence place");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String residenceplace_name = row.getString("residenceplace_name");
                            deptList.add(residenceplace_name);
                        }
                        Log.d(TAG, "Data fetched: " + deptList);
                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter8.clear();
                                adapter8.addAll(deptList);
                                adapter8.notifyDataSetChanged();
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
        auto9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto9.showDropDown(); // Show the dropdown
            }
        });


        auto9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                residency = parent.getItemAtPosition(position).toString();
                Log.d(TAG, " " + residency);
            }
        });






        Button btnback2 = (Button) findViewById(R.id.btnback2);
        btnback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident3.this, incident2.class);
                startActivity(intent);
                finish();
            }
        });
        Button btnnext2 = (Button) findViewById(R.id.btnnext2);
        btnnext2.setOnClickListener(v -> {
// Fetch input data from UI components
        String houseName = t1.getText().toString();
         district = auto1.getText().toString();
        String area = t2.getText().toString();
        String place = t3.getText().toString();
         panchayath = auto2.getText().toString();
        String phoneno = t5.getText().toString();
        String duration = t4.getText().toString();
         phc = auto7.getText().toString();
         subcenter = auto8.getText().toString();
         residency =auto9.getText().toString();
         postoffice = auto3.getText().toString();
         ward = auto4.getText().toString();
         taluk = auto5.getText().toString();
         village = auto6.getText().toString();

// Create instance of Address and send data
        Address adr = new Address();
        adr.sendAddress(houseName, district, panchayath, area, place, postoffice, ward, taluk, village, duration, phoneno, phc, subcenter, residency);

// Set OnClickListener for the button

            Intent intent = new Intent(incident3.this, incident4.class);
            startActivity(intent);
            finish();
        });
    }

    }