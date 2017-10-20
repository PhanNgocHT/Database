package com.example.hp.recycleviewexercise.model;

/**
 * Created by hp on 10/18/2017.
 */

public class Person {
    private String mId;
    private String mName;
    private String mNumber;

    public Person(String id, String name, String number) {
        mId=id;
        mName = name;
        mNumber = number;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}
