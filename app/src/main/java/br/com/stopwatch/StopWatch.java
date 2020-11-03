package br.com.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopWatch extends Activity {

    private int seconds;
    private boolean running;
    private boolean wasrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }

        runTimer(); // inicia a execução quando a atividade for criada.

    } // fim do método onCreate


    private void runTimer() {

        final TextView timeView =  findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int sec = seconds%60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes,sec);

                timeView.setText(time);

                if(running) {
                    seconds++;
                }

                handler.postDelayed(this,1000);

            } // fim do método run()

        }); // fim do método post() do objeto handler

    } // fim do método runTimer

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasrunning", wasrunning);
    }

    protected void onPause() {

        super.onPause();
        wasrunning = running;
        running = false;
    }

    protected  void onResume() {
        super.onResume();
        if(wasrunning) {
            running = true;
        }
    }
    public void onClickStart(View view) {
        running = true;

    } // fim do método onClickStart

    public void onClickStop(View view) {
        running = false;

    } // fim do método onClickStop

    public void onClickReset(View view) {
        running = false;
        seconds = 0;

    } // fim do método onClickReset

} // fim da classe StopWatch