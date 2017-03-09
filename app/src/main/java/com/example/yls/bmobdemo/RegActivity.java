package com.example.yls.bmobdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by yls on 2017/3/7.
 */
public class RegActivity extends AppCompatActivity{
     private Button mButton;
    private EditText edtName;
    private EditText edtphone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);
        mButton = (Button) findViewById(R.id.btn_reg);
      edtName = (EditText) findViewById(R.id.edt_user);
        edtphone = (EditText) findViewById(R.id.edt_phone);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtphone.getText().toString().trim();
                BmobSMS.requestSMSCode(phone, "bmobtest", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if(e==null){
                            Log.i("AAAA", "msg identify id is "+integer);
                            finish();
                        }else {
                            Log.i("AAAA", "done: "+ e.toString());
                        }
                    }
                });
            }
        });

    }

}
