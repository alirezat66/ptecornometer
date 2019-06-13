package ir.greencode.cornometer.ui.Main.twostep;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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

public class TwoStepActivity extends BaseActivity {
    @BindView(R.id.timerProgramCountdown)
    CountDownClock timerProgramCountdown;
    @BindView(R.id.cv_countdownViewTest)
    CountdownView cvCountdownViewTest;
    @BindView(R.id.edt_wait_time)
    EditText edtWaitTime;
    @BindView(R.id.edt_time)
    EditText edtTime;
    @BindView(R.id.btn_start)
    Button btnStart;
    boolean isStart = false;
    boolean isInsecond = false;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    long time = 0;
    long time2 = 0;
    @Override
    protected int getContentView() {
        return R.layout.activity_two_step;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        timerProgramCountdown.setCountdownListener(new CountDownClock.CountdownCallBack() {
            @Override
            public void countdownAboutToFinish() {
                mediaPlayer = MediaPlayer.create(TwoStepActivity.this, R.raw.ticktock);
                mediaPlayer.start();

            }

            @Override
            public void countdownFinished() {


                if(isInsecond){
                    btnStart.setText("Start");
                    isInsecond = true;
                    mediaPlayer2.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stopPlayer();
                        }
                    },1000);

                }else {
                    isInsecond = true;
                    mediaPlayer.stop();
                    mediaPlayer2 = MediaPlayer.create(TwoStepActivity.this, R.raw.beep);
                    mediaPlayer2.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startSecond();
                        }
                    },1000);


                }

            }
        });
    }

    private void startSecond() {
        timerProgramCountdown.startCountDown(time2*1000);
        cvCountdownViewTest.start(time2*1000);
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        if (!isStart) {
            startTimer();
        }else
            {
            stopTimer();
        }
    }


    private void stopTimer() {

        cvCountdownViewTest.stop();
        edtTime.setEnabled(true);
        edtTime.setFocusable(true);
        edtWaitTime.setEnabled(true);
        edtWaitTime.setFocusable(true);
        btnStart.setText("Start");
        isStart = false;
    }

    private void startTimer() {
        stopPlayer();

            try{
                time = Long.parseLong(edtWaitTime.getText().toString());
                time2 = Long.parseLong(edtTime.getText().toString());
            }catch (NumberFormatException ex){
                ex.printStackTrace();
                Toast.makeText(this, "please set time", Toast.LENGTH_SHORT).show();
                return;
            }
        isStart = true;
        isInsecond = false;
        btnStart.setText("Stop");
        timerProgramCountdown.startCountDown(time*1000);
        cvCountdownViewTest.start(time*1000);
        edtTime.setEnabled(false);
        edtTime.setFocusable(false);
        edtWaitTime.setEnabled(false);
        edtWaitTime.setFocusable(false);
        }







    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        if(mediaPlayer2!=null){
            mediaPlayer2.stop();
            mediaPlayer2.reset();
        }
    }
}
