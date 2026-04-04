package com.example.examinationprep;

import static android.text.TextUtils.isEmpty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText etName, etEmail, etPhone;

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

        btn = findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString();

                if (name.isEmpty() || email.isEmpty()) {
                    if (name.isEmpty()) {
                        etName.setError("Name is required");
                    }
                    if (email.isEmpty()) {
                        etEmail.setError("Email is required");
                    }
                    Toast.makeText(MainActivity.this, "Fill all fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Custom Toast Pattern
                    showMyCustomToast("Registration Successful!");
                }
            }

            // Helper method to keep code clean
            public void showMyCustomToast(String msg) {
                // Convert XML to View
                LayoutInflater li = getLayoutInflater();
                View layout = li.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));

                // Find the TextView inside that layout and set message
                TextView text = layout.findViewById(R.id.toast_text);
                text.setText(msg);

                // Create and show Toast
                Toast t = new Toast(getApplicationContext());
                t.setDuration(Toast.LENGTH_SHORT);
                t.setView(layout); // Putting my custom layout inside the toast.
                t.show();
            }
        });
    }
}