package com.pbcr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

public class inc extends AppCompatActivity {
    private EditText date, date1, date2, date3, date4, pathologyslideno, icdcod, remarkmention, specifysite, T, N, M, hospname, hospregno, treattaken, nameofinformant, relationwithpatient, phone;
    DatePickerDialog datePickerDialog, datePickerDialog1, datePickerDialog2, datePickerDialog3, datePickerDialog4,datePickerDialog5;
    private RadioGroup r2, r3, r4, r5, r6, r7, r8, r9,radioGroupAlive;
    Spinner sp1, sp2, sp3, sp4, sp5, sp6;
    CheckBox checkBox21,checkBox29,checkBox30,checkBox31,checkBox32,checkBox5,checkBox19,checkBox001,checkBox002,checkBox003,checkBox004,checkBox005,checkBox006,checkBox007,checkBox01,checkBox02,checkBox03,checkBox04,checkBox05,checkBox06,checkBox07,checkBox08,checkBox09,checkBox010,checkBox011,checkBox012,checkBox013,checkBox014,checkBox015,checkBox016,checkBox017,checkBox018,checkBox019,checkBox0001,checkBox0002,checkBox0003,checkBox0004,checkBox0005,checkBox0006,checkBox0007,checkBox00001,checkBox00002,checkBox00003,checkBox00004,checkBox00005,checkBox00006,checkBox00007;
    final String[] status = {""};
    private LinearLayout deathDetailsLayout;
    private EditText daten,regno,drname,hospnamen,hospadr;
    private final String[] cause = {"Select Item", "Due to Cancer", "Other than Cancer", "Cardiac", "Respiratory", "Peripheral", "Circulatory", "Cerebro Vascular","Failure","Accident","Suicide"};
    private final String[] place = {"Select Item", "Hospital", "Nursing Home", "Residence", "Others"};
    private Spinner spinner, spinner1;

