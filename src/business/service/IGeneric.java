package business.service;

import java.io.IOException;
import java.util.List;

public interface IGeneric <T, E>{
    List<T> findAll() throws IOException;
    T findById(E e);
    void create();
    void update(T t) throws IOException;
    boolean isExistById(E e);
    void save();
}
