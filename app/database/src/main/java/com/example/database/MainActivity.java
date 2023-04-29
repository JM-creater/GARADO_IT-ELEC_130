package com.example.database;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new instance of the database helper
        dbHelper = new MyDatabaseHelper(this);

        // Delete all data
        dbHelper.deleteAllData();

        // Insert data
        dbHelper.insertData("Joseph", 24);
        dbHelper.insertData("Martin", 22);

        // Get data
        getData();

        // Update data
        dbHelper.updateData(2, "Mark", 26);

        // Get data
        getData();

        // Delete Data
        dbHelper.deleteData(2);

        // Get data
        getData();


        // Shared Preferences
        // Get the shared preferences object
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);

        // Save values to shared preferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", "Martin");
        editor.putInt("age", 26);
        editor.putBoolean("is_student", true);
        editor.commit();

        // Get values from shared preferences
        String name = prefs.getString("name", "");
        int age = prefs.getInt("age", 0);
        boolean isStudent = prefs.getBoolean("is_student", false);
        Log.d("MainActivity", name);
        Log.d("MainActivity", String.valueOf(age));
        Log.d("MainActivity", String.valueOf(isStudent));

        // Remove a value
        editor.remove("name");
        editor.apply();

        // Get values from shared preferences
        name = prefs.getString("name", "");
        age = prefs.getInt("age", 0);
        isStudent = prefs.getBoolean("is_student", false);
        Log.d("MainActivity", name);
        Log.d("MainActivity", String.valueOf(age));
        Log.d("MainActivity", String.valueOf(isStudent));

        // Clear shared preferences
        editor.clear();
        editor.commit();

        // Get values from shared preferences
        name = prefs.getString("name", "");
        age = prefs.getInt("age", 0);
        isStudent = prefs.getBoolean("is_student", false);
        Log.d("MainActivity", name);
        Log.d("MainActivity", String.valueOf(age));
        Log.d("MainActivity", String.valueOf(isStudent));
    }

    @SuppressLint("Range")
    private void getData() {
        Cursor cursor = dbHelper.getData();

        Log.d("MainActivity", "========= START =========");
        if (cursor.getCount() > 0) {
            // Loop through the cursor to retrieve the data
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));

                Log.d("MainActivity", "Record retrieved with ID: " + id + ", name: " + name + ", age: " + age);
            }
        } else {
            Log.d("MainActivity", "No records found.");
        }

        Log.d("MainActivity", "========= END =========");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database helper
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}