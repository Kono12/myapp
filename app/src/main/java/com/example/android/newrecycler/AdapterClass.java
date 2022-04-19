package com.example.android.newrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<VhClass> {
   ArrayList<Data> MyData;
   private OnUserClicked onUserClicked;


   public interface OnUserClicked
   {
    public void OnUserClicked(Data user);
    public void OnUserLongClicked(Data user);

   }

   public void register(OnUserClicked onUserClicked){this.onUserClicked=onUserClicked;}

    public AdapterClass(ArrayList<Data> MyData){this.MyData=MyData;}



    @NonNull
    @Override
    public VhClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VhClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VhClass holder, int position) {
        Data user = MyData.get(position);
        holder.img.setImageResource(user.getImg());
        holder.txt.setText(user.getTxt());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUserClicked.OnUserClicked(user);
            }
        });
        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onUserClicked.OnUserLongClicked(user);
                return true;
            }
        });

   }
    @Override
    public int getItemCount() {
        return MyData.size();
    }
}
