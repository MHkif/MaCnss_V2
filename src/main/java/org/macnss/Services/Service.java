package org.macnss.Services;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    T save(T t);
    T update(T t);
    boolean deactivate(String slag);
    T findBy(String id);
    List<T> getAll();
}
