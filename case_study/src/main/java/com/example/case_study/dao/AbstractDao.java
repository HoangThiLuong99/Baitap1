package com.example.case_study.dao;

import java.util.List;

public abstract class AbstractDao<T> {

    public abstract boolean create(T t);
    public abstract T findById(int id);
    public abstract List<T> findAll();
    public abstract boolean update(T t);





}

