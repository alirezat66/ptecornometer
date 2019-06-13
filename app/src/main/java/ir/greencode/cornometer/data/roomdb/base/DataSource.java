package ir.greencode.cornometer.data.roomdb.base;

import java.util.List;

public interface DataSource <T> {
    T get(T t);
    List<T> getAll();
    void insert(T t);
    void delete(T t);
    void update(T t);
}
