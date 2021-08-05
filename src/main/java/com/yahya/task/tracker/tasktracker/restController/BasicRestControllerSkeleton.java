package com.yahya.task.tracker.tasktracker.restController;

import java.util.List;

public interface BasicRestControllerSkeleton<T> {

    List<T> getAll();

    T get(Integer id);

    T add(T item);

    T update(Integer id, T updatedItem);

    void delete(Integer id);
}
