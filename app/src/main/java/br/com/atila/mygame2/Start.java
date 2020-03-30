package br.com.atila.mygame2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }



    public void Start(View view) {

        Intent Start = new Intent(this, GameOn.class);

        startActivity(Start);
    }

    public void Sair(View view) {

        Intent Sair = new Intent(Intent.ACTION_MAIN);
        Sair.addCategory(Intent.CATEGORY_HOME);
        Sair.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Sair);


    }
}