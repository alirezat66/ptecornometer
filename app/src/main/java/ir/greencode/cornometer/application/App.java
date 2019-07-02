package ir.greencode.cornometer.application;

import android.app.Application;

import ir.greencode.cornometer.R;
import ir.greencode.cornometer.di.component.ApplicationComponent;
import ir.greencode.cornometer.di.component.DaggerApplicationComponent;
import ir.greencode.cornometer.di.module.ApplicationModule;
import ir.greencode.cornometer.utility.Constant;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class App extends Application {
    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
/*
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath(Constant.FONTH_NAME).setFontAttrId(R.attr.fontPath).build());
*/
        initialApplicationComponent();
    }

    private void initialApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, Constant.BASE_URL))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
