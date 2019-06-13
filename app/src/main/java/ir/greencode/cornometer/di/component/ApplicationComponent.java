package ir.greencode.cornometer.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import ir.greencode.cornometer.di.module.ApplicationModule;
import retrofit2.Retrofit;

@Component(modules = {ApplicationModule.class})
@Singleton
public interface ApplicationComponent {
    Retrofit exposeRetrofit();
    Context exposeContext();
    SharedPreferences exposeSharePrefrences();


}
