package br.ufpr.quiz;

import br.ufpr.quiz.threads.QuestionListThread;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btnEntrar;
	private EditText nome;
	private Handler handlerLogin = new Handler(){
    	
    	public void handleMessage(Message msg){
    		Bundle bundle = msg.getData();
    		Boolean status = bundle.getBoolean("status");
    		bundle.putInt("position", -1);
    		bundle.putInt("pontuacao", 0);
    		if(status){
    			Intent it = new Intent(MainActivity.this, PerguntasActivity.class);
    			it.putExtras(bundle);
    			startActivity(it);
    		}else
    			Toast.makeText(MainActivity.this, "usuario/senha nao encontrados", Toast.LENGTH_LONG).show();
    	}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnEntrar = (Button) findViewById(R.id.btnStart);
	    btnEntrar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		Log.i("ha","on click mainactivity");
		nome = (EditText) findViewById(R.id.nome);
		QuestionListThread loginThread = new QuestionListThread  (
				nome.getText().toString(),
				handlerLogin);
		loginThread.start();
	}
}
