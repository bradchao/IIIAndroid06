package tw.org.iii.iiiandroid06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView clock;
    private ListView lapList;
    private boolean isRunning;
    private Button btnLeft, btnRight;

    private Timer timer;
    private int i;
    private UIHandler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clock);
        lapList = findViewById(R.id.lapList);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);

        btnLeft.setText(isRunning?"Lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");

        Log.v("brad", "start");
        uiHandler = new UIHandler();
        timer = new Timer();
        timer.schedule(new MyTask(), 0, 10);

    }

    @Override
    public void finish() {
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        super.finish();
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            if (isRunning) {
                i++;
                uiHandler.sendEmptyMessage(0);
            }
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            clock.setText(toClockString());
        }
    }

    private String toClockString(){

        return "10:20:30.12";
    }

    public void clickLeft(View view) {
        if (isRunning){
            // Lap
            doLap();
        }else{
            // Resset
            doReset();
        }
    }

    private void doLap(){

    }

    private void doReset(){
        
    }

    public void clickRight(View view) {
        isRunning = !isRunning;
        btnLeft.setText(isRunning?"Lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");


    }
}
