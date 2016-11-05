package com.example.jose.eventocultural;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

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
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        progressDialog = new ProgressDialog(AlterarActivity.this);

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
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Alterando...");
//        progressDialog.show();

        crud.alteraRegistro(Integer.parseInt(codigo), nome.getText().toString(),telefone.getText().toString()
                ,email.getText().toString(),gosuto.getText().toString(), sujestoes.getText().toString());
        Intent intent = new Intent(AlterarActivity.this,BuscaCadActivity.class);
        startActivity(intent);
    }

    private void Deletar() {
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Deletando...");
//        progressDialog.show();

        crud.deletaRegistro(Integer.parseInt(codigo));
        Intent intent = new Intent(AlterarActivity.this,BuscaCadActivity.class);
        startActivity(intent);
    }

    //Chamar Icon do menu_cadastro na activity Main
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_alterar, menu);
        return true;
    }

    public void menuCliqueAlterar(MenuItem item) {
        alterar();

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        progressDialog.dismiss();
//                        finish();
//                    }
//                }, 2000);
    }



    public void menuCliqueDeletar(MenuItem item) {
        excluirDialog();
    }

    //Pergunta ao usuário se deseja excluir
    public void excluirDialog(){
        new MaterialDialog.Builder(this)
                .title("Excluir")
                .content("Deseja excluir esta pessoa?")
                .positiveText("Sim")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        Deletar();

                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        progressDialog.dismiss();
                                        finish();
                                    }
                                }, 2000);

                    }
                })
                .negativeText("Não")
                .show();
    }
}
