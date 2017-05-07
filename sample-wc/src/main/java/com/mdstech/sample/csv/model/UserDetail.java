package com.mdstech.sample.csv.model;

/**
 * Created by Srini on 5/6/17.
 */
public class UserDetail {
    private Integer id;
    private String fname;
    private String lname;
    private Integer age;
    private String company;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UserDetail [" + id + "{" + fname + " " + lname + " " + age + " " + company +"}]";
    }
}
