package com.mdstech.sample.wordcount;

import java.io.Serializable;

/**
 * Created by Srini on 5/6/17.
 */
public class Sample implements Serializable {
    private final String id;
    private final String key;
    private final String fname;
    private final String lname;
    private final String age;
    private final String company;

    public Sample(String key, String id, String fname, String lname, String age, String company) {
        this.id = id;
        this.key = key;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAge() {
        return age;
    }

    public String getCompany() {
        return company;
    }
}
