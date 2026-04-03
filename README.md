# MSBTE K-Scheme: Mobile Application Development (316006)

## Practical Examination Preparation System

This repository serves as a structured, hands-on preparation guide for the MSBTE K-Scheme Mobile Application Development (MAD) practical examination. It is designed specifically for students who are beginners in Java and Android development, focusing on high-frequency exam patterns and practical execution.

The preparation is built around an intensive 4-day crash plan, utilizing the Pomodoro technique (25 minutes of focused coding followed by a 5-minute break) to ensure maximum retention and efficiency.

---

## Repository Structure and Git Workflow

This project follows a non-linear branch-per-experiment structure. This allows each practical to exist in its own isolated environment for clean revision.

*   **main branch**: Contains the baseline, empty Android Studio project structure.
*   **exp-* branches**: Each branch represents a specific practical experiment.

### Branch Examples
*   `exp-01-hello`: Basic project structure and Hello World.
*   `exp-06-ui-selection`: Implementation of Checkboxes and RadioButtons.
*   `exp-08-09-form-validation`: Logic for input validation and error handling.
*   `exp-09-registration`: Full registration form implementation.
*   `exp-12-calculator`: Arithmetic logic and Grid/Linear layout implementation.
*   `exp-13-splash`: Splash screen implementation using Handler.
*   `exp-19-intent`: Navigation between activities using Explicit and Implicit Intents.
*   `exp-22-sqlite`: Database integration and CRUD operations.

**Note**: Branches are used as a versioning system for revision. Each branch isolates a single experiment. No merging or pull requests are used intentionally.

---

## 1. Course Learning Outcomes (COs)

The preparation system is aligned with the following official MSBTE learning outcomes:

*   **CO1**: Identify the components of the Android Operating System.
*   **CO2**: Set up the Android development environment and create basic applications.
*   **CO3**: Develop user interfaces using various layouts and UI components.
*   **CO4**: Implement data storage using SQLite databases.
*   **CO5**: Utilize advanced features such as sensors, permissions, and background services.

---

## 2. 4-Day Crash Roadmap

### Day 1: UI Foundations and Layouts
*   Mastering TextView, EditText, and Button.
*   Implementing selection components: Checkbox, RadioButton, and RadioGroup.
*   Designing registration forms using LinearLayout and ConstraintLayout.

### Day 2: Navigation and Application Logic
*   Creating a Splash Screen using the Handler class.
*   Switching between Activities using Explicit Intents.
*   Triggering system actions like Dialing a Number or Opening a URL (Implicit Intents).
*   Basic converter logic (Currency, Temperature, or Weight).

### Day 3: Database Integration (SQLite)
*   Extending the SQLiteOpenHelper class.
*   Implementing onCreate() for table schema and onUpgrade() for versioning.
*   Writing CRUD (Create, Read, Update, Delete) methods.
*   Connecting UI forms to the Database.

### Day 4: Advanced Features and Final Revision
*   Managing Services: Bluetooth and WiFi state toggling basics.
*   Understanding Broadcast Receivers for system events.
*   Implementing Camera access and SMS features.
*   Final mock practical of Tier 1 topics.

---

## 3. Minimal Code Pattern Library

### UI Connector Pattern
```java
// Always declare variables at the class level
Button submitBtn;
EditText nameInput;

// Initialize inside onCreate
submitBtn = findViewById(R.id.btn_submit);
nameInput = findViewById(R.id.et_name);
```

### Interaction Pattern
```java
submitBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name = nameInput.getText().toString().trim();
        Toast.makeText(MainActivity.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
    }
});
```

### Selection UI Pattern (Checkbox + RadioGroup)
```java
// RadioGroup handling
radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
    RadioButton rb = findViewById(checkedId);
    String selected = rb.getText().toString();
});

// Checkbox handling
if (checkBox.isChecked()) {
    // perform action
}
```

### Intent Pattern (Explicit and Implicit)
```java
// Explicit Intent
Intent intent = new Intent(CurrentActivity.this, TargetActivity.class);
startActivity(intent);

// Implicit Intent (Dialer)
Intent dialIntent = new Intent(Intent.ACTION_DIAL);
dialIntent.setData(Uri.parse("tel:123456789"));
startActivity(dialIntent);
```

### Form Validation Pattern
```java
if (nameInput.getText().toString().isEmpty()) {
    nameInput.setError("Name is required");
}
```

### SQLite Foundation Pattern
```java
public class MyDbHelper extends SQLiteOpenHelper {
    public MyDbHelper(Context context) {
        super(context, "UserDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
```

---

## 4. Viva Survival Kit

Just contains some questions that might be asked in the viva examination explore more questions on blogs and internet resources or ask AI chat-bots for better results!
1.  **What is an Activity?**
    A single screen with a user interface. It is the entry point for user interaction.
2.  **Difference between Explicit and Implicit Intents?**
    Explicit targets a specific class/activity. Implicit requests the system to find a component to handle an action.
3.  **What is the role of AndroidManifest.xml?**
    It contains essential information about the app: package name, components (activities, services), and required permissions.
4.  **What are the Activity Lifecycle stages?**
    onCreate(), onStart(), onResume(), onPause(), onStop(), onDestroy(), onRestart().
5.  **What is SQLite?**
    A lightweight, serverless relational database engine embedded into Android.
6.  **Linear Layout vs. Relative Layout vs. Constraint Layout?**
    Linear organizes elements in a single row or column. Relative organizes elements in relation to each other. Constraint allows for complex layouts with a flat view hierarchy.
7.  **What is layout_gravity vs gravity?**
    'gravity' controls content inside the view; 'layout_gravity' controls the view's position within its parent.

---

## 5. Smart Priority Strategy

### Tier 1: High Probability (Must-Do)
*   UI Layouts (Linear, Relative, Constraint).
*   Registration Forms + Validation.
*   Intents (Explicit/Implicit).
*   SQLite CRUD Operations.

### Tier 2: Medium Probability (Important)
*   Splash Screens.
*   Calculator / Converter Apps.
*   Multi-activity Apps.

### Tier 3: Low Probability (Secondary)
*   Advanced hardware features (Bluetooth, WiFi, SMS).

---

## 6. Git Workflow for Exam Prep

Creating a new experiment branch:
```bash
git checkout -b exp-xx-topic-name
git push --set-upstream origin exp-xx-topic-name
```

Switching back to a clean state for a new experiment:
```bash
git checkout main
```

The `--set-upstream` flag ensures your local branch is linked to the remote repository.

---

## 7. Common Beginner Mistakes

*   **Wrong IDs**: Using the wrong ID in `findViewById` that doesn't match the XML.
*   **Missing toString()**: Trying to use `getText()` directly without converting to a String.
*   **Not using trim()**: Forgetting to remove whitespace from user input.
*   **Missing Manifest Declarations**: Forgetting to register new Activities in the manifest.
*   **Missing Permissions**: Forgetting to add permissions for Internet or Hardware access.

---

## 8. Exam Hall Strategy

### First 5 Minutes
1.  Read the question carefully.
2.  Identify the core pattern: UI, Intent, or Database.
3.  Plan the layout structure first.

### Debugging
1.  Check IDs in Java and XML.
2.  Review the AndroidManifest.xml for missing activities or permissions.
3.  Check Logcat for specific error messages.

---

## 9. Resources and Tools

*   **Campusify**: Solved manuals and microprojects.
*   **Abhi Android**: UI component tutorials.
*   **Simplified Coding**: Best for SQLite tutorials.
*   **Stack Overflow**: Debugging and code troubleshooting.


