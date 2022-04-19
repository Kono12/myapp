package com.example.android.newrecycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class VhClass extends RecyclerView.ViewHolder {

    ImageView img;
    TextView txt;
    ConstraintLayout parent;


    public VhClass(@NonNull View itemView) {
        super(itemView);
        parent=itemView.findViewById(R.id.constraint);
        img=itemView.findViewById(R.id.imageView);
        txt=itemView.findViewById(R.id.TextView);
    }
}
