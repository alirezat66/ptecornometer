package ir.greencode.cornometer.ui.Main;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asp.fliptimerviewlibrary.CountDownClock;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import ir.greencode.cornometer.R;
import ir.greencode.cornometer.base.BaseActivity;
import ir.greencode.cornometer.ui.Main.twostep.TwoStepActivity;

public class SavedActivity extends BaseActivity {
    @BindView(R.id.timerProgramCountdown)
    CountDownClock timerProgramCountdown;
    @BindView(R.id.cv_countdownViewTest)
    CountdownView cvCountdownViewTest;
    @BindView(R.id.chosenTitle)
    TextView chosenTitle;
    @BindView(R.id.btn_start)
    Button btnStart;

    int type;
    boolean isTwoTime;
    String title;
    long waitingTime;
    long time;

    boolean isStart = false;
    boolean isInsecond = false;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    @Override
    protected int getContentView() {
        return R.layout.activity_for_saved_timer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            type = bundle.getInt("type");
            title = bundle.getString("title");
            waitingTime = bundle.getLong("waitingTime");
            time = bundle.getLong("time");
            chosenTitle.setText(title);
        }

        timerProgramCountdown.setCountdownListener(new CountDownClock.CountdownCallBack() {
            @Override
            public void countdownAboutToFinish() {
                mediaPlayer = MediaPlayer.create(SavedActivity.this, R.raw.ticktock);
                mediaPlayer.start();
            }

            @Override
            public void countdownFinished() {

                if(type==1){
                    mediaPlayer2 = MediaPlayer.create(SavedActivity.this, R.raw.beep);
                    mediaPlayer2.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stopPlayer();
                            stopTimer();
                        }
                    },1000);
                }else {
                    if(isInsecond){
                        btnStart.setText("Start");
                        isInsecond = true;
                        mediaPlayer2 = MediaPlayer.create(SavedActivity.this, R.raw.beep);
                        mediaPlayer2.start();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                stopPlayer();
                                stopTimer();
                            }
                        },1000);

                    }else {
                        isInsecond = true;
                        mediaPlayer.stop();
                        mediaPlayer2 = MediaPlayer.create(SavedActivity.this, R.raw.beep);
                        mediaPlayer2.start();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startSecond();
                            }
                        },1000);


                    }
                }



            }
        });

    }

    private void startSecond() {
        timerProgramCountdown.startCountDown(time);
        cvCountdownViewTest.start(time);
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        startTimer();
    }
    private void startTimer() {
        stopPlayer();
        isStart = true;
        isInsecond = false;
        btnStart.setVisibility(View.GONE);
        if(type==1) {
            timerProgramCountdown.startCountDown(time );
            cvCountdownViewTest.start(time );
        }else {
            timerProgramCountdown.startCountDown(waitingTime );
            cvCountdownViewTest.start(waitingTime );
        }
    }
    private void stopTimer() {
        cvCountdownViewTest.stop();
        btnStart.setVisibility(View.VISIBLE);
        isStart = false;
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

    @Override
    protected void onDestroy() {
        stopTimerFinish();
        super.onDestroy();

    }

    private void stopTimerFinish() {
        cvCountdownViewTest.stop();
        timerProgramCountdown.pauseCountDownTimer();
        if(mediaPlayer2!=null){
            mediaPlayer2.stop();
        }
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }
}
