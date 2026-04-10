package com.example.examinationprep;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etCel;
    Button btn;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etCel = findViewById(R.id.etC);
        btn = findViewById(R.id.convertF);
        tvResult = findViewById(R.id.tvRes);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etC = etCel.getText().toString().trim();

                if (etC.isEmpty()) {
                    etCel.setError("Enter Celsius");
                }
                float c = Float.parseFloat(etC);
                float res;
                    res  = (c * 9 / 5) + 32;
                tvResult.setText("Fahrenheit: " + res);
            }
        });
    }
}