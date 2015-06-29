package br.ufpr.quiz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ListaResultadosActivity extends Activity {
    private DataBase db;
    private List<Resultado> resultados = new ArrayList<Resultado>();
    public ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_resultados);
        
        Bundle extras = getIntent().getExtras();
        String nome = extras.getString("nome");
        int pontuacao = extras.getInt("pontuacao");
        Log.i("ha","na lista resulta ponto val:"+pontuacao);
        this.salvar(nome, pontuacao);
        db = new DataBase(this);
        lerDados();
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(new AdapterList(this, resultados));









    }

    public void salvar (String nome, int pontuacao) {
        DataBase db = new DataBase(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dataJava = new java.util.Date();
        String data = dateFormat.format(dataJava);
        db.abrir();
        db.insereAluno(nome,
                data.toString(),
                new Integer(pontuacao).toString());
        db.fechar();
        

    }
    
    public void lerDados(){
        db.abrir();
        resultados.clear();
        Cursor cursor = db.retornaTodosArtigos();

        if(cursor.moveToFirst())
            do{
            	Resultado a = new Resultado();
                a.id = cursor.getInt(cursor.getColumnIndex(DataBase.KEY_ID));
                a.nome = cursor.getString(cursor.getColumnIndex(DataBase.KEY_NOME));
                a.data = cursor.getString(cursor.getColumnIndex(DataBase.KEY_DATA));
                a.nota = cursor.getString(cursor.getColumnIndex(DataBase.KEY_NOTA));

                resultados.add(a);
            }while(cursor.moveToNext());

        StringBuilder sb = new StringBuilder();
        sb.append("Names in database:\n");
        for (Resultado resultado: resultados) {
            sb.append(resultado.nome + "  " + resultado.data + "  " + resultado.nota + "\n");
        }

        Log.d("EXAMPLE", "names size - " + resultados.size());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_tela2, menu);
        return true;
    }

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
}