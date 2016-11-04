package com.example.jose.eventocultural;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BuscarSujestoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_sujestoes);
    }

    public void btnTelaCadastro(View view) {
        Intent itTelaCadastro = new Intent(BuscarSujestoesActivity.this, MainActivity.class);
        startActivity(itTelaCadastro);
    }
}
