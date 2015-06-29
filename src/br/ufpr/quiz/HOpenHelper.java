package br.ufpr.quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HOpenHelper extends SQLiteOpenHelper{
    static String KEY_ID = "_id";
    static String KEY_NOME = "nome";
    static String KEY_DATA = "data";
    static String KEY_NOTA = "nota";



    String NOME_BANCO = "db_Alunos";
    String NOME_TABELA = "aluno";
//    int VERSAO_BANCO = 11;

    String SQL_CREATE_TABLE = "create table aluno " +
            "(" + KEY_ID + " integer primary key autoincrement, "
            + KEY_NOME + " text not null, "
            + KEY_DATA + " text not null, "
            + KEY_NOTA + " text not null);";

    HOpenHelper (Context context){
        super(context, "db_Alunos", null, 13);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS aluno");
        onCreate(db);
    }
}