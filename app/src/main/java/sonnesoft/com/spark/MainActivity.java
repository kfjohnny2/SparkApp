package sonnesoft.com.spark;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.List;

import sonnesoft.com.spark.adapter.DocenteAdapter;
import sonnesoft.com.spark.model.Docente;
import sonnesoft.com.spark.service.ServiceDocentes;

public class MainActivity extends AppCompatActivity {
    private String hits;
    private DocenteAdapter docenteAdapter;
    private ListView listDocentes;
    private boolean flag_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        new DocentesAsyncTask().execute(null, null, null);

    }

    private void setup() {
        listDocentes = findViewById(R.id.listDocentes);
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

    private class DocentesAsyncTask extends AsyncTask<Void, Void, List<Docente>> {

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
                    docenteAdapter = new DocenteAdapter(s, getApplicationContext());
                    listDocentes.setAdapter(docenteAdapter);
                }
            });


        }
    }
}
