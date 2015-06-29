package br.ufpr.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.quiz.threads.QuestionListThread;
import br.ufpr.quiz.threads.QuestionThread;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PerguntasActivity extends Activity implements OnClickListener {	
	
	TextView pergunta;
	TextView txtOpcoes;
	ImageView imagem;
	int actual_position;
	String resposta;
	String nome;
	RadioGroup RadioOptions;
	Button responder;
	int pontuacao;
	int[] list_questions = new int[5];
	
private Handler handlerPergunta = new Handler(){
    	
    	public void handleMessage(Message msg){
    		Bundle bundle = msg.getData();
    		Log.i("ha","aqui chega");
    		Boolean status = bundle.getBoolean("status");
    		pergunta.setText(bundle.getString("pergunta"));
    		txtOpcoes.setText(bundle.getString("opcoes"));
    		resposta = bundle.getString(("resposta"));
    		pontuacao = bundle.getInt("pontuacao");
    		String imgStr = bundle.getString("imagem");
    		if(!imgStr.equals("")){
    			byte[] decodedString = Base64.decode(imgStr, Base64.DEFAULT);
    			Log.i("ha","decodado valendo:"+decodedString);
    			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    			Drawable d = new BitmapDrawable(getResources(),decodedByte); 
    			imagem.setImageDrawable(d);
    		}
    	}
	};
	

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        Bundle extras = getIntent().getExtras();
        this.pergunta = (TextView) findViewById(R.id.txtQuest);
        this.txtOpcoes = (TextView) findViewById(R.id.txtOptions);
        this.RadioOptions = (RadioGroup) findViewById(R.id.RadioOptions);
        imagem = (ImageView) findViewById(R.id.img);
        nome = extras.getString(("nome"));
        Log.i("ha","nome vale:"+nome);
        this.actual_position = extras.getInt("position")+1;
        list_questions = extras.getIntArray("questions");
        responder = (Button) findViewById(R.id.responder);
	    responder.setOnClickListener(this);
		QuestionThread questThread = new QuestionThread  (
				list_questions[this.actual_position],
				handlerPergunta);
		questThread.start();
    }

    @Override
    public void onClick(View v) {

            // get selected radio button from radioGroup
        int selectedId = RadioOptions.getCheckedRadioButtonId();

        // find the radiobutton by returned id
            RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
            if(resposta.equals(radioSexButton.getText().toString().toLowerCase())){
            	pontuacao =+1;
            }
            if(actual_position>=4){
            	Message msg = new Message();
	    		Bundle bundle = msg.getData();
	    		bundle.putInt("pontuacao", pontuacao);
	    		bundle.putString("nome", nome);
            	Intent it = new Intent(this, ListaResultadosActivity.class);
            	it.putExtras(bundle);
                startActivity(it);
    		}
    		else{
    			Message msg = new Message();
	    		Bundle bundle = msg.getData();
	    		bundle.putInt("position", actual_position);
	    		bundle.putInt("pontuacao", pontuacao);
	    		bundle.putString("nome", nome);
	    		bundle.putIntArray("questions", list_questions);
	    		Log.i("ha","aqui na handleNextPergunta chega");
	    		Intent it = new Intent(PerguntasActivity.this, PerguntasActivity.class);
				it.putExtras(bundle);
				startActivity(it);
    		}

    }
}