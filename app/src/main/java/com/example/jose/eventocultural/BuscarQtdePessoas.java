package com.example.jose.eventocultural;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class BuscarQtdePessoas extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_qtde_pessoas);
        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDadosPessoas();

        String[] nomeCampos = new String[] {CriarBanco.ID, CriarBanco.NOME};
        int[] idViews = new int[] {R.id.idQuantidade, R.id.nomePessoa};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_buscar_qtde_pessoas,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);
    }

    public void btnTelaCadastro(View view) {
        Intent itTelaCadastro = new Intent(BuscarQtdePessoas.this, MainActivity.class);
        startActivity(itTelaCadastro);
    }
}
