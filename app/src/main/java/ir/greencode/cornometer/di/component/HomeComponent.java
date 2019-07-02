package ir.greencode.cornometer.di.component;

import dagger.Component;
import ir.greencode.cornometer.di.module.HomeModule;
import ir.greencode.cornometer.di.scope.PerActivity;
import ir.greencode.cornometer.ui.Main.MainActivity;


@PerActivity
@Component(modules = HomeModule.class,dependencies = ApplicationComponent.class)
public interface HomeComponent {
    void inject(MainActivity mainActivity);
}
