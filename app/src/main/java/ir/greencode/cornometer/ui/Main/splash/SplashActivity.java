package ir.greencode.cornometer.ui.Main.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import javax.inject.Inject;

import ir.greencode.cornometer.R;
import ir.greencode.cornometer.base.BaseActivity;
import ir.greencode.cornometer.data.loc.MySharedPreferences;
import ir.greencode.cornometer.data.roomdb.MyTimer;
import ir.greencode.cornometer.di.component.DaggerSplashComponent;
import ir.greencode.cornometer.di.module.SplashModule;
import ir.greencode.cornometer.ui.Main.HomeViewModel;
import ir.greencode.cornometer.ui.Main.MainActivity;
import ir.greencode.cornometer.utility.Constant;

public class SplashActivity extends BaseActivity {
    @Inject
    MySharedPreferences mySharedPreferencesl;
    @Inject
    HomeViewModel viewModel;

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent, int LAYER_TYPE_SOFTWARE) {
        super.onViewReady(savedInstanceState, intent, LAYER_TYPE_SOFTWARE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                decideForSomething();
            }
        },3000);
    }

    private void decideForSomething() {
        Intent intent = new Intent(this, MainActivity.class);
        if(!mySharedPreferencesl.getDataBoolean(Constant.PREF_FIRST)){
            viewModel.insertCat(new MyTimer("Read Aloud(RA)","40 sec wait,40 sec for read",2,40000,40000));
            viewModel.insertCat(new MyTimer("Describe Image(DI)","25 sec wait,40 sec for describe",2,25000,40000));
            viewModel.insertCat(new MyTimer("Retell lecture(RL)","40 sec for retell",1,0,40000));
            viewModel.insertCat(new MyTimer("Summarize Written Text(SWT)","10 Min for write",1,0,600000));
            viewModel.insertCat(new MyTimer("Essay","20 Min for write",1,0,1200000));
            viewModel.insertCat(new MyTimer("Summarize spoken text","10 Min for hearing and write",1,0,600000));
            viewModel.insertCat(new MyTimer("Summarize spoken text","10 Min for hearing and write",1,0,600000));
            mySharedPreferencesl.putData(Constant.PREF_FIRST,true);
            startActivity(intent);
        }else {
            startActivity(intent);
        }
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerSplashComponent.builder().applicationComponent(getApplicationComponent())
                .splashModule(new SplashModule()).build().inject(this);
    }
}
