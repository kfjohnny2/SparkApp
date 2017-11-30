package sonnesoft.com.spark;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import sonnesoft.com.spark.model.Docente;
import sonnesoft.com.spark.service.ServiceDocentes;

public class MainActivity extends AppCompatActivity {
    private String hits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DocentesAsyncTask().execute(null, null, null);


    }

    private class DocentesAsyncTask extends AsyncTask<Void, Void, List<Docente>>{

        @Override
        protected List<Docente> doInBackground(Void... voids) {
            return ServiceDocentes.getDocentes(getApplicationContext());
        }

        @Override
        protected void onPostExecute(List<Docente> s) {
            super.onPostExecute(s);
            for (Docente d: s) {
                Log.d("DOCENTE: ", d.getNome());
            }
        }
    }
}
