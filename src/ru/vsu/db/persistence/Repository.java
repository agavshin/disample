package ru.vsu.db.persistence;

import java.util.List;

public interface Repository<T, ID> {
    List<T> list();
    T find(ID id);
}
