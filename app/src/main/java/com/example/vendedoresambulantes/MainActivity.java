package com.example.vendedoresambulantes;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void teste(View v){
        TextView    tProd = findViewById(R.id.edTipoProd),
                    nomVend = findViewById(R.id.edNomeVendedor),
                    cid = findViewById(R.id.edCidade),
                    dSem = findViewById(R.id.edDiaSemana);

        String  v1 = tProd.getText().toString(),
                v2 = nomVend.getText().toString(),
                v3 = cid.getText().toString(),
                v4 = dSem.getText().toString(),
                confirmacao = "";

        Banco db = new Banco(this);
        if (db.insere(v1, v2, v3, v4)){
            confirmacao = "inserido com sucesso";
        } else{
            confirmacao = "Erro ao inserir";
        }

        Toast.makeText(this, confirmacao, Toast.LENGTH_SHORT).show();
    }

    public void consulta (View v){
        TextView ed = findViewById(R.id.edCon);

        Banco db = new Banco(this);
        String dados = db.consulta();

        ed.setText(dados);

        Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();

    }
}
