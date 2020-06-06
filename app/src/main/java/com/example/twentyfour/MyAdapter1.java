package com.example.twentyfour;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.VH> {
    ArrayList<String> stringses=new ArrayList<String>();
    public void setOnMyClickListener(MyClickListener myClickListener){
        this.myClickListener=myClickListener;
    }
    public interface MyClickListener{
        public void click(View v,int pos);
    };
    public static MyAdapter1.MyClickListener myClickListener;
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout1_myadapter1,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        holder.tv1.setText(stringses.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos=holder.getLayoutPosition();
                myClickListener.click(holder.itemView,pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringses.size();
    }

    public MyAdapter1(ArrayList<String> stringses){
        this.stringses=stringses;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tv1;
        LinearLayout lin1;
        public VH(@NonNull final View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
            lin1=itemView.findViewById(R.id.lin1);
        }
    }
}
