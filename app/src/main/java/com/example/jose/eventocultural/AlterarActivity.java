package com.example.jose.eventocultural;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class AlterarActivity extends AppCompatActivity {

    EditText nome;
    EditText telefone;
    EditText email;
    EditText gosuto;
    EditText sujestoes;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        nome      = (EditText)findViewById(R.id.edtNome);
        telefone  = (EditText)findViewById(R.id.edtTelefone);
        email     = (EditText)findViewById(R.id.edtEmail);
        gosuto    = (EditText)findViewById(R.id.edtGostou);
        sujestoes = (EditText)findViewById(R.id.edtSujestao);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriarBanco.NOME)));
        telefone.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriarBanco.TELEFONE)));
        email.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriarBanco.EMAIL)));
        gosuto.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriarBanco.GOSTOU)));
        sujestoes.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriarBanco.SUJESTOES)));
    }

    private void alterar() {
        crud.alteraRegistro(Integer.parseInt(codigo), nome.getText().toString(),telefone.getText().toString()
                ,email.getText().toString(),gosuto.getText().toString(), sujestoes.getText().toString());
        Intent intent = new Intent(AlterarActivity.this,BuscaCadActivity.class);
        startActivity(intent);
        finish();
    }

    private void Deletar() {
        crud.deletaRegistro(Integer.parseInt(codigo));
        Intent intent = new Intent(AlterarActivity.this,BuscaCadActivity.class);
        startActivity(intent);
        finish();
    }

    //Chamar Icon do menu_cadastro na activity Main
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_alterar, menu);
        return true;
    }

    public void menuCliqueAlterar(MenuItem item) {
        alterar();
    }



    public void menuCliqueDeletar(MenuItem item) {
        Deletar();
    }


}
