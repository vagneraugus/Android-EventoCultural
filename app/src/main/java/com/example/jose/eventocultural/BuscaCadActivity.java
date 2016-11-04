package com.example.jose.eventocultural;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class BuscaCadActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_cad);


    }

    public void btnTelaCadastro(View view) {
        Intent itTelaCadastro = new Intent(BuscaCadActivity.this, MainActivity.class);
        startActivity(itTelaCadastro);
    }
}
