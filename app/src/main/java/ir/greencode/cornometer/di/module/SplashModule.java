package ir.greencode.cornometer.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ir.greencode.cornometer.data.roomdb.LocalCategoryDataSource;
import ir.greencode.cornometer.data.roomdb.base.AppDatabase;
import ir.greencode.cornometer.ui.Main.HomeViewModel;

@Module
public class SplashModule {
    @Provides
    public HomeViewModel provideHomeViewModel(Context context){
        LocalCategoryDataSource localTaskDataSource=new LocalCategoryDataSource(AppDatabase
                .getInMemoryDatabase(context)
                .categoryDao());
        return new HomeViewModel(localTaskDataSource);
    }
}
