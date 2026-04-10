package com.example.todoapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPrefManager {

    private static final String PREF_NAME = "todo_pref";
    private static final String KEY_TASKS = "tasks";

    public static void saveTasks(Context context, ArrayList<Task> tasks) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(tasks);

        editor.putString(KEY_TASKS, json);
        editor.apply();
    }

    public static ArrayList<Task> getTasks(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_TASKS, null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();

        return json == null ? new ArrayList<>() : gson.fromJson(json, type);
    }
}