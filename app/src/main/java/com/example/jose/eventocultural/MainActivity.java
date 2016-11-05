package com.example.jose.eventocultural;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SQLiteDatabase db;
    private CriarBanco banco;
    String codigo;
    BancoController crud;

    ProgressDialog progressDialog;
    private java.lang.String tabela;
    private Context onUpgrade;
    private Context onCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //----------------------------------------------------------------------//
        //----------------------------------------------------------------------//
        progressDialog = new ProgressDialog(MainActivity.this);

//        Button btn = (Button)findViewById(R.id.idDelete);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setMessage("Deseja apagar  os dados do evento?").setCancelable(false)
//                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                BancoController crud = new BancoController(getBaseContext());
//                                crud.deletaTudo();
//
//                                Toast.makeText(MainActivity.this, "Dados excluídos!", Toast.LENGTH_LONG).show();
//                            }
//                        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        Toast.makeText(MainActivity.this, "Ação cancelada!", Toast.LENGTH_LONG).show();
//                    }
//                });
//
//                AlertDialog alert = builder.create();
//                alert.show();
//
//            }
//        });
}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_deletarBanco) {
            excluirDialog();

        } else if (id == R.id.nav_buscarQtdePessoas) {
            Intent itBuscarQtdePessoas = new Intent(MainActivity.this, BuscarQtdePessoas.class);
            startActivity(itBuscarQtdePessoas);

        } else if (id == R.id.nav_buscarSujestoes) {
            Intent itBuscarSujestoes = new Intent(MainActivity.this, BuscarSujestoesActivity.class);
            startActivity(itBuscarSujestoes);

        }
//        else if (id == R.id.nav_share) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //----------------------------------------------------------------------//
    //----------------------------------------------------------------------//

    //Apos licar no botão de deletar
    public void excluirDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Deseja apagar  os dados do evento?").setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        BancoController crud = new BancoController(getBaseContext());
                        crud.deletaTudo();

                        Toast.makeText(MainActivity.this, "Dados excluídos!", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Ação cancelada!", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }


    //Exclui apos clicar em sim
//    public void excluiu(){
//        banco.onUpgrade(db , 1, 2);
//    }


    public void Salvar() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Salvando...");
        progressDialog.show();


        BancoController crud = new BancoController(getBaseContext());
        EditText nome = (EditText) findViewById(R.id.edtNome);
        EditText telefone = (EditText) findViewById((R.id.edtTelefone));
        EditText email = (EditText) findViewById(R.id.edtEmail);
        EditText gostou = (EditText) findViewById(R.id.edtGostou);
        EditText sujestoes = (EditText) findViewById(R.id.edtSujestao);


        String nomeString = nome.getText().toString();
        String telefoneString = telefone.getText().toString();
        String emailString = email.getText().toString();
        String gotouString = gostou.getText().toString();
        String sujestoesString = sujestoes.getText().toString();
        String resultado;


        resultado = crud.insereDado(nomeString, telefoneString, emailString, gotouString, sujestoesString);

//            Toast.makeText(getApplicationContext(), "Registro cadastrado com sucesso!", Toast.LENGTH_LONG).show();

    }

    //Chamar Icon do menu_cadastro na activity Main
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    public void menuCliqueCadastrar(MenuItem item) {
        Salvar();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();

                        Intent itPesquisaCad = new Intent(MainActivity.this, BuscaCadActivity.class);
                        startActivity(itPesquisaCad);
                    }
                }, 2000);
    }

    public void menuCliquePesquisar(MenuItem item) {
        Intent itPesquisaCad = new Intent(MainActivity.this, BuscaCadActivity.class);
        startActivity(itPesquisaCad);

    }

    public void deletarTudo(View view) {
//        new MaterialDialog.Builder(this)
//                .title("Excluir banco de dados")
//                .content("Deseja realmente excluir todas as informações?")
//                .positiveText("Sim")
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                        BancoController crud = new BancoController(getBaseContext());
//                        crud.deletaTudo();
//
//                        new android.os.Handler().postDelayed(
//                                new Runnable() {
//                                    public void run() {
//                                        progressDialog.dismiss();
//                                        //finish();
//                                    }
//                                }, 2000);
//
//                    }
//                })
//                .negativeText("Não")
//                .show();

        crud.deletaTudo();

    }
}
