package com.example.android.newrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements AdapterClass.OnUserClicked {


    RecyclerView rv;
    ArrayList<Data> myData=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.myRV);

        myData.add(new Data(R.drawable.boy4,"Mohamed Emad"));
        myData.add(new Data(R.drawable.boy4,"Yasser Hany"));
        myData.add(new Data(R.drawable.girl,"Mariam Ahmed"));
        myData.add(new Data(R.drawable.boy4,"Ahmed Mahmoud"));
        myData.add(new Data(R.drawable.girl,"Sara Ahmed"));
        myData.add(new Data(R.drawable.girl,"Yara Ibrahim"));
        myData.add(new Data(R.drawable.boy4,"Ammar Mohamed"));
        myData.add(new Data(R.drawable.boy4,"Ismail Sarr"));
        myData.add(new Data(R.drawable.boy4,"Saif Yassin"));
        myData.add(new Data(R.drawable.boy4,"Mahmoud Ahmed"));
        myData.add(new Data(R.drawable.girl,"Nourhan Hany"));
        myData.add(new Data(R.drawable.boy4,"Hany Mohamed"));
        myData.add(new Data(R.drawable.girl,"Gehad Reda"));

        AdapterClass mainAdapter=new AdapterClass(myData);
        mainAdapter.register(this);
        LinearLayoutManager L1=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(L1);
        rv.setAdapter(mainAdapter);

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv);
    }

    @Override
    public void OnUserClicked(Data user) {
        Toast.makeText(this, user.getTxt() , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnUserLongClicked(Data user) {
        Snackbar.make(rv,user.getTxt(),Snackbar.LENGTH_SHORT).show();
    }


    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
            ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END , ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition=viewHolder.getAdapterPosition(),toPosition=target.getAdapterPosition();
            Collections.swap(myData,fromPosition,toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);


            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
         myData.remove(viewHolder.getAdapterPosition());
         rv.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };



}