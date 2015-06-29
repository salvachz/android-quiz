package br.ufpr.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class AdapterList extends BaseAdapter{

    private LayoutInflater mInflater;
    private List<Resultado> resultados;

    public AdapterList(Context context, List<Resultado> resultados){
        this.resultados = resultados;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return resultados.size();
    }

    @Override
    public Resultado getItem(int position) {
        return resultados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Item itemLinha;

        if(view == null){
            view=mInflater.inflate(R.layout.list_item, null);
            itemLinha= new Item();
            itemLinha.nome = (TextView)view.findViewById(R.id.nomes);
            itemLinha.data = (TextView)view.findViewById(R.id.datas);
            itemLinha.nota = (TextView)view.findViewById(R.id.notas);

            view.setTag(itemLinha);

        }else {

            itemLinha=(Item)view.getTag();

        }
        Resultado item = resultados.get(position);
        itemLinha.nome.setText(item.nome);
        itemLinha.data.setText(item.data);
        itemLinha.nota.setText(item.nota);

        return view;
    }

    private class Item{
        TextView nome;
        TextView data;
        TextView nota;
    }
}