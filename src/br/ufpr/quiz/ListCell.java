package br.ufpr.quiz;

//import com.example.listacar.R;

import java.util.List;





import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListCell extends ArrayAdapter<Resultado> {
	private final Activity context;
	private final List<Resultado> result;

	
	public ListCell(Activity context, List<Resultado> result) {
		super(context, R.layout.activity_list_cell, result);
		this.context = context;
		this.result = result;
		
	}
	
	public View getView(int position, View view, ViewGroup parent) {
		Log.i("ha"," chegou aqui na getView");
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.activity_list_cell,  null, true);
		TextView txtNome= (TextView) rowView.findViewById(R.id.nome);
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		TextView txtPreco = (TextView) rowView.findViewById(R.id.preco);
		//txtNome.setText(result.get(position).getNome());
		//this.imageLoader.displayImage(prod.get(position).getImageResource(), imageView);
		//txtPreco.setText("R$ " + prod.get(position).getPreco());
		return rowView;
	}
}