package com.antsoftware.pruebatimer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView time, time1, date;
    Button start, reset, exit;
    Timer timer = null;
    int tickCount, tickCount1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = (TextView) findViewById(R.id.tvDate);
        time1 = (TextView) findViewById(R.id.tvSecondTask);
        time = (TextView) findViewById(R.id.tvTime);
        start = (Button) findViewById(R.id.btnStart);
        reset = (Button) findViewById(R.id.btnReset);
        exit = (Button) findViewById(R.id.btnExit);

        date.setText(dateTime());

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleTimer();
                tickCount = 0;
                tickCount1 = 0;
                time.setText("");
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleTimer();
            }
        });
    }

    public void scheduleTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        TimerTask myTask1 = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String newNumber = Integer.toString(++tickCount);
                        time.setText(newNumber);
                    }
                });
            }
        };
        TimerTask myTask2 = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String newNumber = Integer.toString(++tickCount1);
                        time1.setText(newNumber);
                    }
                });
            }
        };
        timer.schedule(myTask1, 0, 1000);
        timer.schedule(myTask2, 0, 500);
        
    }

    public String dateTime() {
        DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        return df.format(new Date());
    }
}