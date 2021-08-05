package com.yahya.task.tracker.tasktracker.service;

import java.util.List;

public interface BasicServiceSkeleton<T> {

    T findById(int id);

    T save(T item);

    List<T> findAll();

    boolean deleteById(Integer id);
}
