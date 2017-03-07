package com.example.yls.bmobdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by yls on 2017/3/7.
 */
public class StuActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_activity);
        ((Button)findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.edt_name)).getText().toString().trim();
                String add = ((EditText)findViewById(R.id.edt_add)).getText().toString().trim();
                int  age = Integer.parseInt(((EditText)findViewById(R.id.edt_age)).getText().toString().trim());
                Student s = new Student(name,add,age);
                s.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        finish();
                    }
                });
            }
        });

    }

}
