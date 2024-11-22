package com.pbcr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class followup extends AppCompatActivity {
    private static final String TAG = "followup";
    private EditText date, date1, number;
    private DatePickerDialog datePickerDialog, datePickerDialog1;
    private RadioGroup r;
    private Spinner sp1, sp2;
    private final String[] status = {"Select Item", "NED ", "Remission ", "Disease Status Unchanged", "Residual Dis ", "Regression", "Recurrence", "Met", "Rec+Met", "Progression", "Advanced", "Died with Dis", "Died due to other Causes", "Dies Status Unknown", "Second Primary", "Unknown"};
    private final String[] method = {"Select Item", "MCC Visit", "House Visit", "By Post", "Through Telephone", "Death Records", "PHC/Anganwadi", "Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followup);

        r = findViewById(R.id.passive);
        number = findViewById(R.id.Number);
        date = findViewById(R.id.d1);
        date1 = findViewById(R.id.d2);
        sp1 = findViewById(R.id.spn1);
        setupSpinner(sp1, status);// Replace with actual spinner IDs
        sp2 = findViewById(R.id.spn2);
        setupSpinner(sp2, method);
        date.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(followup.this, (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                date.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);
            }, year, month, dayofmonth);
            datePickerDialog.show();
        });

        date1.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog1 = new DatePickerDialog(followup.this, (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                date1.setText(selectedDayOfMonth + "-" + (selectedMonth + 1) + "-" + selectedYear);
            }, year, month, dayofmonth);
            datePickerDialog1.show();
        });

        Button btnback3 = findViewById(R.id.Flwback);
        btnback3.setOnClickListener(v -> {
            Intent intent = new Intent(followup.this, homepage.class);
            startActivity(intent);
            finish();
        });

        Button btnnext2 = findViewById(R.id.Flwsub);
        btnnext2.setOnClickListener(v -> {
            // Fetch input data from UI components
            String datefollowup = date.getText().toString();
            String followupno = number.getText().toString();
            int selectedId = r.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);

            String status = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";
            String causeofstatus = sp1.getSelectedItem().toString();
            String methods = sp2.getSelectedItem().toString();
            String nextdate = date1.getText().toString();

            Log.d(TAG, "Sending data: " + datefollowup + ", " + followupno + ", " + status + ",  " + nextdate + "," + causeofstatus + "," + methods);

            followdata fl = new followdata();
            fl.senddata(datefollowup, followupno, status, methods, causeofstatus, nextdate);

            Intent intent = new Intent(followup.this, homepage.class);
            startActivity(intent);
            finish();
        });
    }


    private void setupSpinner(Spinner sp1, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
    }
}
