package h.assignment.taskh.service;

import java.util.List;

public interface CrudService<T, S> {
    T create(T entity);

    T read(S id);

    List<T> getAll();

    T update(S id, T newEntity);

    T remove(S id);
}
