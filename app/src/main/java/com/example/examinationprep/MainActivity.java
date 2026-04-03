package com.example.examinationprep;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    RadioGroup rgGen;
    CheckBox cb1, cb2, cb3, cb4;
    TextView tvRes;

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

        btn = findViewById(R.id.btnSubmit);
        rgGen = findViewById(R.id.rgGender);
        cb1 = findViewById(R.id.cbTWD);
        cb2 = findViewById(R.id.cbTBB);
        cb3 = findViewById(R.id.cbFri);
        cb4 = findViewById(R.id.cbST);
        tvRes = findViewById(R.id.tvResult);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = "You are not the only one who Loved! The series\n ";
                if(cb1.isChecked()) selected += "The Walking Dead\n ";
                if(cb2.isChecked()) selected += "The Braking Bad\n ";
                if(cb3.isChecked()) selected += "Friends\n ";
                if(cb4.isChecked()) selected += "Stranger Things\n ";

                int SelectedID = rgGen.getCheckedRadioButtonId();
                RadioButton rb = findViewById(SelectedID);

                if(rb != null) {
                    selected += "\nMany " + rb.getText() + "s have got similar results!";
                }

                tvRes.setText(selected);
            }
        });

    }
}