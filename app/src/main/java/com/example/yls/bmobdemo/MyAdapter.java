package com.example.yls.bmobdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yls on 2017/3/7.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Student> stuList;
    private OnDelListener listener;
    public MyAdapter(List<Student> stuList,OnDelListener listener) {
        this.stuList = stuList;
        this.listener = listener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        final Student s = stuList.get(position);
        holder.nameTx.setText(s.getName());
        holder.addTx.setText(s.getAdd());
        holder.ageTx.setText(s.getAge()+"");
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.delData(s.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return stuList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
         TextView nameTx;
        TextView ageTx;
        TextView addTx;
        Button btnDel;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTx = (TextView) itemView.findViewById(R.id.name);
            ageTx = (TextView) itemView.findViewById(R.id.age);
            addTx = (TextView) itemView.findViewById(R.id.add);
            btnDel = (Button) itemView.findViewById(R.id.btn_del);
        }
    }
}
