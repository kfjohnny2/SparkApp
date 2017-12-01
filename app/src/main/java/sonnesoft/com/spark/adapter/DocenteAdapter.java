package sonnesoft.com.spark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
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
    private List<Docente> docentesTotal;
    private static final int LOAD_MORE = 10;
    public DocenteAdapter(List<Docente> docentes, Context context) {
        this.docentesTotal =docentes;
        List<Docente> subList = docentes.subList(0, LOAD_MORE);
        this.docentes = new ArrayList<>(subList);
        this.context = context;
    }

    public void addMore(){
        List<Docente> subList = docentesTotal.subList(docentes.size()-1, (docentes.size()-1)+LOAD_MORE);
        List<Docente> copyOfSubList = new ArrayList<>(subList);
        docentes.addAll(copyOfSubList);
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
        TextView txtFormacao = view.findViewById(R.id.txtFormacao);
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
