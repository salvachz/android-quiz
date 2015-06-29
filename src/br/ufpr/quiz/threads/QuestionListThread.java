package br.ufpr.quiz.threads;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import br.ufpr.quiz.WebService;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class QuestionListThread extends Thread{
            private String user;
            private Handler handler;
            
            public QuestionListThread (String user_id, Handler handler){
                this.user = user_id;
                this.handler = handler;
            }
            
            public void run(){
                Log.i("ha","chamou requestProduct");
                WebService ws = new WebService("http://salvachz.com.br/quiz");
                Log.i("ha","vai montar parametros");
                Map<String,String> params = new HashMap<String,String>();
                
                
                try{
                	String result = ws.webGet("/", params);
                    Log.i("ha","done webGet valendo"+result);
                    JSONObject json = new JSONObject(result);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("status", json.getBoolean("status"));
                    bundle.putInt("position", 0);
                    JSONArray jsonArray = json.getJSONArray("questions");
                    int[] list = new int[5];
                    for (int i=0; i<jsonArray.length(); i++) {
                        list[i] = jsonArray.getInt(i);
                    }
                    bundle.putIntArray("questions", list);
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
