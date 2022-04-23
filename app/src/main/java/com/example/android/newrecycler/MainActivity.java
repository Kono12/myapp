package com.example.android.newrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements AdapterClass.OnUserClicked {

    MediaPlayer mediaPlayer=new MediaPlayer();
    RecyclerView rv;
    ArrayList<Data> myData=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.myRV);
        mediaPlayer=MediaPlayer.create(this,R.raw.click);

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

        // choose your manager
        LinearLayoutManager L1=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        GridLayoutManager G1=new GridLayoutManager(this,1);
        rv.setLayoutManager(L1);


        rv.setAdapter(mainAdapter);

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater menuInflater= getMenuInflater();
      menuInflater.inflate(R.menu.menu,menu);
      return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menu_id:
            {
                mediaPlayer.start();
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_camera:
            {
                mediaPlayer.start();
                Toast.makeText(this, "Captured", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_settings:
            {
                mediaPlayer.start();
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            }


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnUserClicked(Data user) {
        mediaPlayer.start();
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

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.start();
        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(i);
    }
}