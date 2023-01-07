package com.example.temporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.prefs.Preferences;

public class add_task extends AppCompatActivity {
    private EditText title, task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        title = (EditText) findViewById(R.id.tarea);
        task = (EditText) findViewById(R.id.descripcion);
    }

    public void cancelar(View view) {
        Intent C = new Intent(this, MainActivity.class);
        startActivity(C);
    }
    public void SendTask(View view) {
        SharedPreferences data = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_Editor = data.edit();
        int dayWeek = getIntent().getIntExtra("Dia", 0);
        int monthYear = getIntent().getIntExtra("Mes", 0);
        int yearFull = getIntent().getIntExtra("Año", 0);
        String Tarea = title.getText().toString();
        String DescripcionTarea = task.getText().toString();
        obj_Editor.putString("tareaTitle", Tarea);
        obj_Editor.putString("tareaDescription", DescripcionTarea);
        Intent C = new Intent(this, MainActivity.class);
        C.putExtra("DDay",dayWeek);
        C.putExtra("MMonth",monthYear);
        C.putExtra("YYear",yearFull);
        startActivity(C);
    }


}