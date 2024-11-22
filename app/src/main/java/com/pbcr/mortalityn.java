package com.pbcr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class mortalityn extends AppCompatActivity {
    private EditText date,regno,drname,hospname,hospadr;
    DatePickerDialog datePickerDialog;
    private final String[] cause = {"Select Item", "Due to Cancer", "Other than Cancer", "Cardiac", "Respiratory", "Peripheral", "Circulatory", "Cerebro Vascular","Failure","Accident","Suicide"};
    private final String[] place = {"Select Item", "Hospital", "Nursing Home", "Residence", "Others"};
    private RadioGroup r;
    private Spinner spinner, spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortalityn);
        date = findViewById(R.id.date1);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(mortalityn.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        date.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);
                    }
                }, year, month, dayofmonth);
                datePickerDialog.show();
            }
        });
        regno = findViewById(R.id.number);
        drname = findViewById(R.id.drname);
        hospname = findViewById(R.id.hospname);
        hospadr = findViewById(R.id.hospaddr);
        r = findViewById(R.id.R);
        spinner = findViewById(R.id.causedeath);
        setupSpinner(spinner, cause);
        spinner1 = findViewById(R.id.placedeath);
        setupSpinner(spinner1, place);

        Button back =(Button) findViewById(R.id.btnbn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mortalityn.this, homepage.class);
                startActivity(intent);
                finish();
            }

        });
        Button next = (Button) findViewById(R.id.btnnn);
        next.setOnClickListener(v -> {
            String registernumber = regno.getText().toString();
            String doctorname = drname.getText().toString();
            String hospitalname = hospname.getText().toString();
            String hospitaladdress = hospadr.getText().toString();
            String deathofcause = spinner.getSelectedItem().toString();
            String placeofdeath = spinner1.getSelectedItem().toString();
            String Date = date.getText().toString();




            // Get the selected RadioButton from the RadioGroup
           // int selectedId = r.getCheckedRadioButtonId();
            //RadioButton selectedRadioButton = findViewById(selectedId);

          //  String status = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";// Ensure it's not null
            sendmortality m = new sendmortality();
            m.sendMortality( registernumber, doctorname,  hospitalname, hospitaladdress,Date,  deathofcause,placeofdeath);




                Intent intent = new Intent(mortalityn.this,homepage.class);
                startActivity(intent);
                finish();

        });

    }

    private void setupSpinner(Spinner spinner, String[] items) {  ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}