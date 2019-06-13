package ir.greencode.cornometer.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ir.greencode.cornometer.api.MyApiService;
import ir.greencode.cornometer.data.roomdb.LocalCategoryDataSource;
import ir.greencode.cornometer.data.roomdb.base.AppDatabase;
import ir.greencode.cornometer.di.scope.PerActivity;
import ir.greencode.cornometer.mvp.view.HomeView;
import ir.greencode.cornometer.ui.Main.HomeViewModel;
import retrofit2.Retrofit;

@Module
public class HomeModule {

    HomeView mView;

    public HomeModule(HomeView mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    MyApiService provideContentApiService(Retrofit retrofit){
        return retrofit.create(MyApiService.class);
    }
    @PerActivity
    @Provides
    HomeView provideMainView(){
        return mView;
    }
    @Provides
    public HomeViewModel provideHomeViewModel(Context context){
        LocalCategoryDataSource localTaskDataSource=new LocalCategoryDataSource(AppDatabase
                .getInMemoryDatabase(context)
                .categoryDao());
        return new HomeViewModel(localTaskDataSource);
    }
}
