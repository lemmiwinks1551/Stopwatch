package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Stopwatch extends AppCompatActivity {
    int seconds = 0;
    boolean running = false;
    boolean wasRunning = false;
    final String TAG = "Stopwatch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Активность запускается, выполняется ее метод onCreate().
         * Выполняется код инициализации активности из метода onCreate().
         * В этой точке активность еще не видна, так как метод onStart() еще не вызывался */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onStop() {
        /**
         * Метод onStop() выполняется, когда активность перестает быть видимой пользователю.
         * После выполнения метода onStop() активность не видна на экране */
        Log.d(TAG, "onStop");
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        /**
         * Метод onResume() выполняется когда активность собирается перейти на передний план.
         После выполнения метода onResume() активность обладает фокусом, а пользователь может взаимодействовать с ней.*/
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        /**
         * onPause() выполняется когда активность перестает находиться на переднем плане */
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        /**
         * Метод onSaveInstanceState() вызывается перед уничтожением активности;
         * это означает, что вам представится возможность сохранить все значения,
         * которые нужно сохранить, прежде чем они будут безвозвратно потеряны.
         * Метод onSaveInstanceState() получает один параметр типа Bundle. Тип
         * Bundle позволяет объединить разные типы данных в один объект */
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    // Запуск секундомера
    public void onClickStart(View view) {
        Log.d(TAG, "onClickStart");
        running = true;
    }

    // Остановка секундомера
    public void onClickStop(View view) {
        Log.d(TAG, "onClickStop");
        running = false;
    }

    // Сброс
    public void onClickReset(View view) {
        Log.d(TAG, "onClickReset");
        running = false;
        seconds = 0;
    }

    // Запуск таймера
    public void runTimer() {
        final TextView timeView = findViewById(R.id.time_view);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
