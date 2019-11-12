package tw.org.iii.iiiandroid06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        timer = new Timer();
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            i++;
            Log.v("brad", "i = " + i);
            clock.setText("" + i);
        }
    }


    public void clickLeft(View view) {


    }
    public void clickRight(View view) {
        isRunning = !isRunning;
        btnLeft.setText(isRunning?"Lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");

        timer.schedule(new MyTask(), 0, 10);

    }
}