    final String TAG = "inc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc);
        daten = findViewById(R.id.dateOfDeath);
        daten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog5 = new DatePickerDialog(inc.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        daten.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);
                    }
                }, year, month, dayofmonth);
                datePickerDialog5.show();
            }
        });
        regno = findViewById(R.id.mortalityRegNumber);
        drname = findViewById(R.id.doctorName);
        hospnamen = findViewById(R.id.hospitalName);
        hospadr = findViewById(R.id.hospitalAddress);
        spinner = findViewById(R.id.causeOfDeath);
        setupSpinner(spinner, cause);
        spinner1 = findViewById(R.id.placeOfDeath);
        setupSpinner(spinner1, place);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        r5 = findViewById(R.id.r5);
        r6 = findViewById(R.id.r6);
        r7 = findViewById(R.id.r7);
        r8 = findViewById(R.id.r8);
        r9 = findViewById(R.id.r9);
        pathologyslideno = findViewById(R.id.pslideno);
        icdcod = findViewById(R.id.icd);
        icdcod = findViewById(R.id.icdh);
        icdcod = findViewById(R.id.icd1);
        remarkmention = findViewById(R.id.remark);
        icdcod = findViewById(R.id.icd2);
        icdcod = findViewById(R.id.icd3);
        specifysite = findViewById(R.id.spsite);
        T = findViewById(R.id.t);
        N = findViewById(R.id.number);
        M = findViewById(R.id.m);
        hospname = findViewById(R.id.hname);
        hospregno = findViewById(R.id.hrno);
        treattaken = findViewById(R.id.ttaken);
        nameofinformant = findViewById(R.id.nameinf);
        relationwithpatient = findViewById(R.id.relationp);
        phone = findViewById(R.id.phonemobile);
         checkBox21 = findViewById(R.id.checkBox21);
         checkBox29 = findViewById(R.id.checkBox29);
         checkBox30 = findViewById(R.id.checkBox30);
         checkBox31 = findViewById(R.id.checkBox31);
         checkBox32 = findViewById(R.id.checkBox32);
         checkBox001 = findViewById(R.id.checkbox3);
         checkBox002 = findViewById(R.id.checkbox1);
         checkBox003 = findViewById(R.id.checkbox11);
         checkBox004 = findViewById(R.id.checkbox17);
         checkBox005 = findViewById(R.id.checkboxdoc);
         checkBox006 = findViewById(R.id.checkbox111);
         checkBox007 = findViewById(R.id.checkbox14);
         checkBox5 = findViewById(R.id.checkBox5);
         checkBox19 = findViewById(R.id.checkBox19);
         checkBox01 = findViewById(R.id.checkbox_option1);
         checkBox02 = findViewById(R.id.checkbox_option2);
         checkBox03 = findViewById(R.id.checkbox_option3);
         checkBox04 = findViewById(R.id.checkbox_option4);
         checkBox05 = findViewById(R.id.checkbox_option5);
         checkBox06 = findViewById(R.id.checkbox_option6);
         checkBox07 = findViewById(R.id.checkbox_option7);
         checkBox08 = findViewById(R.id.checkbox_option8);
         checkBox09 = findViewById(R.id.checkbox_option9);
         checkBox010 = findViewById(R.id.checkbox_option10);
         checkBox011 = findViewById(R.id.checkbox_option11);
         checkBox012 = findViewById(R.id.checkbox_option12);
         checkBox013 = findViewById(R.id.checkbox_option13);
         checkBox014 = findViewById(R.id.checkbox_option14);
         checkBox015 = findViewById(R.id.checkbox_option15);
         checkBox016 = findViewById(R.id.checkbox_option16);
         checkBox017 = findViewById(R.id.checkbox_option17);
         checkBox018 = findViewById(R.id.checkbox_option18);
         checkBox019 = findViewById(R.id.checkbox_option19);
        checkBox0001 = findViewById(R.id.checkbox_option20);
        checkBox0002 = findViewById(R.id.checkbox_option21);
        checkBox0003 = findViewById(R.id.checkbox_option22);
        checkBox0004 = findViewById(R.id.checkbox_option23);
        checkBox0005 = findViewById(R.id.checkbox_option24);
        checkBox0006 = findViewById(R.id.checkbox_option25);
        checkBox0007 = findViewById(R.id.checkbox_option26);
        checkBox00001 = findViewById(R.id.checkbox_option27);
        checkBox00002 = findViewById(R.id.checkbox_option28);
        checkBox00003 = findViewById(R.id.checkbox_option29);
        checkBox00004 = findViewById(R.id.checkbox_option30);
        checkBox00005 = findViewById(R.id.checkbox_option31);
        checkBox00006 = findViewById(R.id.checkbox_option32);
        checkBox00007 = findViewById(R.id.checkbox_option33);
        radioGroupAlive = findViewById(R.id.r10);
        deathDetailsLayout = findViewById(R.id.deathDetailsLayout);
        radioGroupAlive.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton45) {
                    deathDetailsLayout.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.radioButton47) {
                    deathDetailsLayout.setVisibility(View.GONE);
                }
            }
        });



        sp1 = (Spinner) findViewById(R.id.spntmr);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inc.this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        final List<String> tumourList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/tmr.php");
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
                        tumourList.add("Select item");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String tumour_site_name = row.getString("tumour_site_name");
                            tumourList.add(tumour_site_name);
                        }

                        Log.d(TAG, "Data fetched: " + tumourList);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.clear();
                                adapter.addAll(tumourList);
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

        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.d(TAG, String.valueOf(tumourList));
                Log.d(TAG, value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "nothing selected");
            }
        });
        sp2 = (Spinner) findViewById(R.id.spnhist);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(inc.this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter1);

        final List<String> histologyList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/hist.php");
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
                        histologyList.add("Select item");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String histology_name = row.getString("histology_name");
                            histologyList.add(histology_name);
                        }

                        Log.d(TAG, "Data fetched: " + histologyList);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter1.clear();
                                adapter1.addAll(histologyList);
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

        sp2.setSelection(0);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.d(TAG, String.valueOf(histologyList));
                Log.d(TAG, value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "nothing selected");
            }
        });
        sp3 = (Spinner) findViewById(R.id.spnhist1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(inc.this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter2);

        final List<String> histologygradingList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/histgrading.php");
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
                        histologygradingList.add("Select item");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String grading_name = row.getString("grading_name");
                            histologygradingList.add(grading_name);
                        }

                        Log.d(TAG, "Data fetched: " + histologygradingList);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter2.clear();
                                adapter2.addAll(histologygradingList);
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

        sp3.setSelection(0);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.d(TAG, String.valueOf(histologygradingList));
                Log.d(TAG, value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "nothing selected");
            }
        });
        sp4 = (Spinner) findViewById(R.id.spntmr1);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(inc.this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(adapter3);

        final List<String> tumour2List = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/tmr.php");
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
                        tumour2List.add("Select item");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String tumour_site_name = row.getString("tumour_site_name");
                            tumour2List.add(tumour_site_name);
                        }

                        Log.d(TAG, "Data fetched: " + tumour2List);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter3.clear();
                                adapter3.addAll(tumour2List);
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

        sp4.setSelection(0);
        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.d(TAG, String.valueOf(tumour2List));
                Log.d(TAG, value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "nothing selected");
            }
        });
        sp5 = (Spinner) findViewById(R.id.spnhist2);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(inc.this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp5.setAdapter(adapter4);

        final List<String> histology2List = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/hist.php");
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
                        histology2List.add("Select item");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String histology_name = row.getString("histology_name");
                            histology2List.add(histology_name);
                        }

                        Log.d(TAG, "Data fetched: " + histology2List);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter4.clear();
                                adapter4.addAll(histology2List);
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

        sp5.setSelection(0);
        sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.d(TAG, String.valueOf(histology2List));
                Log.d(TAG, value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "nothing selected");
            }
        });
        sp6 = (Spinner) findViewById(R.id.spnhist3);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(inc.this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp6.setAdapter(adapter5);

        final List<String> histologygrading2List = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL url = new URL("http://10.0.2.2/histgrading.php");
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
                        histologygrading2List.add("Select item");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject row = data.getJSONObject(i);
                            String grading_name = row.getString("grading_name");
                            histologygrading2List.add(grading_name);
                        }

                        Log.d(TAG, "Data fetched: " + histologygrading2List);

                        // Update the spinner on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter5.clear();
                                adapter5.addAll(histologygrading2List);
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

        sp6.setSelection(0);
        sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Log.d(TAG, String.valueOf(histologygrading2List));
                Log.d(TAG, value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "nothing selected");
            }
        });
        date = findViewById(R.id.pathdate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(inc.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        date.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);

                    }
                }, year, month, dayofmonth);
                datePickerDialog.show();
            }
        });
        date1 = findViewById(R.id.date1);
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog1 = new DatePickerDialog(inc.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        date1.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);

                    }
                }, year, month, dayofmonth);
                datePickerDialog1.show();
            }
        });
        date2 = findViewById(R.id.Date2);
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog2 = new DatePickerDialog(inc.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        date2.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);

                    }
                }, year, month, dayofmonth);
                datePickerDialog2.show();
            }
        });
        date3 = findViewById(R.id.Date3);
        date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog3 = new DatePickerDialog(inc.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        date3.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);

                    }
                }, year, month, dayofmonth);
                datePickerDialog3.show();
            }
        });
        date4 = findViewById(R.id.Date4);
        date4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog4 = new DatePickerDialog(inc.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        date4.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);

                    }
                }, year, month, dayofmonth);
                datePickerDialog4.show();
            }
        });


        CheckBox checkboxToggle = findViewById(R.id.checkbox1);
        LinearLayout layoutOptions = findViewById(R.id.layout_options);

        checkboxToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutOptions.setVisibility(View.VISIBLE);
            } else {
                layoutOptions.setVisibility(View.GONE);
            }
        });
        CheckBox checkboxToggle1 = findViewById(R.id.checkbox11);
        LinearLayout layoutOptionss = findViewById(R.id.layout_optionss);

        checkboxToggle1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutOptionss.setVisibility(View.VISIBLE);
            } else {
                layoutOptionss.setVisibility(View.GONE);
            }
        });
        CheckBox checkboxToggle2 = findViewById(R.id.checkbox111);
        LinearLayout layoutOptionsss = findViewById(R.id.layout_optionsss);

        checkboxToggle2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutOptionsss.setVisibility(View.VISIBLE);
            } else {
                layoutOptionsss.setVisibility(View.GONE);
            }
        });
        Button btnback3 = (Button) findViewById(R.id.btnback4);
        btnback3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inc.this, incident4.class);
                startActivity(intent);
                finish();
            }
