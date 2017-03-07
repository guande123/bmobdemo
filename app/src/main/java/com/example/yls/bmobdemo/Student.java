package com.example.yls.bmobdemo;

import cn.bmob.v3.BmobObject;

/**
 * Created by yls on 2017/3/7.
 */
public class Student extends BmobObject{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, String add, int age) {
        this.name = name;
        this.add = add;
        this.age = age;
    }
public Student(){}
    private String name;
    private String add;
    private int age;
}
