# MSBTE K-Scheme: Mobile Application Development (316006)

This repository provides a structured approach to mastering Mobile Application Development (MAD) using Java and Android Studio. The project is designed to follow standard development practices, focusing on modularity, core Android components, and data persistence.

---

## Project Overview

This repository serves as a comprehensive technical guide for the MSBTE K-Scheme curriculum. It covers the transition from basic user interface design to complex system integrations, including SQLite databases and hardware communication.

### Technical Stack
* Language: Java (JDK 17+)
* IDE: Android Studio
* Build System: Gradle
* Database: SQLite
* Architecture: MVC / Single Activity Pattern

---

## Modular Structure (Git Branches)

The project uses a branch-per-feature strategy to maintain code isolation and clean version history.

| Branch Name | Feature / Experiment | Key Components |
| :--- | :--- | :--- |
| main | Base Project | Project scaffolding and Gradle configuration |
| feat-ui-basics | Layouts & Widgets | ConstraintLayout, Material Components, Input Handling |
| feat-navigation | Intent System | Activity transitions and Data Bundles |
| feat-storage | SQLite Persistence | SQLiteOpenHelper and CRUD implementations |
| feat-multimedia | Camera & Video | MediaStore API and Runtime Permissions |
| feat-comms | SMS Utility | SmsManager and BroadcastReceiver integration |

---

## Core Implementation Concepts

### 1. UI/UX Development
Focuses on building responsive interfaces using flat view hierarchies for performance optimization.
* Patterns: View Binding and centralized resource management (strings.xml, dimens.xml).
* Layouts: Implementation of LinearLayout for sequential stacks and ConstraintLayout for complex positioning.

### 2. Android Lifecycle Management
Detailed implementation of Activity and Fragment lifecycles to handle system-initiated state changes.
* Primary Hooks: onCreate, onResume, onPause, and onDestroy.

### 3. Data Persistence
Implementation of local relational storage for offline-first application logic.
* Schema Design: Definition of normalized table structures.
* Data Access: Type-safe methods for Create, Read, Update, and Delete operations.

### 4. Hardware Integration
Interaction with device hardware through standardized system APIs.
* Permissions: Implementation of the Android 6.0+ runtime permission model.
* System Services: Use of SmsManager and Camera Intents for external hardware access.

---

## Implementation Standards

### View Initialization
Proper use of lifecycle hooks to ensure UI components are bound before interaction.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initializeViews();
}
```

### Input Validation
Ensuring data integrity through server-side and client-side validation logic.

```java
if (TextUtils.isEmpty(input)) {
    editText.setError("Required field");
    return;
}
```

### Security Compliance
Adhering to modern API requirements for PendingIntents in Android 12 and above.

```java
PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
```

---

## Setup and Installation

1. Clone the repository:
   ```
   https://github.com/Tanishq747Shivasharan/Mobile-Application-Development-CM6K.git
   ```

2. Open the project in Android Studio:
   File > Open > [Project Directory]

3. Sync Gradle dependencies:
   Ensure a stable internet connection for the initial build.

4. Execute the application:
   Deploy to a connected physical device or AVD (Shift + F10).

---

## Learning Outcomes

* CO1: Understand Android system architecture and component interactions.
* CO2: Configure and maintain the Android development environment.
* CO3: Design modular and responsive user interfaces.
* CO4: Implement persistent storage solutions using SQLite.
* CO5: Integrate system services and hardware-level permissions.

---

## Contributing
This is an educational resource. Contributions to documentation or code samples can be submitted via Pull Requests.

---

## License
Distributed under the MIT License.
