package com.example.examinationprep;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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

    MyDbHelper dbHelper;
    EditText etN, etC, etId;
    Button btn, btnUpdate, btnDelete;
    TextView resultView;

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

        etN = findViewById(R.id.etName);
        etC = findViewById(R.id.etCourse);
        btn = findViewById(R.id.btnSubmit);
        resultView = findViewById(R.id.tvData);
        etId = findViewById(R.id.etId);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        dbHelper = new MyDbHelper(this);
        showData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String name = etN.getText().toString().trim();
                String course = etC.getText().toString().trim();
                boolean isValid = true;

                if (name.isEmpty()) {
                    etN.setError("Name is required");
                    isValid = false;
                }

                if (course.isEmpty()) {
                    etC.setError("Course is required");
                    isValid = false;
                }

                if (!isValid) {
                    return;
                }

                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("course",course);

                long res = db.insert("students", null, values);

                db.close();

                if (res != -1) {
                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    etN.setText("");
                    etC.setText("");
                    showData();
                }
                else {
                    Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idStr = etId.getText().toString().trim();
                String name = etN.getText().toString().trim();
                String course = etC.getText().toString().trim();

                boolean isValid = true;

                if (idStr.isEmpty()) {
                    etId.setError("ID required");
                    isValid = false;
                }

                if (name.isEmpty()) {
                    etN.setError("Name required");
                    isValid = false;
                }

                if (course.isEmpty()) {
                    etC.setError("Course required");
                    isValid = false;
                }

                if (!isValid) return;

                int id = Integer.parseInt(idStr);

                updateStudent(id, name, course);
                showData();

                etId.setText("");
                etN.setText("");
                etC.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idStr = etId.getText().toString().trim();

                if (idStr.isEmpty()) {
                    etId.setError("ID required");
                    return;
                }

                int id = Integer.parseInt(idStr);

                deleteStudent(id);
                showData();

                etId.setText("");
            }
        });

    }
    public void showData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM students", null);
        int idxName = c.getColumnIndex("name");
        int idxCourse = c.getColumnIndex("course");

        if (idxName == -1) {
            Toast.makeText(this, "Column 'name' not found in database", Toast.LENGTH_SHORT).show();
            c.close();
            db.close();
            return;
        } else if (idxCourse == -1) {
            Toast.makeText(this, "Column 'course' not found in database", Toast.LENGTH_SHORT).show();
            c.close();
            db.close();
        }
        if (c.moveToFirst()) {
            StringBuilder b = new StringBuilder();
            do {
                String name = c.getString(idxName);
                String course = c.getString(idxCourse);

                b.append("Name: ").append(name).append("    Course: ").append(course).append("\n\n");
            } while(c.moveToNext());

            resultView.setText(b.toString());
        }
        else {
            resultView.setText("No records found.");
        }
        c.close();
        db.close();
    }

    public void updateStudent(int id, String newName, String newCourse) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put("name", newName);
        v.put("course", newCourse);

        int rowsAffected = db.update(
                MyDbHelper.TABLE_NAME,
                v,
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        if (rowsAffected > 0) {
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsDeleted = db.delete(MyDbHelper.TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});

        db.close();

        if (rowsDeleted > 0) {
            Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        }
    }

}