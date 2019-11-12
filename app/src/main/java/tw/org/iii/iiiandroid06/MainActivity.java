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
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
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

    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from = {"lapItem"};
    private int[] to = {R.id.lapItem};

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

        initListView();
    }

    private void initListView(){
        data = new LinkedList<>();
        adapter = new SimpleAdapter(
                this, data, R.layout.item, from, to);
        lapList.setAdapter(adapter);
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
        int hs = i % 100;
        int ts = i / 100;
        int hh = ts / (60*60);
        int mm = (ts - hh*60*60)/60;
        int ss = ts % 60;
        return hh + ":" + mm + ":" + ss + "." + hs;

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
        i = 0;
        uiHandler.sendEmptyMessage(0);
    }

    public void clickRight(View view) {
        isRunning = !isRunning;
        btnLeft.setText(isRunning?"Lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");


    }
}
