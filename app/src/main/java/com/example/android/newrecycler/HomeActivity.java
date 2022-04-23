package com.example.android.newrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void BtnContactsClicked(View view) {
        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void BtnHomeClicked(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction frag = manager.beginTransaction();
        Fragment fragment = new BlankFragment();
        frag.replace(R.id.home_frame,fragment);
        frag.commit();
    }

    public void AddContactClicked(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction frag = manager.beginTransaction();
        Fragment fragment = new AddContact();
        frag.replace(R.id.home_frame,fragment);
        frag.commit();
    }

    @Override
    public void onBackPressed() {
        ExitDialog exitDialog=new ExitDialog();
        exitDialog.show(getSupportFragmentManager(),"");
        exitDialog.setCancelable(false);

    }
}