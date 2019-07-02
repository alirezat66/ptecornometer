package ir.greencode.cornometer.di.component;

import dagger.Component;
import ir.greencode.cornometer.di.module.SplashModule;
import ir.greencode.cornometer.di.scope.PerActivity;
import ir.greencode.cornometer.ui.Main.splash.SplashActivity;

@PerActivity
@Component(modules = SplashModule.class,dependencies = ApplicationComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
