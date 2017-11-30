package sonnesoft.com.spark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sonnesoft.com.spark.R;
import sonnesoft.com.spark.model.Docente;

/**
 * Created by johnnylee on 30/11/17.
 */

public class DocenteAdapter extends BaseAdapter {
    List<Docente> docentes;
    Context context;
    private LayoutInflater mInflator;

    public DocenteAdapter(List<Docente> docentes, Context context) {
        this.docentes = docentes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return docentes.size();
    }

    @Override
    public Object getItem(int i) {
        return docentes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return docentes.get(i).getIdServidor();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = mInflator.inflate(R.layout.item_list_docente, null);
        TextView txtNome = view.findViewById(R.id.txtNome);
        TextView txtFormacao = (TextView) view.findViewById(R.id.txtFormacao);
        TextView txtVinculo = view.findViewById(R.id.txtVinculo);
        Docente d = docentes.get(i);
        if(d != null){
            txtNome.setText(d.getNome());
            txtFormacao.setText(d.getFormacao());
            txtVinculo.setText(d.getVinculo());
        }
        return view;
    }
}
