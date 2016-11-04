package com.example.jose.eventocultural;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jose on 04/11/2016.
 */

public class BancoController {
    private SQLiteDatabase db;
    private CriarBanco banco;

    public BancoController(Context context){
        banco = new CriarBanco(context);
    }

    public String insereDado(String nome, String telefone, String email, String gostou, String sujestoes){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriarBanco.NOME, nome);
        valores.put(CriarBanco.TELEFONE, telefone);
        valores.put(CriarBanco.EMAIL, email);
        valores.put(CriarBanco.GOSTOU, gostou);
        valores.put(CriarBanco.SUJESTOES, sujestoes);

        resultado = db.insert(CriarBanco.TABELA, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro de inserção";
        }
        else{
            return "Registro inserido com sucesso";
        }
    }

    //Buscar Cadastros
    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME,banco.TELEFONE,banco.EMAIL,banco.GOSTOU, banco.SUJESTOES};
        String where = CriarBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriarBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    //

    //Buscar Quantidade de Pessoas
    public Cursor carregaDadosPessoas(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    //

    //Buscar Sujestões
    public Cursor carregaDadosSujestoes(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.SUJESTOES};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    //

    public void alteraRegistro(int id, String nome, String telefone, String email,String gostou, String sujestoes){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriarBanco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(CriarBanco.NOME, nome);
        valores.put(CriarBanco.TELEFONE, telefone);
        valores.put(CriarBanco.EMAIL, email);
        valores.put(CriarBanco.GOSTOU, gostou);
        valores.put(CriarBanco.SUJESTOES, sujestoes);

        db.update(CriarBanco.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = CriarBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriarBanco.TABELA,where,null);
        db.close();
    }
}
