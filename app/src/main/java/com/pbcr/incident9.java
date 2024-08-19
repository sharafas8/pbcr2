package com.pbcr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class incident9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident9);
        Button btnback8 =(Button) findViewById(R.id.mornext1);
        btnback8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(incident9.this, incident8.class);
                startActivity(intent);
                finish();
            }

        });
        Button btnnext1 = (Button) findViewById(R.id.btnnext8);
        btnnext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Intent intent = new Intent(incident9.this,incident10.class);
                startActivity(intent);
                finish();
            }
        });
    }
}