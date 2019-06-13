package ir.greencode.cornometer.ui.Main.timer;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.greencode.cornometer.R;
import ir.greencode.cornometer.base.BaseActivity;

public class TimerActivity extends BaseActivity {

    Timer timer;
    @BindView(R.id.hour)
    TextView hour;

    boolean isStart = false;
    @BindView(R.id.minute)
    TextView minute;

    @BindView(R.id.seconds)
    TextView seconds;


    @BindView(R.id.startButton)
    ImageButton startButton;

    @BindView(R.id.milli_second)
    TextView milliSecond;


    long timeInMillies = 0L;
    long timeSwap = 0L;
    long finalTime = 0L;
    private long startTime = 0L;
    private Handler myHandler = new Handler();


    @Override
    protected int getContentView() {
        return R.layout.activity_timer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.startButton)
    public void onViewClicked() {
        if(!isStart) {
            isStart=true;
            startButton.setImageDrawable(getDrawable(R.drawable.ic_pause));
            startTime = SystemClock.uptimeMillis();
            myHandler.postDelayed(updateTimerMethod, 0);

        }else {
            startButton.setImageDrawable(getDrawable(R.drawable.ic_play));
            isStart = false;
            timeSwap += timeInMillies;
            myHandler.removeCallbacks(updateTimerMethod);
        }
    }

    private Runnable updateTimerMethod = new Runnable() {

        public void run() {
            timeInMillies = SystemClock.uptimeMillis() - startTime;
            finalTime = timeSwap + timeInMillies;
            int sec = (int) (finalTime / 1000);
            int hourse = (int) (sec/3600);
            sec = sec%3600;
            int minutes = sec / 60;
            sec = sec % 60;
            int milliseconds = (int) (finalTime % 1000);
            hour.setText(hourse>9?""+hourse:"0"+hourse);
            minute.setText(minutes>9?""+minutes:"0"+minutes);
            seconds.setText(sec>9?""+sec:"0"+sec);
            myHandler.postDelayed(this, 0);
        }

    };


}

