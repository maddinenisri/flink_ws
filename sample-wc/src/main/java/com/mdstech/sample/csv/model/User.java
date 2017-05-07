package com.mdstech.sample.csv.model;

/**
 * Created by Srini on 5/5/17.
 */
public class User {
    private Integer id;
    private String fname;
    private String lname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "User [" + id + "{" + fname + " " + lname + "}]";
    }
}
