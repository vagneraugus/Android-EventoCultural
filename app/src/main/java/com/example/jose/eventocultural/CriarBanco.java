package com.example.jose.eventocultural;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jose on 04/11/2016.
 */

public class CriarBanco extends SQLiteOpenHelper {
    static final String NOME_BANCO   = "banco.db";
    static final String TABELA       = "evento";
    static final String ID           = "_id";
    static final String NOME         = "nome";
    static final String TELEFONE     = "telefone";
    static final String EMAIL        = "email";
    static final String GOSTOU       = "gostou";
    static final String SUJESTOES    = "sujestoes";
    static final int VERSAO          = 1;


    public CriarBanco(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "
                + TABELA    + "("
                + ID        + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOME      + " TEXT, "
                + TELEFONE  + " TEXT, "
                + EMAIL     + " TEXT,"
                + GOSTOU    + " TEXT,"
                + SUJESTOES + " TEXT"
                            + ")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABELA );
        onCreate(db);
    }
}
