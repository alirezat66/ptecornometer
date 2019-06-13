package ir.greencode.cornometer.data.roomdb;

import java.util.List;

import ir.greencode.cornometer.data.roomdb.base.DataSource;

public class LocalCategoryDataSource implements DataSource<MyTimer> {
    private MyTimerDao myTimerDao;

    public LocalCategoryDataSource(MyTimerDao myTimerDao) {
        this.myTimerDao = myTimerDao;
    }



    @Override
    public MyTimer get(MyTimer category) {
        return myTimerDao.specialCat(category.getid());
    }

    @Override
    public List<MyTimer> getAll() {
        return myTimerDao.listOfTimers();
    }

    @Override
    public void insert(MyTimer category) {
        myTimerDao.insertTimer(category);
    }

    @Override
    public void delete(MyTimer category) {
        myTimerDao.nukeTable();
    }

    @Override
    public void update(MyTimer category) {
        myTimerDao.insertTimer(category);
    }
}
