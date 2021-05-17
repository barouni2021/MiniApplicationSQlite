package com.example.miniapplicationsqlite2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.btn_fragmentajout)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_fragmentlister)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_fragmentmodifier)).setOnClickListener(this);

    }

    private void loadFragment(Fragment fragment) {

        Bundle bundle = new Bundle();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        bundle.putSerializable("Clé", db);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeHolder,fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fragmentajout :
                loadFragment(new AjoutFragment());
                break;

            case R.id.btn_fragmentlister :
                //loadFragment(new ListFragment());
                break;
            case R.id.btn_fragmentmodifier :
                //loadFragment(new ModifFragment());
                break;
        }

    }
}