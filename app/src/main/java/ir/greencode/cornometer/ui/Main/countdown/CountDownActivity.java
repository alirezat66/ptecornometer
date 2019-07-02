package ir.greencode.cornometer.ui.Main.countdown;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asp.fliptimerviewlibrary.CountDownClock;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import ir.greencode.cornometer.R;
import ir.greencode.cornometer.base.BaseActivity;

public class CountDownActivity extends BaseActivity {


    float prog = 0;


    @BindView(R.id.timerProgramCountdown)
    CountDownClock timerProgramCountdown;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    @BindView(R.id.cv_countdownViewTest)
    CountdownView cvCountdownViewTest;
    @BindView(R.id.btn_two_min)
    Button btnTwoMin;
    @BindView(R.id.btn_one_min)
    Button btnOneMin;
    @BindView(R.id.btn_thirty_sec)
    Button btnThirtySec;
    @BindView(R.id.edt_time)
    EditText edtTime;
    @BindView(R.id.btn_start)
    Button btnStart;

    boolean isStart = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_count_down_timer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        timerProgramCountdown.setCountdownListener(new CountDownClock.CountdownCallBack() {
            @Override
            public void countdownAboutToFinish() {
                mediaPlayer.start();

            }

            @Override
            public void countdownFinished() {
                mediaPlayer.stop();
               mediaPlayer2.start();
               btnStart.setText("Start");
            }
        });


    }

    @OnClick({R.id.btn_two_min, R.id.btn_one_min, R.id.btn_thirty_sec, R.id.btn_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_two_min:
                startTimer(120);
                break;
            case R.id.btn_one_min:
                startTimer(60);
                break;
            case R.id.btn_thirty_sec:
                startTimer(30);
                break;
            case R.id.btn_start:
                if (!isStart) {
                    startTimer(0);
                }else {
                    stopTimer();
                }
                break;
        }
    }

    private void stopTimer() {
        timerProgramCountdown.pauseCountDownTimer();
        timerProgramCountdown.startCountDown(0);
        cvCountdownViewTest.stop();
    }

    private void startTimer(long time) {
        stopPlayer();
        makeMedia();
        if(time==0){
           try{
               time = Long.parseLong(edtTime.getText().toString());
           }catch (NumberFormatException ex){
               ex.printStackTrace();
               Toast.makeText(this, "please set time", Toast.LENGTH_SHORT).show();
               return;
           }
        }
        if(isStart){
            Toast.makeText(this, "please stop it first", Toast.LENGTH_SHORT).show();
            return;
        }
        isStart = true;
        btnStart.setText("Stop");
        timerProgramCountdown.startCountDown(time*1000);
        cvCountdownViewTest.start(time*1000);

    }

    private void makeMedia() {
        mediaPlayer = MediaPlayer.create(this, R.raw.ticktock);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.beep);
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if(mediaPlayer2!=null){
            mediaPlayer2.stop();
            mediaPlayer2.release();
        }
    }
}
