package br.ufpr.quiz.threads;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import br.ufpr.quiz.WebService;

public class QuestionThread extends Thread{
	private int question;
	private Handler handler;
	
	public QuestionThread (int question, Handler handler){
        this.question = question;
        this.handler = handler;
    }
    
    public void run(){
        Log.i("ha","chamou requestProduct");
        WebService ws = new WebService("http://salvachz.com.br/quiz");
        Log.i("ha","vai montar parametros");
        Map<String,String> params = new HashMap<String,String>();
        params.put("question", new Integer(this.question).toString());
        
        
        try{
        	String result = ws.webGet("/processQuest.php", params);
            Log.i("ha","done webGet valendo"+result);
            JSONObject json = new JSONObject(result);
            Bundle bundle = new Bundle();
            bundle.putBoolean("status", json.getBoolean("status"));
            bundle.putString("pergunta", json.getString("pergunta"));
            bundle.putString("opcoes", json.getString("opcoes"));
            bundle.putString("resposta", json.getString("resposta"));
            bundle.putString("imagem", json.getString("imagem"));
            
            Message msg = new Message();
            msg.setData(bundle);
            Log.i("ha","run sending message");
            this.handler.sendMessage(msg);
        }catch (org.json.JSONException e) {
            e.printStackTrace();
            Log.e("ha","catch loginThread");
        }
    }
}
