package com.example.yls.bmobdemo;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by yls on 2017/3/7.
 */
public class StuActivity extends AppCompatActivity{
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_activity);
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }

        ((Button)findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = ((EditText)findViewById(R.id.edt_name)).getText().toString().trim();
                final String add = ((EditText)findViewById(R.id.edt_add)).getText().toString().trim();
                final int  age = Integer.parseInt(((EditText)findViewById(R.id.edt_age)).getText().toString().trim());
                String path = Environment.getExternalStorageDirectory().getPath()+ File.separator+"dog1.jpg";
                File file = new File(path);
                if(file.exists()){
                    Toast.makeText(StuActivity.this,"file exists",Toast.LENGTH_LONG).show();
                }
                final BmobFile bmobFile = new BmobFile(file);
                bmobFile.setUrl(path);
                bmobFile.upload(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            Student s = new Student(name,add,age);
                            s.setImgtx(bmobFile);
                            s.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    finish();
                                }
                            });
                        }else{
                            Toast.makeText(StuActivity.this,"upload failed",Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });

    }

}
