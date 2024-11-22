package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button incident =(Button) findViewById(R.id.incident);
        incident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,incident1.class );
                startActivity(intent);
                finish();
            }
        });
        Button mortality =(Button) findViewById(R.id.mortality);
        mortality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,mortality1.class );
                startActivity(intent);
                finish();
            }
        });
        Button followup =(Button) findViewById(R.id.follow);
        followup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, followup.class );
                startActivity(intent);
                finish();
            }
        });


    }
}