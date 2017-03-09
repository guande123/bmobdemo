package com.example.yls.bmobdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity implements  OnDelListener{
    private RecyclerView mRecyclerView;
    private Button btnGet;
    private List<Student>  stuList = new ArrayList<Student>();
    private MyAdapter mAdapter;
    private EditText searchEdt;
    private Button searchBtn;
    private  Button mButtonReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"e8169f6b908c89b44c95bd13a2e01d72");
        initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this
                ,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter = new MyAdapter(stuList,MainActivity.this));
        getAllMsg();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        btnGet = (Button) findViewById(R.id.btn_get);
        searchBtn = (Button) findViewById(R.id.search_btn);
        searchEdt = (EditText) findViewById(R.id.search_edt);
        mButtonReg = (Button) findViewById(R.id.register);
        mButtonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegActivity.class));
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  age = searchEdt.getText().toString().trim();
                BmobQuery<Student> query = new BmobQuery<Student>();
                String sql = "select * from Student where age = "+age;
                query.doSQLQuery(sql, new SQLQueryListener<Student>() {
                    @Override
                    public void done(BmobQueryResult<Student> bmobQueryResult, BmobException e) {
                       if (e==null){
                           mAdapter.setData(bmobQueryResult.getResults());
                       }else{
                           Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                       }
                    }
                });
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StuActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllMsg();
    }


    public void getAllMsg() {
        BmobQuery<Student> query =new  BmobQuery<Student>();
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e==null){
                    mAdapter.setData(list);
                }else {
                    Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                    Log.i("AAAA",e.toString());
                }
            }
        });
    }

    @Override
    public void delData(String name) {
        for(Student s :stuList){
            if(name.equals(s.getName())){
                Student s2 = new Student();
                s2.setObjectId(s.getObjectId());
                s2.delete(new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            getAllMsg();
                            mAdapter.notifyDataSetChanged();
                        }else{
                           Toast.makeText(MainActivity.this,"删除失败：" + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }

                });
            }
        }
    }
}
