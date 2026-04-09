package com.example.examinationprep;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    EditText etName, etEmail, etPhone;
    TextView tvData;
    int selectedId = -1;

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

        db = new DBHelper(this);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        tvData = findViewById(R.id.tvData);

        findViewById(R.id.btnInsert).setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            long r = db.insertUser(name, email, phone);
            Toast.makeText(this, r != -1 ? "Inserted!" : "Error", Toast.LENGTH_SHORT).show();
            clearFields();
            showAll();
        });

        findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            if (selectedId == -1) {
                Toast.makeText(this, "Enter ID to update (or tap Show Data first)", Toast.LENGTH_SHORT).show();
                return;
            }
            db.updateUser(selectedId,
                    etName.getText().toString(),
                    etEmail.getText().toString(),
                    etPhone.getText().toString());
            Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
            clearFields();
            showAll();
        });

        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            if (selectedId == -1) {
                Toast.makeText(this, "Enter ID to delete", Toast.LENGTH_SHORT).show();
                return;
            }
            db.deleteUser(selectedId);
            Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
            selectedId = -1;
            clearFields();
            showAll();
        });

        findViewById(R.id.btnShow).setOnClickListener(v -> showAll());
    }

    private void showAll() {
        Cursor cursor = db.getAllUsers();
        if (cursor.getCount() == 0) {
            tvData.setText("No data found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ID | Name | Email | Phone\n");
        sb.append("--------------------------\n");
        while (cursor.moveToNext()) {
            sb.append(cursor.getInt(0)).append(" | ")
                    .append(cursor.getString(1)).append(" | ")
                    .append(cursor.getString(2)).append(" | ")
                    .append(cursor.getString(3)).append("\n");
        }
        cursor.close();
        tvData.setText(sb.toString());
    }

    private void clearFields() {
        etName.setText("");
        etEmail.setText("");
        etPhone.setText("");
        selectedId = -1;
    }
}
