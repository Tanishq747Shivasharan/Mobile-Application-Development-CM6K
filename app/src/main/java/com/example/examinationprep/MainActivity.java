package com.example.examinationprep;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String[] titles = {"Android Dev", "Clean Code", "Java Basics", "Data Structures", "Python Guide", "Database 101"};
    String[] authors = {"Google", "Robert C. Martin", "Herbert Schildt", "Cormen et al.", "Guido van Rossum", "Elmasri"};
    int[] covers = {R.drawable.book1, R.drawable.book2, R.drawable.book3, R.drawable.book4, R.drawable.book5, R.drawable.book6};
    GridView gv;

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

        gv = findViewById(R.id.gridView);
        BookAdapter ba = new BookAdapter(this, titles, authors, covers);
        gv.setAdapter(ba);
        gv.setOnItemClickListener((adapterView, view, i, l) ->
                Toast.makeText(this, "Selected: " + titles[i], Toast.LENGTH_SHORT).show());
    }
}