//
        });


        Button btnnext3 = findViewById(R.id.btnnext4);
        btnnext3.setOnClickListener(v -> {
            // Collecting checkbox data
            ArrayList<Integer> selectedCheckboxIds = getSelectedCheckboxIds();
            ArrayList<Integer> selectedCheckboxIds1 = getSelectedCheckboxIds1();
            ArrayList<Integer> selectedCheckboxIds2 = getSelectedCheckboxIds2();
            ArrayList<Integer> selectedCheckboxIds3 = getSelectedCheckboxIds3();
            ArrayList<Integer> selectedCheckboxIds4 = getSelectedCheckboxIds4();


            // Convert to JSON using Gson
            String selectedCheckboxIdsJson = new Gson().toJson(selectedCheckboxIds);
            String selectedCheckboxIds1Json = new Gson().toJson(selectedCheckboxIds1);
            String selectedCheckboxIds2Json = new Gson().toJson(selectedCheckboxIds2);
            String selectedCheckboxIds3Json = new Gson().toJson(selectedCheckboxIds3);
            String selectedCheckboxIds4Json = new Gson().toJson(selectedCheckboxIds4);



            // Send data to the server
            sendDataToServer(selectedCheckboxIdsJson, "http://10.0.2.2/dia1.php");
            sendDataToServer(selectedCheckboxIds1Json, "http://10.0.2.2/dia2.php");
            sendDataToServer(selectedCheckboxIds2Json, "http://10.0.2.2/dia3.php");
            sendDataToServer(selectedCheckboxIds3Json, "http://10.0.2.2/stage.php");
            sendDataToServer(selectedCheckboxIds4Json, "http://10.0.2.2/treatgiven.php");



            // Collect other form data
            String cancerinfopathologyno = pathologyslideno.getText().toString();
            String cancerinfodate = date.getText().toString();
            String cancerinfoprimarytumoursite = sp1.getSelectedItem().toString();
            String cancerinfoprimaryhistology = sp2.getSelectedItem().toString();
            String cancerinfosecondarytumoursite = sp4.getSelectedItem().toString();
            String cancerinforemarksecsite = remarkmention.getText().toString();
            String cancerinfosecondaryhistology = sp5.getSelectedItem().toString();
            String cancerinfohistologicgrading = sp3.getSelectedItem().toString();
            String cancerinfohistologicgradingsec = sp6.getSelectedItem().toString();
            String cancerinfoicd10 = icdcod.getText().toString();

            // Collect radio button data
            String cancerinfolaterality = getSelectedRadioButtonText(r2);
            String cancerinfosequence = getSelectedRadioButtonText(r3);
            String cancerinfoclinicalextent = getSelectedRadioButtonText(r4);
            String cancerinfocomposite = getSelectedRadioButtonText(r5);
            String cancerinfointention = getSelectedRadioButtonText(r6);
            String cancerinfotretmentreceived = getSelectedRadioButtonText(r7);
            String cancerinfoistreatcomp = getSelectedRadioButtonText(r8);
            String cancerinfofamilyhistory = getSelectedRadioButtonText(r9);

            // Collect text data
            String cancerinfosequencesite = specifysite.getText().toString();
            String cancerinfotnmt = T.getText().toString();
            String cancerinfotnmn = N.getText().toString();
            String cancerinfotnmm = M.getText().toString();
            String cancerinfohospname = hospname.getText().toString();
            String cancerinfohospregno = hospregno.getText().toString();
            String cancerinfodatereg = date1.getText().toString();
            String cancerinfotreattaken = treattaken.getText().toString();
            String cancerinfotretmentdate = date2.getText().toString();
            String cancerinfodateofcompletion = date3.getText().toString();
            String cancerinfodatelastcontact = date4.getText().toString();
            String cancerinfoinformantname = nameofinformant.getText().toString();
            String cancerinfoinformantrelation = relationwithpatient.getText().toString();
            String cancerinfoinformantmob = phone.getText().toString();
            String registernumber = regno.getText().toString();
            String doctorname = drname.getText().toString();
            String hospitalname = hospnamen.getText().toString();
            String hospitaladdress = hospadr.getText().toString();
            String deathofcause = spinner.getSelectedItem().toString();
            String placeofdeath = spinner1.getSelectedItem().toString();
            String Date = daten.getText().toString();
            sendmortality m = new sendmortality();
            m.sendMortality( registernumber, doctorname,  hospitalname, hospitaladdress,Date,  deathofcause,placeofdeath);

            // Send form data
            CancerRelated rel = new CancerRelated();
            rel.sendCancerRelatedData(cancerinfopathologyno, cancerinfodate, cancerinfoprimarytumoursite, cancerinfoprimaryhistology, cancerinfosecondarytumoursite, cancerinforemarksecsite, cancerinfosecondaryhistology, cancerinfohistologicgrading, cancerinfohistologicgradingsec, cancerinfoicd10, cancerinfolaterality, cancerinfosequence, cancerinfosequencesite, cancerinfoclinicalextent, cancerinfotnmt, cancerinfotnmn, cancerinfotnmm, cancerinfocomposite, cancerinfohospname, cancerinfohospregno, cancerinfodatereg, cancerinfotreattaken, cancerinfointention, cancerinfotretmentreceived, cancerinfotretmentdate, cancerinfoistreatcomp, cancerinfodateofcompletion, cancerinfodatelastcontact, cancerinfofamilyhistory, cancerinfoinformantname, cancerinfoinformantrelation, cancerinfoinformantmob);

            // Navigate to homepage
            Intent intent = new Intent(inc.this, homepage.class);
            startActivity(intent);
            finish();
        });
    }


        // Collect selected checkboxes for the first set
        private ArrayList<Integer> getSelectedCheckboxIds() {
            ArrayList<Integer> selectedCheckboxIds = new ArrayList<>();
            if (checkBox21.isChecked()) selectedCheckboxIds.add(2);
            if (checkBox29.isChecked()) selectedCheckboxIds.add(3);
            if (checkBox30.isChecked()) selectedCheckboxIds.add(4);
            if (checkBox31.isChecked()) selectedCheckboxIds.add(5);
            if (checkBox32.isChecked()) selectedCheckboxIds.add(6);
            if (checkBox5.isChecked()) selectedCheckboxIds.add(98);
            if (checkBox19.isChecked()) selectedCheckboxIds.add(99);  // Make sure checkbox19 is handled
            return selectedCheckboxIds;
        }

        // Collect selected checkboxes for the second set
        private ArrayList<Integer> getSelectedCheckboxIds1 () {
            ArrayList<Integer> selectedCheckboxIds1 = new ArrayList<>();
            if (checkBox001.isChecked()) selectedCheckboxIds1.add(1);
            if (checkBox002.isChecked()) selectedCheckboxIds1.add(2);
            if (checkBox003.isChecked()) selectedCheckboxIds1.add(3);
            if (checkBox004.isChecked()) selectedCheckboxIds1.add(4);
            if (checkBox005.isChecked()) selectedCheckboxIds1.add(5);
            if (checkBox006.isChecked()) selectedCheckboxIds1.add(98);
            if (checkBox007.isChecked()) selectedCheckboxIds1.add(99);
            return selectedCheckboxIds1;
        }
    private ArrayList<Integer> getSelectedCheckboxIds2 () {
        ArrayList<Integer> selectedCheckboxIds2 = new ArrayList<>();
        if (checkBox01.isChecked()) selectedCheckboxIds2.add(1);
        if (checkBox02.isChecked()) selectedCheckboxIds2.add(2);
        if (checkBox03.isChecked()) selectedCheckboxIds2.add(3);
        if (checkBox04.isChecked()) selectedCheckboxIds2.add(4);
        if (checkBox05.isChecked()) selectedCheckboxIds2.add(5);
        if (checkBox06.isChecked()) selectedCheckboxIds2.add(16);
        if (checkBox07.isChecked()) selectedCheckboxIds2.add(6);
        if (checkBox08.isChecked()) selectedCheckboxIds2.add(7);
        if (checkBox09.isChecked()) selectedCheckboxIds2.add(8);
        if (checkBox010.isChecked()) selectedCheckboxIds2.add(9);
        if (checkBox011.isChecked()) selectedCheckboxIds2.add(10);
        if (checkBox012.isChecked()) selectedCheckboxIds2.add(17);
        if (checkBox013.isChecked()) selectedCheckboxIds2.add(18);
        if (checkBox014.isChecked()) selectedCheckboxIds2.add(19);
        if (checkBox015.isChecked()) selectedCheckboxIds2.add(20);
        if (checkBox016.isChecked()) selectedCheckboxIds2.add(11);
        if (checkBox017.isChecked()) selectedCheckboxIds2.add(12);
        if (checkBox018.isChecked()) selectedCheckboxIds2.add(13);
        if (checkBox019.isChecked()) selectedCheckboxIds2.add(14);
        return selectedCheckboxIds2;
    }
    private ArrayList<Integer> getSelectedCheckboxIds3 () {
       ArrayList<Integer> selectedCheckboxIds3 = new ArrayList<>();
        if (checkBox0001.isChecked()) selectedCheckboxIds3.add(1);
        if (checkBox0002.isChecked()) selectedCheckboxIds3.add(2);
         if (checkBox0003.isChecked()) selectedCheckboxIds3.add(3);
         if (checkBox0004.isChecked()) selectedCheckboxIds3.add(4);
         if (checkBox0005.isChecked()) selectedCheckboxIds3.add(5);
         if (checkBox0006.isChecked()) selectedCheckboxIds3.add(98);
         if (checkBox0007.isChecked()) selectedCheckboxIds3.add(99);
        return selectedCheckboxIds3;

    }
    private ArrayList<Integer> getSelectedCheckboxIds4 () {
        ArrayList<Integer> selectedCheckboxIds4 = new ArrayList<>();
        if (checkBox00001.isChecked()) selectedCheckboxIds4.add(1);
        if (checkBox00002.isChecked()) selectedCheckboxIds4.add(2);
        if (checkBox00003.isChecked()) selectedCheckboxIds4.add(3);
        if (checkBox00004.isChecked()) selectedCheckboxIds4.add(4);
        if (checkBox00005.isChecked()) selectedCheckboxIds4.add(5);
        if (checkBox00006.isChecked()) selectedCheckboxIds4.add(98);
        if (checkBox00007.isChecked()) selectedCheckboxIds4.add(99);
        return selectedCheckboxIds4;
    }



    private void sendDataToServer(String jsonData, String serverUrl) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(serverUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    urlConnection.setDoOutput(true);

                    OutputStream outputStream = urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write("selectedCheckboxes=" + URLEncoder.encode(jsonData, "UTF-8"));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        reader.close();
                        return result.toString();
                    } else {
                        return "Error: " + responseCode;
                    }
                } catch (Exception e) {
                    return "Exception: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(), "Server Response: " + result, Toast.LENGTH_LONG).show();
            }
        }.execute();
    }


    // Get selected RadioButton text
    private String getSelectedRadioButtonText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        return selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";



    }
    private void setupSpinner(Spinner spinner, String[] items) {  ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}