package sonnesoft.com.spark;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class DocenteDetalhadoActivity extends AppCompatActivity {
    private TextView txtNome;
    private TextView txtLocacao;
    private TextView txtCategoria;
    private TextView txtSiape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_detalhado);
        Intent i = getIntent();
        String nome = i.getStringExtra(getApplicationContext().getString(R.string.EXTRA_NOME));
        String categoria = i.getStringExtra(getApplicationContext().getString(R.string.EXTRA_CATEGORIA));
        String locacao = i.getStringExtra(getApplicationContext().getString(R.string.EXTRA_LOCACAO));
        final String siape = i.getStringExtra(getApplicationContext().getString(R.string.EXTRA_SIAPE));

        txtNome = findViewById(R.id.txtNome);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtLocacao = findViewById(R.id.txtLocacao);
        txtSiape = findViewById(R.id.txtConsultaSiape);

        txtNome.setText(nome);
        txtCategoria.setText(categoria);
        txtLocacao.setText(locacao);
        txtSiape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sigaa.ufrn.br/sigaa/public/docente/portal.jsf?siape="+siape));
                startActivity(browserIntent);
            }
        });
    }
}
