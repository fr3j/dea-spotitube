package nl.frej.dea.spotitube.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(long id);

    List<T> findAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
