package sonnesoft.com.spark;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sonnesoft.com.spark.adapter.DocenteAdapter;
import sonnesoft.com.spark.model.Docente;
import sonnesoft.com.spark.service.ServiceDocentes;

public class MainActivity extends AppCompatActivity {
    private DocenteAdapter docenteAdapter;
    private ListView listDocentes;
    private boolean flag_loading;
    private EditText edSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        new DocentesAsyncTask().execute(null, null, null);

    }

    private void setup() {
        listDocentes = findViewById(R.id.listDocentes);
        edSearch = findViewById(R.id.edConsulta);
        edSearch.setOnEditorActionListener(editorActionListener);
        listDocentes.setOnItemClickListener(itemClickListener);
        listDocentes.setOnScrollListener(new AbsListView.OnScrollListener() {

            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    if (!flag_loading) {
                        flag_loading = true;
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                docenteAdapter.addMore();

                            }
                        });
                        flag_loading = false;
                        docenteAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if(i == EditorInfo.IME_ACTION_DONE){
                searchResult(textView.getText().toString());
                return true;
            }
            return false;
        }
    };

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Docente d = (Docente) docenteAdapter.getItem(i);
            Intent intent = new Intent(MainActivity.this, DocenteDetalhadoActivity.class);
            intent.putExtra(getApplicationContext().getString(R.string.EXTRA_NOME), d.getNome());
            intent.putExtra(getApplicationContext().getString(R.string.EXTRA_CATEGORIA), d.getCategoria());
            intent.putExtra(getApplicationContext().getString(R.string.EXTRA_LOCACAO), d.getLotacao());
            intent.putExtra(getApplicationContext().getString(R.string.EXTRA_SIAPE), d.getSiape());
            startActivity(intent);

        }
    };

    private void searchResult(String criteria){
        docenteAdapter.search(criteria);
        docenteAdapter.notifyDataSetChanged();
    }
    private class DocentesAsyncTask extends AsyncTask<Void, Void, List<Docente>> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(MainActivity.this, "SPARK", "Fetching data...");

        }

        @Override
        protected List<Docente> doInBackground(Void... voids) {
            return ServiceDocentes.getDocentes(getApplicationContext());
        }

        @Override
        protected void onPostExecute(final List<Docente> s) {
            super.onPostExecute(s);
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(s != null){
                        docenteAdapter = new DocenteAdapter(s, getApplicationContext());
                        listDocentes.setAdapter(docenteAdapter);
                    }
                }
            });

            pDialog.dismiss();
        }
    }
}
