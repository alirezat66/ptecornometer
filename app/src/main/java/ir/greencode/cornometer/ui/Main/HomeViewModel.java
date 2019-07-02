package ir.greencode.cornometer.ui.Main;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import ir.greencode.cornometer.data.roomdb.MyTimer;
import ir.greencode.cornometer.data.roomdb.base.DataSource;

public class HomeViewModel extends ViewModel {
    private DataSource dataSource;

    public HomeViewModel(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<MyTimer> getAllCats(){
        return dataSource.getAll();
    }

    public MyTimer getCat(MyTimer category){
        return (MyTimer) dataSource.get(category);
    }

    public void insertCat(final MyTimer category){
            dataSource.insert(category);

    }

    public void delete(final MyTimer cat){

            dataSource.delete(cat);

    }

    public void update(final MyTimer category){

            dataSource.update(category);

    }
}
