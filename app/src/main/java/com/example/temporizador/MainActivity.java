package com.example.temporizador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView Tarea;
    private CalendarView Calendario;
    private Button agregar;
    private int dia;
    private int mes;
    private int año;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tarea = (TextView) findViewById(R.id.tareaTexto);
        Calendario = (CalendarView) findViewById(R.id.cv_calend);
        final SharedPreferences fecha = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int D = getIntent().getIntExtra("dia", 0);
                int M = getIntent().getIntExtra("mes", 0);
                int Y = getIntent().getIntExtra("año", 0);
                if (year == Y && month == M && dayOfMonth == D) {
                    String task = fecha.getString("TareaTitle", "");
                    String DTask = fecha.getString("TareaDescripcion", "");
                    Tarea.setText(task);
                    Tarea.append(DTask);
                } else {
                    Tarea.setText("");
                }
            }
        });
    }

    public void Agendar(View view) {
        Calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dia = dayOfMonth;
                mes = month;
                año = year;
                SharedPreferences date = getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor obj_Editor = date.edit();
                obj_Editor.putInt("dia", dia);
                obj_Editor.putInt("mes", mes);
                obj_Editor.putInt("año", año);
                obj_Editor.commit();
            }
        });
        Intent i = new Intent(this, add_task.class);
        i.putExtra("Dia", dia);
        i.putExtra("Mes", mes);
        i.putExtra("Año", año);
        startActivity(i);
    }
}