package com.example.hp.recycleviewexercise.database;

import com.example.hp.recycleviewexercise.model.Person;

import java.util.ArrayList;

/**
 * Created by hp on 10/20/2017.
 */

public interface Database {
    boolean save(Person person);

    boolean update(Person person);

    boolean delete(Person person);

    ArrayList<Person> getAllData();
}
