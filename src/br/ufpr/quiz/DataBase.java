package br.ufpr.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class DataBase {
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

    final Context context;
    HOpenHelper openHelper;
    SQLiteDatabase db;

    public DataBase(Context ctx) {
        this.context = ctx;
        openHelper = new HOpenHelper(context);
    }

    public DataBase abrir() {
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar() {
        openHelper.close();
    }

    public long insereAluno(String nome, String data, String nota){
        ContentValues campos = new ContentValues();
        campos.put(KEY_NOME, nome);
        campos.put(KEY_DATA, data);
        campos.put(KEY_NOTA, nota);

        return db.insert(NOME_TABELA, null, campos);
    }

    public Cursor retornaTodosArtigos(){
        return db.query(NOME_TABELA, new String[]{
                        KEY_ID, KEY_NOME, KEY_DATA, KEY_NOTA}, null, null, null, null, null);
    }


}