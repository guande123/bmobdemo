package com.example.yls.bmobdemo;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

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

    public BmobFile getImgtx() {
        return imgtx;
    }

    public void setImgtx(BmobFile imgtx) {
        this.imgtx = imgtx;
    }

    private BmobFile imgtx;
}
