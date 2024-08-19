package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class incident2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident2);
        Button btnback1 =(Button) findViewById(R.id.btnback1);
        btnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident2.this, incident1.class);
                startActivity(intent);
                finish();
            }

        });
        Button btnnext1 = (Button) findViewById(R.id.btnnext1);
        btnnext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident2.this,incident3.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
